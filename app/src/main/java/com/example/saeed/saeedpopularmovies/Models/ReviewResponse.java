package com.example.saeed.saeedpopularmovies.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ReviewResponse {
    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("page")
    @Expose
    private Integer mPage;
    @SerializedName("results")
    @Expose
    private List<Review> mResults = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getmPage() {
        return mPage;
    }

    public void setmPage(Integer mPage) {
        this.mPage = mPage;
    }

    public List<Review> getmResults() {
        return mResults;
    }

    public void setmResults(List<Review> mResults) {
        this.mResults = mResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
