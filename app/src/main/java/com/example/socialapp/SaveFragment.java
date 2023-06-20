package com.example.socialapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.socialapp.model.ArrayListImagePost;
import com.example.socialapp.model.ArrayListImageSave;

public class SaveFragment extends Fragment {

    String phoneNumber;

    public SaveFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_save, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview2);
        GridViewAdaptor gridViewAdaptor = new GridViewAdaptor(getActivity(), new ArrayListImageSave().setListData(phoneNumber));
        gridView.setAdapter(gridViewAdaptor);
        return rootView;
    }
}