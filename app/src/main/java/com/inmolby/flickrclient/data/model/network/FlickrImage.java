package com.inmolby.flickrclient.data.model.network;

import com.google.gson.annotations.SerializedName;
import com.inmolby.flickrclient.data.model.local.RealmFlickrImage;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yasser on 15/10/17.
 */

public class FlickrImage
{
    @SerializedName("id")
    private long photoID;

    @SerializedName("owner")
    private String ownerId;

    @SerializedName("secret")
    private String secret;

    @SerializedName("server")
    private int serverID;

    @SerializedName("farm")
    private int farmID;

    @SerializedName("title")
    private String title;

    public FlickrImage(long photoID, String ownerId, String secret, int serverID, int farmID, String title) {
        this.photoID = photoID;
        this.ownerId = ownerId;
        this.secret = secret;
        this.serverID = serverID;
        this.farmID = farmID;
        this.title = title;
    }

    public FlickrImage(RealmFlickrImage flickrImage)
    {
        this(flickrImage.getPhotoID(),flickrImage.getOwnerId(),flickrImage.getSecret(),flickrImage.getServerID()
                ,flickrImage.getFarmID(),flickrImage.getTitle());
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

    private String getUrl(char size)
    {
        return "https://farm1.staticflickr.com/"+serverID+"/"+photoID+"_"+secret+"_"+size+".jpg";
    }

    public String getSmallPicture()
    {
        return getUrl('m');
    }

    public String getMediumPicture()
    {
        return getUrl('z');
    }

    public String getLargePicture()
    {
        return getUrl('b');
    }

    public String getTitle() {
        return title;
    }
}
