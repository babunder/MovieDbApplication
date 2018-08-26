package com.example.test.moviedbapplication;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.example.test.moviedbapplication.UtillClass.AppConfig;
import com.example.test.moviedbapplication.UtillClass.GridSpacingItemDecoration;
import com.example.test.moviedbapplication.UtillClass.Utility;
import com.example.test.moviedbapplication.adapter.PostsAdapter;
import com.example.test.moviedbapplication.callback.callbackInterface;
import com.example.test.moviedbapplication.database.DatabaseAdapter;
import com.example.test.moviedbapplication.viewmodel.CategoryViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements callbackInterface {

    private CategoryViewModel categoryViewModel;
    private ArrayList<CategoryViewModel> lstCategoryViewModels;
    private TextView tvMessage;
    private Utility mUtility;
    private Context mContext;
    private DatabaseAdapter dbAdapter;

    private String strSelectionType;
    private TextView tvMostPopular, tvFavorite;

    private PostsAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        displayUI();
    }

    /**
     * Method to initialize the UI elements
     */
    public void init() {

        mContext = this;
        mUtility = new Utility(mContext);
        lstCategoryViewModels = new ArrayList<>();

        dbAdapter = new DatabaseAdapter(MainActivity.this);
        dbAdapter.open();

        tvMessage = (TextView) findViewById(R.id.tvMessage);
        tvMostPopular = (TextView) findViewById(R.id.tvMostPopular);
        tvFavorite = (TextView) findViewById(R.id.tvFavorite);
        strSelectionType = AppConfig.POPULAR;

        initRecyclerView();
    }

    /**
     * Renders RecyclerView with Grid Images in 3 columns
     */
    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(4), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    /**
     * Method to display the Ui with data
     */
    public void displayUI() {

        recyclerView.setVisibility(View.VISIBLE);
        tvMessage.setVisibility(View.GONE);
        if (strSelectionType.equals(AppConfig.POPULAR)) {

            if (mUtility.checkInternetAvailability()) {

                categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
                final ProgressDialog progressDoalog = new ProgressDialog(this);
                progressDoalog.setMessage("Loading....");
                progressDoalog.show();

                categoryViewModel.getArrayListMutableLiveData().observe(this, new Observer<ArrayList<CategoryViewModel>>() {
                    @Override
                    public void onChanged(@Nullable ArrayList<CategoryViewModel> categoryViewModels) {
                        progressDoalog.dismiss();
                        lstCategoryViewModels = categoryViewModels;
                        setValueToList(lstCategoryViewModels);
                    }
                });

            } else {
                recyclerView.setVisibility(View.GONE);
                tvMessage.setVisibility(View.VISIBLE);
            }

        } else {

            lstCategoryViewModels = dbAdapter.fetchFavoriteMovie();
            if (lstCategoryViewModels != null && lstCategoryViewModels.size() > 0) {
                setValueToList(lstCategoryViewModels);
            } else {
                recyclerView.setVisibility(View.GONE);
                tvMessage.setText(mContext.getResources().getString(R.string.lbl_no_record_found));
                tvMessage.setVisibility(View.VISIBLE);
            }
        }

    }

    /**
     * Method to display the details in GridView
     */
    public void setValueToList(ArrayList<CategoryViewModel> categoryViewModels) {
        if (mAdapter == null) {
            mAdapter = new PostsAdapter(this, categoryViewModels);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.refreshAdapter(categoryViewModels);
        }
    }

    /**
     * On click of Most popular Tab
     *
     * @param view
     */
    public void onMostPopularClick(View view) {
        tvMostPopular.setTextColor(mContext.getResources().getColor(R.color.green));
        tvFavorite.setTextColor(mContext.getResources().getColor(R.color.white));
        strSelectionType = AppConfig.POPULAR;
        displayUI();
    }

    /**
     * On click of Favorite Tab
     *
     * @param view
     */
    public void onFavoriteClick(View view) {
        tvMostPopular.setTextColor(mContext.getResources().getColor(R.color.white));
        tvFavorite.setTextColor(mContext.getResources().getColor(R.color.green));
        strSelectionType = AppConfig.FAVORITE;
        displayUI();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onTaskComplete() {
        if (strSelectionType.equals(AppConfig.FAVORITE)) {
            displayUI();
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
