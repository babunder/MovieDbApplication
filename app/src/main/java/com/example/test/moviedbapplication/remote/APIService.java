package com.example.test.moviedbapplication.remote;

import com.example.test.moviedbapplication.model.CategoriesDetail;
import com.example.test.moviedbapplication.viewmodel.CategoryDetailsViewModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by babunder.prajapati on 8/24/2018.
 */

public interface APIService {

    // Get List of Popular Movie
    @GET("popular")
    Call<CategoryList> getCategoryList(@Query("api_key") String name);
    // Get Details of Movie based on Movie Id
    @GET("{movie_id}")
    Call<CategoriesDetail> getCategoryDetails( @Path("movie_id") String strMovieId, @Query("api_key") String name);


}
