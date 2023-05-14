package com.example.socialapp.model;

import com.example.socialapp.R;

import java.util.ArrayList;

public class ArrayListImageLike {
    public ArrayList<image> setListData() {
        ArrayList<image> arrayList = new ArrayList<>();
        arrayList.add(new image(R.drawable.tratac, "first"));
        arrayList.add(new image(R.drawable.tradau, "first"));
        arrayList.add(new image(R.drawable.tradao, "first"));
        arrayList.add(new image(R.drawable.trasua, "first"));
        arrayList.add(new image(R.drawable.trachanh, "first"));
        arrayList.add(new image(R.drawable.travai, "first"));

        return arrayList;
    }
}
