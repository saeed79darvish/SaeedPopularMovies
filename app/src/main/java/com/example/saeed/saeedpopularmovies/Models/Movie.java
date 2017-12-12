package com.example.saeed.saeedpopularmovies.Models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;


@Parcel
public class Movie implements Serializable {
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("overview")
    public String overView;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("id")
    public String mId;
    @SerializedName("title")
    public String mTitle;
    @SerializedName("vote_average")
    public String voteAverage;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "posterPath='" + posterPath + '\'' +
                ", overView='" + overView + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", mId='" + mId + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", voteAverage='" + voteAverage + '\'' +
                '}';
    }
}