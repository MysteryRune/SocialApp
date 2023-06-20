package com.example.socialapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.socialapp.model.image;

import java.util.ArrayList;

public class GridViewAdaptor extends ArrayAdapter<image> {
    public GridViewAdaptor(@NonNull Context context, ArrayList<image> images) {
        super(context,0, images);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HolderView holderView;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_gridview, parent, false);

            holderView = new HolderView(convertView);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }

        image image = getItem(position);
        holderView.imageView.setImageDrawable(image.getDrawble());
        Glide.with(getContext()).load(image.getURL()).into(holderView.imageView);
        Log.d(TAG, image.getIdImageStorage());

        return convertView;
    }

    private static class HolderView{
        private final ImageView imageView;

        public HolderView(View view) {
            imageView = (ImageView) view.findViewById(R.id.imageGrid);
        }
    }
}
