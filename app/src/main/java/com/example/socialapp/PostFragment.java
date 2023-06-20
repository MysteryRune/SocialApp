package com.example.socialapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.socialapp.image_detail.ImageDetail;
import com.example.socialapp.model.ArrayListImageHome;
import com.example.socialapp.model.ArrayListImagePost;
import com.example.socialapp.model.image;
import com.example.socialapp.nav.HomeFragment;

import java.util.ArrayList;


public class PostFragment extends Fragment implements AdapterView.OnItemClickListener {

    String phoneNumber;
    private ArrayList<image> mImageData;

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get Args
        Bundle b = this.getArguments();
        assert b != null;
        phoneNumber = b.getString("Phone number");

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        new ArrayListImageHome().setListData(new ArrayListImageHome.ImageDataCallback() {
            @Override
            public void onDataLoaded(ArrayList<image> imageData) {
                mImageData = imageData;
                GridViewAdaptor gridViewAdaptor = new GridViewAdaptor(requireActivity(), imageData);
                gridView.setAdapter(gridViewAdaptor);
                gridView.setOnItemClickListener(PostFragment.this);
            }
        });
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), ImageDetail.class);

        intent.putExtra("images", mImageData);

        intent.putExtra("current", i);
        startActivity(intent);
    }
}