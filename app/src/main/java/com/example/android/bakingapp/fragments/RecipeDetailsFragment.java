package com.example.android.bakingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.IngredientsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<JSONObject> ingredients;

    public RecipeDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_details_card, container, false);

        mRecyclerView = rootView.findViewById(R.id.recycler_view_ingredients);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //todo: the following is temporary:
        ingredients = new ArrayList<>();
        try {
            JSONArray dummyRecipesArray = new JSONArray(getResources().getString(R.string.recipes_json));

            JSONObject recipe = (JSONObject) dummyRecipesArray.get(0);

            JSONArray ing = recipe.getJSONArray("ingredients");
            for (int i = 0; i < ing.length(); i++) {
                ingredients.add((JSONObject) ing.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mAdapter = new IngredientsAdapter(ingredients);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
