package com.inmolby.flickrclient.presenter;

import android.content.Context;

import com.inmolby.flickrclient.data.callback.PresenterCallback;
import com.inmolby.flickrclient.data.model.FlickrImage;
import com.inmolby.flickrclient.data.network.NetworkImpl;
import com.inmolby.flickrclient.data.network.exception.NoConnectivityException;
import com.inmolby.flickrclient.presenter.contract.IHomePresenter;
import com.inmolby.flickrclient.view.contract.IHomeView;

import java.util.ArrayList;

/**
 * Created by yasser on 15/10/17.
 */

public class HomePresenter implements IHomePresenter {

    IHomeView iHomeView;

    NetworkImpl networkCalls;

    static final int PHOTOS_PER_PAGE = 10;

    public  HomePresenter(IHomeView iHomeView,Context context)
    {
        this.iHomeView=iHomeView;
        networkCalls = NetworkImpl.getInstance(context);
    }

    @Override
    public void getPopularImages(int page,boolean isSwipeRefresh) {
        PresenterCallback p = presenterCallback;
        if(!isSwipeRefresh && page==1)
            iHomeView.showProgressBar();
        if(isSwipeRefresh)
            p=swipeRefreshCallback;

        networkCalls.getTrendingImages(PHOTOS_PER_PAGE,page,p);
    }

    PresenterCallback<ArrayList<FlickrImage>> presenterCallback = new PresenterCallback<ArrayList<FlickrImage>>() {
        @Override
        public void success(ArrayList<FlickrImage> data) {
            //TODO: Save the images to the database
            iHomeView.hideProgressBar();
            iHomeView.showImages(data);
        }

        @Override
        public void error(Throwable t) {
            iHomeView.hideProgressBar();
            if(t instanceof NoConnectivityException)
                iHomeView.showConnectionError(t.getMessage());
            else
                iHomeView.showError(t.getMessage());
        }
    };

    PresenterCallback<ArrayList<FlickrImage>> swipeRefreshCallback = new PresenterCallback<ArrayList<FlickrImage>>() {
        @Override
        public void success(ArrayList<FlickrImage> data) {
            //TODO: Clear the database
            //TODO: Save new data to database
            iHomeView.hideProgressBar();
            iHomeView.hideRefreshing();
            iHomeView.clearImages();
            iHomeView.showImages(data);
        }

        @Override
        public void error(Throwable t) {
            iHomeView.hideProgressBar();
            iHomeView.hideRefreshing();
            if(t instanceof NoConnectivityException)
                iHomeView.showConnectionError(t.getMessage());
            else
                iHomeView.showError(t.getMessage());
        }
    };
}
