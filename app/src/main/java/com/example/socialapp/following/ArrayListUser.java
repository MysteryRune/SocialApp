package com.example.socialapp.following;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialapp.R;
import com.example.socialapp.model.User;
import com.example.socialapp.model.image;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArrayListUser {

    public ArrayList<User> setListData() {
        ArrayList<User> arrayList = new ArrayList<>();

        arrayList.add(new User(R.drawable.girl, "Tra Sữa", "trasua"));
        arrayList.add(new User(R.drawable.gamer, "Tra Đào", "tradao"));
        arrayList.add(new User(R.drawable.profile, "Tra tắc", "tratac"));
        arrayList.add(new User(R.drawable.panda, "Tra Vải", "travai"));
        arrayList.add(new User(R.drawable.man, "Tra Dâu", "tradau"));
        arrayList.add(new User(R.drawable.woman, "Tra Sữa", "a"));
        arrayList.add(new User(R.drawable.girl, "Tra Sữa", "b"));
        arrayList.add(new User(R.drawable.girl, "Tra Sữa", "c"));
        arrayList.add(new User(R.drawable.girl, "Tra Sữa", "d"));


        return arrayList;
    }
}
