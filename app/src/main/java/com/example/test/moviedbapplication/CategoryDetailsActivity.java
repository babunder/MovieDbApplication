package com.example.test.moviedbapplication;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.test.moviedbapplication.UtillClass.AppConfig;
import com.example.test.moviedbapplication.UtillClass.Utility;
import com.example.test.moviedbapplication.database.DatabaseAdapter;
import com.example.test.moviedbapplication.databinding.DetailsViewModelBinding;
import com.example.test.moviedbapplication.viewmodel.CategoryDetailsViewModel;
import com.example.test.moviedbapplication.viewmodel.CategoryViewModel;
import com.example.test.moviedbapplication.viewmodel.MyViewModelFactory;

/**
 * Created by babunder.prajapati on 8/25/2018.
 */

public class CategoryDetailsActivity extends AppCompatActivity {

    private CategoryDetailsViewModel categoryDetailsViewModel;
    private  CategoryViewModel categoryViewModel ;
    private Utility mUtility;
    private Context mContext;
    DetailsViewModelBinding detailsViewModelBinding;
    private ScrollView scrollView;
    private TextView tvMessage;
    private DatabaseAdapter dbAdapter;
    private String strMovieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        displayUI();
    }

    /**
     * Method to initialize the UI elements
     */
    public void init() {

        mContext = this;
        mUtility = new Utility(mContext);

        detailsViewModelBinding = DataBindingUtil.setContentView(this, R.layout.category_detail);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.hide();

        scrollView = (ScrollView) detailsViewModelBinding.getRoot().findViewById(R.id.scrollView);
        tvMessage = (TextView) detailsViewModelBinding.getRoot().findViewById(R.id.tvMessage);

        dbAdapter = new DatabaseAdapter(mContext);
        dbAdapter.open();

        Intent intent = getIntent();
        if (intent != null) {
            strMovieId = intent.getStringExtra(AppConfig.MOVIE_ID);
            categoryViewModel = dbAdapter.getFavoriteById(strMovieId);
        }

        detailsViewModelBinding.getRoot().findViewById(R.id.ivBackPress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    /**
     * Method to display the Ui with data
     */
    public void displayUI() {

        if (mUtility.checkInternetAvailability()) {

            scrollView.setVisibility(View.VISIBLE);
            tvMessage.setVisibility(View.GONE);

            categoryDetailsViewModel = ViewModelProviders.of(this, new MyViewModelFactory(this.getApplication(), strMovieId)).get(CategoryDetailsViewModel.class);
            final ProgressDialog progressDoalog = new ProgressDialog(this);
            progressDoalog.setMessage("Loading....");
            progressDoalog.show();

            categoryDetailsViewModel.getMutableLiveData().observe(this, new Observer<CategoryDetailsViewModel>() {
                @Override
                public void onChanged(@Nullable CategoryDetailsViewModel categoryDetailsViewModel) {
                    progressDoalog.dismiss();
                    CategoryDetailsViewModel categoryDetailsViewModel1 = categoryDetailsViewModel;
                    detailsViewModelBinding.setCategoryDetailsModel(categoryDetailsViewModel1);
                    detailsViewModelBinding.executePendingBindings();
                }
            });
        }else{
            scrollView.setVisibility(View.GONE);
            tvMessage.setVisibility(View.VISIBLE);
        }
    }
}
