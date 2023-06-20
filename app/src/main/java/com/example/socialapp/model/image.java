package com.example.socialapp.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class image implements Serializable {
    private final Drawable drawable;
    private final String topic;
    private final String idImageStorage;
    private final String URL;

    public image(Drawable imageId, String idImageStorage, String topic, String url) {
        this.drawable = imageId;
        this.idImageStorage = idImageStorage;
        this.topic = topic;
        this.URL = url;
    }

    public Drawable getDrawble() {
        return drawable;
    }

    public String getIdImageStorage() {
        return idImageStorage;
    }

    public String getTopic() {
        return topic;
    }

    public String getURL() {
        return URL;
    }

}
