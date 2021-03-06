package com.inmolby.flickrclient.data.local.contract;

import com.inmolby.flickrclient.data.callback.PresenterCallback;
import com.inmolby.flickrclient.data.model.network.FlickrImage;

import java.util.ArrayList;

/**
 * Created by yasser on 15/10/17.
 * <p>
 * This Interface is the Blue Print to any Local Storage implementation
 */

public interface LocalStorageCalls {
    void storeImages(ArrayList<FlickrImage> flickrImages, PresenterCallback presenterCallback);

    void retrieveImages(PresenterCallback presenterCallback);
}
