package com.example.socialapp.nav;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialapp.LikeFragment;
import com.example.socialapp.PostFragment;
import com.example.socialapp.R;
import com.example.socialapp.SaveFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    TabLayout tab;
    ViewPager2 viewPager2;
    String phoneNumber;
    String name, profileAvatar, nickName, following, follower, like;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tab = view.findViewById(R.id.tab);
        viewPager2 = view.findViewById(R.id.viewpager2);
        Bundle b = this.getArguments();
        assert b != null;
        phoneNumber = b.getString("Phone number");

        DocumentReference docRef = db.collection("Users").document("user_" + phoneNumber);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        name = document.getString("Name");
                        nickName = document.getString("Citizen identification");
                        following = String.valueOf(document.get("Following"));
                        follower = String.valueOf(document.get("Follower"));
                        like = String.valueOf(document.get("Like"));
                        profileAvatar = document.getString("Profile avatar");

                        ((TextView) view.findViewById(R.id.name)).setText(name);
                        ((TextView) view.findViewById(R.id.nickName)).setText(nickName);
                        ((TextView) view.findViewById(R.id.following)).setText(following);
                        ((TextView) view.findViewById(R.id.follower)).setText(follower);
                        ((TextView) view.findViewById(R.id.like)).setText(like);
                        if (Objects.equals(profileAvatar, "default")) {
                            ((ImageView) view.findViewById(R.id.profileAvatar)).setImageResource(R.drawable.default_avatar);
                        }
                    }
                }
            }
        });


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