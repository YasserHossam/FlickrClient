package com.inmolby.flickrclient.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yasser on 15/10/17.
 */

public class FlickrImage
{
    @SerializedName("id")
    long photoID;

    @SerializedName("owner")
    String ownerId;

    @SerializedName("secret")
    String secret;

    @SerializedName("server")
    int serverID;

    @SerializedName("farm")
    int farmID;

    @SerializedName("title")
    String title;

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
