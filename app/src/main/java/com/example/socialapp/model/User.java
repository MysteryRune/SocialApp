package com.example.socialapp.model;

public class User {
    private  int avatar;
    private String name;

    public User(int avatar, String name, String id) {
        this.avatar = avatar;
        this.name = name;
        this.id = id;
    }

    private String id;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
