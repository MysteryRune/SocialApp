package com.example.socialapp.nav;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialapp.LikeFragment;
import com.example.socialapp.PostFragment;
import com.example.socialapp.R;
import com.example.socialapp.SaveFragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ProfileFragment extends Fragment {

    TabLayout tab;
    ViewPager2 viewPager2;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tab = view.findViewById(R.id.tab);
        viewPager2 = view.findViewById(R.id.viewpager2);

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
        tabLayoutMediator.attach();// Inflate the layout for this fragment
        return view;
    }

    private class ViewPagerAdapter extends FragmentStateAdapter {

        public ViewPagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if(position == 0){
                return new PostFragment();
            } else if (position==1) {
                return new SaveFragment();
            } else {
                return new LikeFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
}
    }