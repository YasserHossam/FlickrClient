package com.inmolby.flickrclient.data.network;

import android.content.Context;
import com.inmolby.flickrclient.R;
import com.inmolby.flickrclient.data.callback.PresenterCallback;
import com.inmolby.flickrclient.data.model.network.BaseResponse;

import com.inmolby.flickrclient.data.network.contract.NetworkCalls;
import com.inmolby.flickrclient.data.network.interceptor.ConnectivityInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yasser on 15/10/17.
 */

public class NetworkImpl implements NetworkCalls {

    Retrofit retrofit;

    FlickrAPI flickrAPI;

    private String apiKey;

    private static NetworkImpl networkCalls;

    private NetworkImpl(Context appContext)
    {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ConnectivityInterceptor(appContext))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        flickrAPI = retrofit.create(FlickrAPI.class);
        apiKey = appContext.getResources().getString(R.string.flickr_api_key);
    }

    public static NetworkImpl getInstance(Context context)
    {
        if(networkCalls==null)
            networkCalls=new NetworkImpl(context);
        return networkCalls;
    }

    @Override
    public void getTrendingImages(int photosPerPage, int pageNumber, final PresenterCallback presenterCallback) {
        Call<BaseResponse> call = flickrAPI.getPopularImages(apiKey,photosPerPage,pageNumber,FlickrAPI.JSON_FORMAT,1);
        String s=call.request().url().url().toString();
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                presenterCallback.success(response.body().getPhotoResponse().getPhotos());
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                   presenterCallback.error(t);
            }
        });
    }
}
