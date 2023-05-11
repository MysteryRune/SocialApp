package com.example.socialapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.socialapp.model.ArrayListImagePost;
import com.example.socialapp.model.image;


public class PostFragment extends Fragment implements AdapterView.OnItemClickListener {



    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        GridViewAdaptor gridViewAdaptor = new GridViewAdaptor(getActivity(), new ArrayListImagePost().setListData());
        gridView.setAdapter(gridViewAdaptor);
        gridView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        image image = (image) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(getContext(), ImageDetail.class);

        intent.putExtra("imageId", image.getImageId());

        startActivity(intent);
    }
}