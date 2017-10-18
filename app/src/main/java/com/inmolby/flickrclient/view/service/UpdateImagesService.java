package com.inmolby.flickrclient.view.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.inmolby.flickrclient.presenter.HomePresenter;
import com.inmolby.flickrclient.view.contract.IHomeView;

/**
 * Created by yasser on 18/10/17.
 * A service that is called every 1 minute to sync data with flickr api
 */

public class UpdateImagesService extends Service {

    private final IBinder mBinder = new LocalBinder();

    private static IHomeView iHomeView;

    HomePresenter homePresenter;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (iHomeView != null) {
            homePresenter = new HomePresenter(iHomeView, getApplicationContext());
            homePresenter.updateImages();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public UpdateImagesService getServiceInstance() {
            return UpdateImagesService.this;
        }
    }

    public static void registerCallbacks(IHomeView iHomeViewCallback) {
        iHomeView = iHomeViewCallback;
    }
}


