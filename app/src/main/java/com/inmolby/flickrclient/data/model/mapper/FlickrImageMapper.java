package com.inmolby.flickrclient.data.model.mapper;

import com.inmolby.flickrclient.data.model.local.RealmFlickrImage;
import com.inmolby.flickrclient.data.model.network.FlickrImage;

import java.util.ArrayList;

/**
 * Created by yasser on 16/10/17.
 * A mapper class to map between Realm Objects and Retrofit Objects
 */

public class FlickrImageMapper {
    public static FlickrImage mapToFlickrImage(RealmFlickrImage realmFlickrImage)
    {
        return new FlickrImage(realmFlickrImage);
    }

    public static RealmFlickrImage mapToRealmFlickrImage(FlickrImage flickrImage)
    {
        return new RealmFlickrImage(flickrImage);
    }

    public static ArrayList<RealmFlickrImage> mapToRealmFlickrImageArrayList(ArrayList<FlickrImage> flickrImages)
    {
        ArrayList<RealmFlickrImage> realmFlickrImages = new ArrayList<>();

        for(FlickrImage flickrImage:flickrImages)
            realmFlickrImages.add(new RealmFlickrImage(flickrImage));

        return realmFlickrImages;
    }

    public static ArrayList<FlickrImage> mapToFlickrImageArrayList(ArrayList<RealmFlickrImage> realmFlickrImages)
    {
        ArrayList<FlickrImage> flickrImages = new ArrayList<>();

        for(RealmFlickrImage realmFlickrImage:realmFlickrImages)
            flickrImages.add(new FlickrImage(realmFlickrImage));

        return flickrImages;
    }
}
