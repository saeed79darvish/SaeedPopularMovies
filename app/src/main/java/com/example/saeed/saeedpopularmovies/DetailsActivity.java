package com.example.saeed.saeedpopularmovies;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.saeed.saeedpopularmovies.Adapters.MovieReviewAdapter;
import com.example.saeed.saeedpopularmovies.Models.Movie;
import com.example.saeed.saeedpopularmovies.Models.Review;
import com.example.saeed.saeedpopularmovies.Models.ReviewResponse;
import com.example.saeed.saeedpopularmovies.REST.APIService;
import com.example.saeed.saeedpopularmovies.databinding.ActivityDetailsBinding;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetailsActivity extends AppCompatActivity {

    public static final String REVIEWS_STATE_KEY = "review-key";
    private static final String TAG = DetailsActivity.class.getSimpleName();
    private static final String EXTRA_OBJECT = "movie-object";
    Vibrator vibrator;
    List<Review> reviewList;
    ActivityDetailsBinding detailsBinding;
    MovieReviewAdapter movieReviewAdapter;
    RecyclerView reviewsRecyclerView;
    private Movie movieData;
    private String movieId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setTitle(R.string.title_activity_movie_details);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        setupRecyclerViewUi();

        if (getIntent().getExtras() != null) {
            movieData = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_OBJECT));
            movieId = movieData.getId();
        }
        updateUi(movieData);
        if (savedInstanceState != null) {
            reviewList = Parcels.unwrap(savedInstanceState.getParcelable(REVIEWS_STATE_KEY));
            movieReviewAdapter.setReviewList(reviewList);
            reviewsRecyclerView.setVisibility(View.VISIBLE);
        } else {
            fetchReviews(movieId);
        }
    }

    private void setupRecyclerViewUi() {

        reviewsRecyclerView = (RecyclerView) findViewById(R.id.rcv_reviews_list);
        movieReviewAdapter = new MovieReviewAdapter(this);
        reviewsRecyclerView.setHasFixedSize(true);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecyclerView.setAdapter(movieReviewAdapter);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(REVIEWS_STATE_KEY, Parcels.wrap(reviewList));
    }


    private void fetchReviews(String movieId) {
        detailsBinding.pbReviewsLoading.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<ReviewResponse> call = service.getMovieReviews(movieId, APIService.API_KEY);
        call.enqueue(new Callback<ReviewResponse>() {


            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                detailsBinding.pbReviewsLoading.setVisibility(View.INVISIBLE);
                if (!response.isSuccessful()) {
                    detailsBinding.tvReviewsError.setVisibility(View.VISIBLE);
                    return;
                }
                if (response.body().getResults().size() != 0) {
                    reviewsRecyclerView.setVisibility(View.VISIBLE);
                    reviewList = response.body().getResults();
                    movieReviewAdapter.setReviewList(reviewList);
                } else
                    detailsBinding.tvReviewsError.setVisibility(View.VISIBLE);
            }


            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                detailsBinding.pbReviewsLoading.setVisibility(View.INVISIBLE);
                detailsBinding.tvReviewsError.setVisibility(View.VISIBLE);
            }
        });
    }


    private void updateUi(Movie movieInfo) {
        detailsBinding.tvMovieTitle.setText(movieInfo.getTitle());
        detailsBinding.tvMovieRating.setText(movieInfo.getVote_average());
        detailsBinding.tvMovieOverview.setText(movieInfo.getOverview());
        detailsBinding.tvMovieReleaseDate.setText(movieInfo.getRelease_date());
        Picasso.with(this)
                .load(APIService.IMAGE_URL + movieInfo.getPoster_path())
                .into(detailsBinding.imvMovieThumbnail);

    }
}



