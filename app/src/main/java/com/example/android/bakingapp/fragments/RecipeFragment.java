package com.example.android.bakingapp.fragments;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.RecipeAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        mLayoutManager = new GridLayoutManager(getActivity(), numberOfColumns());
        mRecyclerView.setLayoutManager(mLayoutManager);


        ArrayList<String> myDataset = new ArrayList<>();
        String lorem = "The path of the righteous man is beset on all sides by the iniquities of the selfish and the tyranny of evil men. Blessed is he who, in the name of charity and good will, shepherds the weak through the valley of darkness, for he is truly his brother's keeper and the finder of lost children. And I will strike down upon thee with great vengeance and furious anger those who would attempt to poison and destroy My brothers. And you will know My name is the Lord when I lay My vengeance upon thee.";
        for(int i=0; i<12; i++){
            myDataset.add(lorem);
        }
        // specify an adapter
        mAdapter = new RecipeAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        return  rootView;
    }


    private int numberOfColumns() {
        int nColumns = 0;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = getActivity().getWindowManager();
        if(windowManager != null){
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //this divider can be changed to adjust the size of the poster
            int widthDivider = 450; //Integer.valueOf(Utilities.POSTER_SIZE);
            int width = displayMetrics.widthPixels;
            nColumns = width / widthDivider;
        }
        if(nColumns < 2) return 2;
        return nColumns;
    }

}
