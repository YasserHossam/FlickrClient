package com.inmolby.flickrclient.view.contract;

import com.inmolby.flickrclient.data.model.network.FlickrImage;

import java.util.ArrayList;

/**
 * Created by yasser on 15/10/17.
 * This interface is the Blue print to any implementation of home view
 */

public interface IHomeView {
    //Bind new data to the recycler view
    void showImages(ArrayList<FlickrImage> images);
    //Clear the data from the recyclerview
    void clearImages();

    void showConnectionError(String errorMessage);

    void showError(String errorMessage);
    //hides the refreshing circle of the SwipeRefreshLayout
    void hideRefreshing();

    void hideProgressBar();

    void showProgressBar();
    //Shows a snackbar with the given message
    void showLoadingView(String message);

}
