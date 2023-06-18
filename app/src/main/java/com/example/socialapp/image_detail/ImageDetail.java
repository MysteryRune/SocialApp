package com.example.socialapp.image_detail;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.socialapp.ProfileOtherUser;
import com.example.socialapp.R;
import com.example.socialapp.model.image;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageDetail extends AppCompatActivity {

    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail);
        getSupportActionBar().hide();

        viewPager2 = findViewById(R.id.viewpager2);



        Intent intent = getIntent();
        ArrayList<image> images =(ArrayList<image>)  getIntent().getSerializableExtra("images");
        int currentPosition = intent.getIntExtra("current", 0);

        ImageDetailFragmentAdaptor imagePagerAdapter = new ImageDetailFragmentAdaptor(this, images);
        viewPager2.setAdapter(imagePagerAdapter);
        viewPager2.setCurrentItem(currentPosition, false);



    }


}