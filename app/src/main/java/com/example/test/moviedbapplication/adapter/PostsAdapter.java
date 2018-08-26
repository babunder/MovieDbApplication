package com.example.test.moviedbapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test.moviedbapplication.CategoryDetailsActivity;
import com.example.test.moviedbapplication.R;
import com.example.test.moviedbapplication.UtillClass.AppConfig;
import com.example.test.moviedbapplication.callback.callbackInterface;
import com.example.test.moviedbapplication.database.DatabaseAdapter;
import com.example.test.moviedbapplication.databinding.CategoryBinding;
import com.example.test.moviedbapplication.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {

    private List<CategoryViewModel> arrayList;
    private LayoutInflater layoutInflater;
    private Context context;
    private DatabaseAdapter dbAdapter;
    public callbackInterface callback;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CategoryBinding binding;

        public MyViewHolder(final CategoryBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public PostsAdapter(Context context, ArrayList<CategoryViewModel> arrayList) {

        this.arrayList = arrayList;
        this.context = context;

        this.callback = (callbackInterface) context;
        dbAdapter = new DatabaseAdapter(context);
        dbAdapter.open();

    }

    public void refreshAdapter(ArrayList<CategoryViewModel> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CategoryBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.innerlayout, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.binding.setCategorymodel(arrayList.get(position));


        final ImageView ivFavourite = holder.binding.ivFavourite;
        if (dbAdapter.isRecordExist(arrayList.get(position).id)) {
            ivFavourite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_fill_favorite));
        } else {
            ivFavourite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_border));
        }

        ivFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivFavourite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_fill_favorite));
                if (dbAdapter.isRecordExist(arrayList.get(position).id)) {
                    dbAdapter.deleteFavorite(arrayList.get(position).id);
                } else {
                    dbAdapter.insertFavoriteMovie(arrayList.get(position));
                }
                callback.onTaskComplete();
                notifyDataSetChanged();
            }
        });

        final LinearLayout llParent = holder.binding.llParent;
        llParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CategoryDetailsActivity.class).putExtra(AppConfig.MOVIE_ID, arrayList.get(position).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
