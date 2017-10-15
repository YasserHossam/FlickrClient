package com.inmolby.flickrclient.data.local.contract;

import com.inmolby.flickrclient.data.model.FlickrImage;

import java.util.ArrayList;

/**
 * Created by yasser on 15/10/17.
 */

public interface LocalDB {
    void storeImages(ArrayList<FlickrImage> flickrImages);
    void retrieveImages();
}
