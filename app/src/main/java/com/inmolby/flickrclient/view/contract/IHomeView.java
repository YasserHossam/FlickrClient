package com.inmolby.flickrclient.view.contract;

import com.inmolby.flickrclient.data.model.network.FlickrImage;

import java.util.ArrayList;

/**
 * Created by yasser on 15/10/17.
 */

public interface IHomeView {
    void showImages(ArrayList<FlickrImage> images);
    void clearImages();
    void showConnectionError(String errorMessage);
    void showError(String errorMessage);
    void hideRefreshing();
    void hideProgressBar();
    void showProgressBar();
}
