package com.example.test.moviedbapplication.model;

import com.example.test.moviedbapplication.CategoryDetailsActivity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by babunder.prajapati on 8/25/2018.
 */

public class CategoriesDetail {

    @SerializedName("id")
    @Expose
    public String id = "";

    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath = "";

    @SerializedName("original_title")
    @Expose
    public String originalTitle = "";

    @SerializedName("overview")
    @Expose
    public String overview = "";


    @SerializedName("poster_path")
    @Expose
    public String posterPath = "";

    @SerializedName("release_date")
    @Expose
    public String releaseDate = "";

    @SerializedName("vote_average")
    @Expose
    public String voteAverage = "";

    public CategoriesDetail(){

    }
}
