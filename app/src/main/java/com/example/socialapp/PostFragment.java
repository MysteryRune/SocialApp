package com.example.socialapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.socialapp.model.ArrayListImagePost;


public class PostFragment extends Fragment {



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
        return rootView;
    }
}