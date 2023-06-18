package com.example.socialapp.image_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.socialapp.model.image;

import java.util.ArrayList;
import java.util.List;

public class ImageDetailFragmentAdaptor extends FragmentStateAdapter {
    private ArrayList<image> images;

    public ImageDetailFragmentAdaptor(@NonNull FragmentActivity fragmentActivity, ArrayList<image> images) {
        super(fragmentActivity);
        this.images = images;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ImageDetail_Fragment.newInstance(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
