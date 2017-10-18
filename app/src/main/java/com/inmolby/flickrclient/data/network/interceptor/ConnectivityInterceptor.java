package com.inmolby.flickrclient.data.network.interceptor;

import android.content.Context;

import com.inmolby.flickrclient.data.exception.NoConnectivityException;
import com.inmolby.flickrclient.data.network.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yasser on 15/10/17.
 * Retrofit Interceptor to check network connection before starting the request
 */

public class ConnectivityInterceptor implements Interceptor {
    private Context mContext;

    public ConnectivityInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtil.isOnline(mContext)) {
            throw new NoConnectivityException();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }


}
