package com.example.test.moviedbapplication.remote;

import com.example.test.moviedbapplication.UtillClass.AppConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by babunder.prajapati on 8/24/2018.
 */

public class RetroClass {

    private static Retrofit getRetroInstance(){

        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder().baseUrl(AppConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public static APIService getApiService(){

        return getRetroInstance().create(APIService.class);
    }



}
