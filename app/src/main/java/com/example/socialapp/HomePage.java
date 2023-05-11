package com.example.socialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomePage extends AppCompatActivity {
TabLayout tab;
ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        tab = findViewById(R.id.tab);
        viewPager2 = findViewById(R.id.viewpager2);

        viewPager2.setAdapter(new ViewPagerAdapter(this));

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tab, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setIcon(getResources().getDrawable(R.drawable.more));
                } else if (position == 1) {
                    tab.setIcon(getResources().getDrawable(R.drawable.save));
                } else if (position == 2) {
                    tab.setIcon(getResources().getDrawable(R.drawable.heart));
                }
            }
        });
        tabLayoutMediator.attach();
    }
}