package com.example.socialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageDetail extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail);
        getSupportActionBar().hide();

        int imageId = getIntent().getIntExtra("imageId", 0);
        Drawable drawable = getResources().getDrawable(imageId);
        imageView = findViewById(R.id.imageView6);
        imageView.setImageDrawable(drawable);
    }
}