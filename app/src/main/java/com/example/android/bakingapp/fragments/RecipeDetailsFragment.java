package com.example.android.bakingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.ListAdapter;
import com.example.android.bakingapp.models.Recipe;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeDetailsFragment extends Fragment {

//    private RecyclerView ingredientsRecyclerView;
//    private RecyclerView.Adapter mAdapter;

//    private ArrayList<JSONObject> ingredients;

    public RecipeDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_details_card, container, false);

        RecyclerView ingredientsRecyclerView = rootView.findViewById(R.id.recycler_view_ingredients);
        RecyclerView stepsRecyclerView = rootView.findViewById(R.id.recycler_view_steps);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        ingredientsRecyclerView.setLayoutManager(mLayoutManager);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bundle recipeBundle = getArguments();
        if(recipeBundle != null) {
            Recipe selectedRecipe = recipeBundle.getParcelable("SELECTED_RECIPE");

            ArrayList<String> ingredients = selectedRecipe.getIngredients();
//            IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredients);
            ListAdapter ingredientsAdapter = new ListAdapter(ingredients, R.layout.ingredient);
            ingredientsRecyclerView.setAdapter(ingredientsAdapter);

            ArrayList<String> recipeSteps = selectedRecipe.getSteps();
            ArrayList<String> steps = new ArrayList<>();
            for(int i=0; i<recipeSteps.size(); i++) {
                try {
                    JSONObject obj = new JSONObject(recipeSteps.get(i));
                    steps.add(obj.getString("shortDescription"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

//          StepsAdapter stepsAdapter = new IngredientsAdapter(steps);
            ListAdapter stepsAdapter = new ListAdapter(steps, R.layout.step);
            stepsRecyclerView.setAdapter(stepsAdapter);
        }
        return rootView;
    }
}
