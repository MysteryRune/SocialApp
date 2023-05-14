package com.example.socialapp.nav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.socialapp.R;
import com.example.socialapp.following.ArrayListUser;
import com.example.socialapp.following.rcvUserAdapter;
import com.example.socialapp.model.User;

import java.util.ArrayList;
import java.util.List;


public class FollowingFragment extends Fragment {

    private RecyclerView recyclerView;
    private rcvUserAdapter userAdapter;

    private SearchView searchView;

    public FollowingFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_following, container, false);

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

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        userAdapter = new rcvUserAdapter(getContext() ,new ArrayListUser().setListData());
        recyclerView.setAdapter(userAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        // Inflate the layout for this fragment
        return view;
    }
    private void filterList(String s) {
        List<User> filterList = new ArrayList<>();
        for(User user: new ArrayListUser().setListData()){
            if(user.getId().toLowerCase().contains(s.toLowerCase())){
                filterList.add(user);
            }
        }

            userAdapter.updateList(filterList);

    }
}