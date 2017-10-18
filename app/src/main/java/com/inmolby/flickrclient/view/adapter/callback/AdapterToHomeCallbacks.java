package com.inmolby.flickrclient.view.adapter.callback;

import com.inmolby.flickrclient.data.model.network.FlickrImage;

/**
 * Created by yasser on 16/10/17.
   This interface is called by the Adapter to inform HomeActivity of any Changes
 */

public interface AdapterToHomeCallbacks {
    void onThumbnailClick(FlickrImage image);
}
