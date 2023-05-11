package com.example.socialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.socialapp.nav.FollowingFragment;
import com.example.socialapp.nav.HomeFragment;
import com.example.socialapp.nav.ProfileFragment;
import com.example.socialapp.nav.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreen extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        getSupportActionBar().hide();

        navigationView = findViewById(R.id.nav_bottom);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainscreen, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                if(item.getItemId() == R.id.nav_home){
                    fragment = new HomeFragment();
                } else if (item.getItemId() == R.id.nav_search) {
                    fragment = new SearchFragment();
                } else if (item.getItemId() == R.id.nav_post) {
                        showPostImage();
                        return true;
                } else if (item.getItemId() == R.id.nav_following) {
                    fragment = new FollowingFragment();
                } else if (item.getItemId() == R.id.nav_profile) {
                    fragment = new ProfileFragment();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.mainscreen, fragment).commit();

                return true;
            }
        });


    }

    private void showPostImage() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_post);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.Animation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        Button button;
        button = dialog.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}