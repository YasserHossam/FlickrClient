package com.inmolby.flickrclient.presenter.contract;

/**
 * Created by yasser on 15/10/17.
 */

public interface IHomePresenter {
    void initialImageLoading();
    void loadMoreImages(int page);
    void swipeRefresh();
}
