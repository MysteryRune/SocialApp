package com.example.socialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

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

        CircleImageView circleImageView;
        circleImageView = findViewById(R.id.avatar);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ImageDetail.this, ProfileOtherUser.class));
            }
        });
    }
}