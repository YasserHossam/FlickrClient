package com.inmolby.flickrclient.data.local;

import com.inmolby.flickrclient.data.callback.PresenterCallback;
import com.inmolby.flickrclient.data.local.contract.LocalStorageCalls;
import com.inmolby.flickrclient.data.model.local.RealmFlickrImage;
import com.inmolby.flickrclient.data.model.mapper.FlickrImageMapper;
import com.inmolby.flickrclient.data.model.network.FlickrImage;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by yasser on 16/10/17.
 * <p>
 * Implementation of local storage using Realm
 */

public class LocalStorageImpl implements LocalStorageCalls {

    private static LocalStorageImpl instance;
    private final Realm realm;

    private LocalStorageImpl() {
        realm = Realm.getDefaultInstance();
    }

    public static LocalStorageImpl getInstance() {

        if (instance == null)
            instance = new LocalStorageImpl();
        return instance;
    }

    public void refresh() {

        realm.refresh();
    }

    public void clearAll() {

        realm.beginTransaction();
        realm.clear(RealmFlickrImage.class);
        realm.commitTransaction();
    }

    public boolean hasImages() {

        return !realm.allObjects(RealmFlickrImage.class).isEmpty();
    }

    @Override
    public void storeImages(final ArrayList<FlickrImage> flickrImages, final PresenterCallback presenterCallback) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ArrayList<RealmFlickrImage> realmFlickrImages = FlickrImageMapper.mapToRealmFlickrImageArrayList(flickrImages);
                for (RealmFlickrImage realmFlickrImage : realmFlickrImages)
                    realm.copyToRealm(realmFlickrImage);
            }
        }, new Realm.Transaction.Callback() {
            @Override
            public void onSuccess() {
                presenterCallback.success(null);
            }

            @Override
            public void onError(Exception e) {
                //TODO: Handle DB Error
            }
        });
    }

    @Override
    public void retrieveImages(final PresenterCallback presenterCallback) {
        final RealmResults<RealmFlickrImage> results = realm.where(RealmFlickrImage.class).findAllAsync();
        results.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                results.removeChangeListener(this);
                presenterCallback.success(FlickrImageMapper.mapToFlickrImageArrayList(new ArrayList<RealmFlickrImage>(results)));
            }
        });
    }
}
