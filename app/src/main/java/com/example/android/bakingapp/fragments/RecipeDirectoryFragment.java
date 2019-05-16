package com.example.android.bakingapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.RecipeDirectoryActivity;
import com.example.android.bakingapp.activities.SelectedRecipeActivity;
import com.example.android.bakingapp.adapters.GenericAdapter;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.viewmodel.RecyclerViewAdapterViewModel;

import java.util.ArrayList;

public class RecipeDirectoryFragment extends Fragment {

    private RecipeDirectoryActivity hostActivity;

    private RecyclerView mRecyclerView;
//    private RecipeDirectoryAdapter /*RecyclerView.Adapter*/ mAdapter;

    private GenericAdapter<RecyclerViewAdapterViewModel> mGenericAdapter;
//    private GenericAdapterKT<RecyclerViewAdapterViewModel> mGenericAdapterKT;

    private RecyclerView.LayoutManager mLayoutManager;

    public RecipeDirectoryFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_directory, container, false);

        mRecyclerView = rootView.findViewById(R.id.recipe_directory_recyclerview);

        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hostActivity = (RecipeDirectoryActivity) getActivity();



        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(getActivity(), numberOfColumns());
//        mLayoutManager = new LinearLayoutManager(hostActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final ArrayList<Recipe> recipes = hostActivity.getRecipes();

        // specify an adapter
//        mAdapter = new RecipeDirectoryAdapter(recipes);
//        mRecyclerView.setAdapter(mAdapter);
        //TODO: USING GENERIC ADAPTER:
        mGenericAdapter = new GenericAdapter<>(R.layout.recipe_card/*, recipes*/);
        mGenericAdapter.addItems(recipes);
        mGenericAdapter.setOnListItemViewClickListener(new GenericAdapter.OnListItemViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Recipe recipe = recipes.get(position);
                if (recipe != null) {
                    Context context = view.getContext();
                    Intent selectedRecipeIntent = new Intent(context, SelectedRecipeActivity.class);
                    selectedRecipeIntent.putExtra("CLICKED_RECIPE", recipe);
                    context.startActivity(selectedRecipeIntent);
                }
            }
        });
        mRecyclerView.setAdapter(mGenericAdapter);

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
