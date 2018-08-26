package com.example.test.moviedbapplication.remote;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.test.moviedbapplication.UtillClass.AppConfig;
import com.example.test.moviedbapplication.model.Categories;
import com.example.test.moviedbapplication.model.CategoriesDetail;
import com.example.test.moviedbapplication.viewmodel.CategoryDetailsViewModel;
import com.example.test.moviedbapplication.viewmodel.CategoryViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by babunder.prajapati on 8/24/2018.
 */

public class UserRepository {

    public MutableLiveData<ArrayList<CategoryViewModel>> data = new MutableLiveData<>();
    private ArrayList<Categories> items;
    private ArrayList<CategoryViewModel> categoryViewModels;
    public MutableLiveData<CategoryDetailsViewModel> dataDetails = new MutableLiveData<>();

    public UserRepository() {
    }

    /**
     * Method to get popular Movie list Details form server
     *
     * @return
     */
    public MutableLiveData<ArrayList<CategoryViewModel>> getPopularMovieData() {

        APIService apiService = RetroClass.getApiService();

        Call<CategoryList> categoryListCall = apiService.getCategoryList(AppConfig.API_KEY);
        categoryListCall.enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {

                items = response.body().getCategories();

                CategoryViewModel categoryViewModel;
                categoryViewModels = new ArrayList<>();

                for (int i = 0; i < items.size(); i++) {

                    categoryViewModel = new CategoryViewModel(items.get(i));
                    categoryViewModels.add(categoryViewModel);
                }

                data.setValue(categoryViewModels);
            }

            @Override
            public void onFailure(Call<CategoryList> call, Throwable t) {

            }
        });

        return data;
    }


    /**
     * Method to get Movie Details form server
     *
     * @return
     */
    public MutableLiveData<CategoryDetailsViewModel> getMovieDetails(String strMovieId) {

        APIService apiService = RetroClass.getApiService();

        Call<CategoriesDetail> categoryListCall = apiService.getCategoryDetails(strMovieId, AppConfig.API_KEY);
        categoryListCall.enqueue(new Callback<CategoriesDetail>() {
            @Override
            public void onResponse(Call<CategoriesDetail> call, Response<CategoriesDetail> response) {

                CategoriesDetail mDetails = response.body();
                CategoryDetailsViewModel categoryDetailsViewModel = new CategoryDetailsViewModel(mDetails);
                dataDetails.setValue(categoryDetailsViewModel);
            }

            @Override
            public void onFailure(Call<CategoriesDetail> call, Throwable t) {
                Log.e("", t.getMessage());
            }
        });
        return dataDetails;
    }

}
