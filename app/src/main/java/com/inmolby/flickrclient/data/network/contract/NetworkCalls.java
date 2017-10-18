package com.inmolby.flickrclient.data.network.contract;

import com.inmolby.flickrclient.data.callback.PresenterCallback;

/**
 * Created by yasser on 15/10/17.
 * <p>
 * This Interface is the Blue Print to any Networking implementation
 */

public interface NetworkCalls {
    void getTrendingImages(int photosPerPage, int pageNumber, PresenterCallback presenterCallback);

    void getRecentImages(int photosPerPage, int pageNumber, PresenterCallback presenterCallback);
}
