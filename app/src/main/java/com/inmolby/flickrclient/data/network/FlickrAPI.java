package com.inmolby.flickrclient.data.network;

import com.inmolby.flickrclient.data.model.network.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yasser on 15/10/17.
 * <p>
 * Retrofit api routes
 */

public interface FlickrAPI {

    String GET_POPULAR_IMAGES_METHOD = "flickr.interestingness.getList";

    String GET_RECENT_IMAGES_METHOD = "flickr.photos.getRecent";

    String JSON_FORMAT = "json";

    @GET("/services/rest/?method=" + GET_POPULAR_IMAGES_METHOD)
    Call<BaseResponse> getPopularImages(@Query("api_key") String apiKey,
                                        @Query("per_page") int photosPerPage,
                                        @Query("page") int pageNumber,
                                        @Query("format") String format,
                                        @Query("nojsoncallback") int jsonCallback);

    @GET("/services/rest/?method=" + GET_RECENT_IMAGES_METHOD)
    Call<BaseResponse> getRecentImages(@Query("api_key") String apiKey,
                                       @Query("per_page") int photosPerPage,
                                       @Query("page") int pageNumber,
                                       @Query("format") String format,
                                       @Query("nojsoncallback") int jsonCallback);
}
