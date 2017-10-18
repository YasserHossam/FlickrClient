package com.inmolby.flickrclient.data.model.local;

import com.inmolby.flickrclient.data.model.network.FlickrImage;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yasser on 16/10/17.
 * The Image Model that is used by Realm to store images
 */

public class RealmFlickrImage extends RealmObject {
    @PrimaryKey
    private long photoID;

    private String ownerId;

    private String secret;

    private int serverID;

    private int farmID;

    private String title;

    public RealmFlickrImage() {
    }

    public RealmFlickrImage(long photoID, String ownerId, String secret, int serverID, int farmID, String title) {
        this.photoID = photoID;
        this.ownerId = ownerId;
        this.secret = secret;
        this.serverID = serverID;
        this.farmID = farmID;
        this.title = title;
    }

    public RealmFlickrImage(FlickrImage flickrImage) {
        this(flickrImage.getPhotoID(), flickrImage.getOwnerId(), flickrImage.getSecret(), flickrImage.getServerID(),
                flickrImage.getFarmID(), flickrImage.getTitle());
    }

    public void setPhotoID(long photoID) {
        this.photoID = photoID;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public void setFarmID(int farmID) {
        this.farmID = farmID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPhotoID() {
        return photoID;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getSecret() {
        return secret;
    }

    public int getServerID() {
        return serverID;
    }

    public int getFarmID() {
        return farmID;
    }

    public String getTitle() {
        return title;
    }
}
