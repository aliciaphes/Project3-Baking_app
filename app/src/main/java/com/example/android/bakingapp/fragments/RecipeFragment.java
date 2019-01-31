package com.example.android.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.RecipeAdapter;

public class RecipeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public RecipeFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_directory, container, false);

        mRecyclerView = rootView.findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(getActivity(), 5);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        String myDataset[] = {"1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3", "1", "2", "3"};
        mAdapter = new RecipeAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        return  rootView;
    }
}
