package com.example.test.moviedbapplication.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.test.moviedbapplication.UtillClass.Utility;
import com.example.test.moviedbapplication.model.Categories;
import com.example.test.moviedbapplication.remote.UserRepository;

import java.util.ArrayList;

/**
 * Created by babunder.prajapati on 8/24/2018.
 */

public class CategoryViewModel extends ViewModel {

    public String id = "";
    public String title = "";
    public String desc = "";
    public String imagepath = "";

    public MutableLiveData<ArrayList<CategoryViewModel>> arrayListMutableLiveData = new MutableLiveData<>();
    private UserRepository userRepository;

    public CategoryViewModel() {

        userRepository = new UserRepository();
        arrayListMutableLiveData = userRepository.getPopularMovieData();
    }

    public CategoryViewModel(Categories categories) {
        this.id = categories.id;
        this.title = categories.title;
        this.desc = categories.desc;
        this.imagepath = categories.imagepath;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageUrl() {
        return imagepath;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
       // Picasso.with(imageView.getContext()).load(AppConfig.IMAGE_BASE_URL + imageUrl).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.ic_default).into(imageView);
        Utility.loadImage (imageView , imageUrl);
    }

    public MutableLiveData<ArrayList<CategoryViewModel>> getArrayListMutableLiveData() {
        return arrayListMutableLiveData;
    }
}
