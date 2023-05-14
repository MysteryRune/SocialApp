package com.example.socialapp.nav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;
import android.widget.SearchView;


import com.example.socialapp.GridViewAdaptor;
import com.example.socialapp.R;
import com.example.socialapp.model.ArrayListImageHome;
import com.example.socialapp.model.ArrayListImageSearch;
import com.example.socialapp.model.image;

import java.util.ArrayList;

public class SearchFragment extends Fragment {



    private SearchView searchView;

    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return true;
            }
        });


//        GridView gridView = (GridView) view.findViewById(R.id.gridViewSearch);
//        GridViewAdaptor gridViewAdaptor = new GridViewAdaptor(getActivity(), new ArrayListImageSearch().setListData());
//        gridView.setAdapter(gridViewAdaptor);



        return view;
    }

    private void filterList(String s) {
        ArrayList<image> filteredList = new ArrayList<>();

        for (image item : new ArrayListImageSearch().setListData()) {
            if (item.getChude().toLowerCase().contains(s.toLowerCase())) {
                filteredList.add(item);
            }
        }

        GridView gridView = (GridView) getView().findViewById(R.id.gridViewSearch);
        GridViewAdaptor gridViewAdaptor = new GridViewAdaptor(getActivity(), filteredList);
        gridView.setAdapter(gridViewAdaptor);

    }
}