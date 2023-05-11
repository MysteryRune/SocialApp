package com.example.socialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ProfileOtherUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_otheruser);
        getSupportActionBar().hide();
    }
}