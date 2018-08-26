package com.example.test.moviedbapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by babunder.prajapati on 8/24/2018.
 */

public class Categories {

    @SerializedName("id")
    @Expose
    public String id = "";

    @SerializedName("title")
    @Expose
    public String title = "";

    @SerializedName("overview")
    @Expose
    public String desc = "";

    @SerializedName("poster_path")
    @Expose
    public String imagepath = "";

   /* public Categories(String id, String title, String desc, String imagepath) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.imagepath = imagepath;
    }*/

    public Categories() {
    }
}
