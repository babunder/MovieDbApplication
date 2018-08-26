package com.example.test.moviedbapplication.UtillClass;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.test.moviedbapplication.R;

/**
 * Created by babunder.prajapati on 8/25/2018.
 */

public class Utility {


    private Context mContext;

    public Utility(Context context) {
        this.mContext = context;
    }

    /**
     * Method to check network availability
     *
     * @return
     */
    public boolean checkInternetAvailability() {

        ConnectivityManager check = (ConnectivityManager) mContext.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (check != null) {
            NetworkInfo[] info = check.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * Method to set Image URL.
     *
     * @param imageView
     * @param imageUrl
     */
    public static void loadImage(ImageView imageView, String imageUrl) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_default);
        requestOptions.error(R.drawable.ic_default);

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(AppConfig.IMAGE_BASE_URL + imageUrl)
                .into(imageView);

    }

}
