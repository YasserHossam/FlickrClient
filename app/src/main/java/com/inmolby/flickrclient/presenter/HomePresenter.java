package com.inmolby.flickrclient.presenter;

import android.content.Context;

import com.inmolby.flickrclient.data.callback.PresenterCallback;
import com.inmolby.flickrclient.data.local.LocalStorageImpl;
import com.inmolby.flickrclient.data.model.network.FlickrImage;
import com.inmolby.flickrclient.data.network.NetworkImpl;
import com.inmolby.flickrclient.data.exception.NoConnectivityException;
import com.inmolby.flickrclient.presenter.contract.IHomePresenter;
import com.inmolby.flickrclient.view.contract.IHomeView;

import java.util.ArrayList;

/**
 * Created by yasser on 15/10/17.
 * Implementation of HomePresenter
 */

public class HomePresenter implements IHomePresenter {

    //Responsible for the interaction between presenter layer and view layer
    IHomeView iHomeView;

    //Responsible for the interaction between presenter layer and data(network) layer
    NetworkImpl networkCalls;

    //Responsible for the interaction between presenter layer and data(localDB) layer
    LocalStorageImpl localStorage;

    static final int PHOTOS_PER_PAGE = 20;

    //This boolean is to indicate if we already fetched data from storage (for offline data)
    boolean fetchedStorageData = false;

    public HomePresenter(IHomeView iHomeView, Context context) {
        this.iHomeView = iHomeView;
        networkCalls = NetworkImpl.getInstance(context);
        localStorage = LocalStorageImpl.getInstance();
    }

    //To Fetch the images when the user enters the application
    @Override
    public void initialImageLoading() {
        int page = 1;

        iHomeView.showProgressBar();
        networkCalls.getTrendingImages(PHOTOS_PER_PAGE, page, initialImageLoadingCallback);
    }

    //To Fetch the images for the Endless Scrolling (Infinite Scrolling)
    @Override
    public void loadMoreImages(int page) {
        iHomeView.showLoadingView("Loading more images");
        networkCalls.getTrendingImages(PHOTOS_PER_PAGE, page, loadMoreImagesCallback);
    }

    //To Fetch the images in the Manual and Automatic update
    @Override
    public void updateImages() {
        iHomeView.showLoadingView("Syncing With Flickr");
        networkCalls.getRecentImages(PHOTOS_PER_PAGE, 1, updateImagesCallback);
    }

    PresenterCallback<ArrayList<FlickrImage>> initialImageLoadingCallback = new PresenterCallback<ArrayList<FlickrImage>>() {
        @Override
        public void success(ArrayList<FlickrImage> data) {
            localStorage.clearAll();
            localStorage.storeImages(data, saveToLocalDBCallback);
            iHomeView.clearImages();
            iHomeView.hideProgressBar();
            iHomeView.showImages(data);
        }

        @Override
        public void error(Throwable t) {
            iHomeView.hideProgressBar();
            if (t instanceof NoConnectivityException) {
                iHomeView.showConnectionError(t.getMessage());
            } else
                iHomeView.showError(t.getMessage());
            if (!fetchedStorageData) {
                localStorage.retrieveImages(retrieveLocalDataCallback);
                fetchedStorageData =true;
            }
        }
    };

    PresenterCallback<ArrayList<FlickrImage>> loadMoreImagesCallback = new PresenterCallback<ArrayList<FlickrImage>>() {
        @Override
        public void success(ArrayList<FlickrImage> data) {
            localStorage.storeImages(data, saveToLocalDBCallback);
            iHomeView.showImages(data);
        }

        @Override
        public void error(Throwable t) {
            if (t instanceof NoConnectivityException) {
                iHomeView.showConnectionError(t.getMessage());
            } else
                iHomeView.showError(t.getMessage());
        }
    };

    PresenterCallback<ArrayList<FlickrImage>> updateImagesCallback = new PresenterCallback<ArrayList<FlickrImage>>() {
        @Override
        public void success(ArrayList<FlickrImage> data) {
            localStorage.clearAll();
            localStorage.storeImages(data, saveToLocalDBCallback);
            iHomeView.hideRefreshing();
            iHomeView.clearImages();
            iHomeView.showImages(data);
        }

        @Override
        public void error(Throwable t) {
            iHomeView.hideRefreshing();
            if (t instanceof NoConnectivityException)
                iHomeView.showConnectionError(t.getMessage());
            else
                iHomeView.showError(t.getMessage());
        }
    };

    PresenterCallback<Object> saveToLocalDBCallback = new PresenterCallback<Object>() {
        @Override
        public void success(Object data) {

        }

        @Override
        public void error(Throwable t) {

        }
    };

    PresenterCallback<ArrayList<FlickrImage>> retrieveLocalDataCallback = new PresenterCallback<ArrayList<FlickrImage>>() {
        @Override
        public void success(ArrayList<FlickrImage> data) {
            if (data != null && data.size() > 0)
                iHomeView.showImages(data);
        }

        @Override
        public void error(Throwable t) {

        }
    };
}
