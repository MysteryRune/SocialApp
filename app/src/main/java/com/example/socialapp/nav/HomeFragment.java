package com.example.socialapp.nav;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.socialapp.GridViewAdaptor;
import com.example.socialapp.image_detail.ImageDetail;
import com.example.socialapp.R;
import com.example.socialapp.model.ArrayListImageHome;
import com.example.socialapp.model.image;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener{


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.gridViewHome);

        GridViewAdaptor gridViewAdaptor = new GridViewAdaptor(requireActivity(), new ArrayListImageHome().setListData());
        gridView.setAdapter(gridViewAdaptor);
        gridView.setOnItemClickListener(this);
        // Inflate the layout for this fragment
     return rootView;


    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        image image = (image) adapterView.getItemAtPosition(i);

        ArrayList<image> images = new ArrayListImageHome().setListData();

        Intent intent = new Intent(getContext(), ImageDetail.class);

        intent.putExtra("images", images);

        intent.putExtra("current", i);


        startActivity(intent);
    }
}