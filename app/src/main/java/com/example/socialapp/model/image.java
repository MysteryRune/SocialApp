package com.example.socialapp.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class image implements Serializable {
    private final Drawable drawable;
    private final String topic;
    private final String idImageStorage;
    private final String URL;

    private final String like;

    public image(Drawable imageId, String idImageStorage, String topic, String url, String like) {
        this.drawable = imageId;
        this.idImageStorage = idImageStorage;
        this.topic = topic;
        this.URL = url;
        this.like = like;
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

    public String getLike() {
        return like;
    }
}
