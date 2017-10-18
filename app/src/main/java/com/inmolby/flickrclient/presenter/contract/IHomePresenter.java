package com.inmolby.flickrclient.presenter.contract;

/**
 * Created by yasser on 15/10/17.
 * This interface is the blueprint of HomeView Presenter (HomeActivity)
 */

public interface IHomePresenter {
    void initialImageLoading();
    void loadMoreImages(int page);
    void updateImages();
}
