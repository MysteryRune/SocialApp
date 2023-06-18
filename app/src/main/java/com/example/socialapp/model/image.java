package com.example.socialapp.model;

import java.io.Serializable;

public class image implements Serializable {
    private final Integer imageId;
    private final String chude;

    public image(Integer imageId, String chude) {
        this.imageId = imageId;
        this.chude = chude;
    }

    public Integer getImageId() {
        return imageId;
    }

    public String getChude() {
        return chude;
    }
}
