package com.example.test.moviedbapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.test.moviedbapplication.UtillClass.Utility;
import com.example.test.moviedbapplication.model.CategoriesDetail;
import com.example.test.moviedbapplication.remote.UserRepository;

import java.util.ArrayList;

/**
 * Created by babunder.prajapati on 8/24/2018.
 */

public class CategoryDetailsViewModel extends ViewModel {

    public String id = "";
    public String backdropPath = "";
    public String originalTitle = "";
    public String overview = "";
    public String posterPath = "";
    public String releaseDate = "";
    public String voteAverage = "";


    public String strMovieId = "";
    public MutableLiveData<CategoryDetailsViewModel> arrayListMutableLiveData = new MutableLiveData<>();
    private UserRepository userRepository;
    public ArrayList<CategoryDetailsViewModel> categoryViewModels;

    public Context mContext;
    private Application mApplication;

    public CategoryDetailsViewModel(Application context , String strMovieId) {
        this.mApplication = context;
        this.strMovieId = strMovieId;

        userRepository = new UserRepository();
        arrayListMutableLiveData = userRepository.getMovieDetails(this.strMovieId);
    }


   /* public CategoryDetailsViewModel() {
        userRepository = new UserRepository();
        arrayListMutableLiveData = userRepository.getMovieData(this.strMovieId);
    }*/

    public CategoryDetailsViewModel(CategoriesDetail categoriesDetail) {

        this.id = categoriesDetail.id;
        this.backdropPath = categoriesDetail.backdropPath;
        this.originalTitle = categoriesDetail.originalTitle;
        this.overview = categoriesDetail.overview;
        this.posterPath = categoriesDetail.posterPath;
        this.releaseDate = categoriesDetail.releaseDate;
        this.voteAverage = categoriesDetail.voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }


    @BindingAdapter({"bind:backdropPath"})
    public static void loadBackDropImage(ImageView imageView, String imageUrl) {
       Utility.loadImage (imageView , imageUrl);
    }

    @BindingAdapter({"bind:posterPath"})
    public static void loadPosterImage(ImageView imageView, String imageUrl) {
        Utility.loadImage (imageView , imageUrl);
    }

    /**
     * method to get the live data
     *
     * @return
     */
    public MutableLiveData<CategoryDetailsViewModel> getMutableLiveData() {
        return arrayListMutableLiveData;
    }
}
