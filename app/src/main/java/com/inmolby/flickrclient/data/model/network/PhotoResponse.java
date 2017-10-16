package com.inmolby.flickrclient.data.model.network;

import com.google.gson.annotations.SerializedName;
import com.inmolby.flickrclient.data.model.network.FlickrImage;

import java.util.ArrayList;

/**
 * Created by yasser on 15/10/17.
 */

public class PhotoResponse {
    @SerializedName("page")
    int pageNumber;

    @SerializedName("pages")
    int totalPages;

    @SerializedName("perpage")
    int dataPerPage;

    @SerializedName("total")
    int totalResults;

    @SerializedName("photo")
    ArrayList<FlickrImage> photos;

    public int getPageNumber() {
        return pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getDataPerPage() {
        return dataPerPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public ArrayList<FlickrImage> getPhotos() {
        return photos;
    }
}
