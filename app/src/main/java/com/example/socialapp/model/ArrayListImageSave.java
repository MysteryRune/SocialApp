package com.example.socialapp.model;

import com.example.socialapp.R;

import java.util.ArrayList;

public class ArrayListImageSave {
    public ArrayList<image> setListData() {
        ArrayList<image> arrayList = new ArrayList<>();
        arrayList.add(new image(R.drawable.travai));
        arrayList.add(new image(R.drawable.trasua));
        arrayList.add(new image(R.drawable.tradau));
        arrayList.add(new image(R.drawable.tradao));
        arrayList.add(new image(R.drawable.tratac));
        arrayList.add(new image(R.drawable.trachanh));


        return arrayList;
    }
}
