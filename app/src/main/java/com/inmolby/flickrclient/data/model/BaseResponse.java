package com.inmolby.flickrclient.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yasser on 15/10/17.
 */

public class BaseResponse {
    @SerializedName("photos")
    PhotoResponse photoResponse;

    public PhotoResponse getPhotoResponse() {
        return photoResponse;
    }
}
