package com.example.test.moviedbapplication.remote;

import com.example.test.moviedbapplication.model.Categories;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by babunder.prajapati on 8/24/2018.
 */

class CategoryList {

    @SerializedName("page")
    @Expose
    int strPageCount;

    @SerializedName("results")
    @Expose
    public ArrayList<Categories> categories= new ArrayList<>();

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

    public int getStrPageCount() {
        return strPageCount;
    }

    public void setStrPageCount(int strPageCount) {
        this.strPageCount = strPageCount;
    }
}
