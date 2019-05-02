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
import com.example.android.bakingapp.adapters.IngredientsAdapter;
import com.example.android.bakingapp.adapters.StepsAdapter;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.RecipeStep;

import java.util.ArrayList;

public class RecipeDetailsFragment extends Fragment {

    public RecipeDetailsFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_details_card, container, false);

        RecyclerView ingredientsRecyclerView = rootView.findViewById(R.id.recycler_view_ingredients);
        RecyclerView stepsRecyclerView = rootView.findViewById(R.id.recycler_view_steps);

        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bundle recipeBundle = getArguments();
        if(recipeBundle != null) {
            Recipe selectedRecipe = recipeBundle.getParcelable("SELECTED_RECIPE");

            ArrayList<String> ingredients = selectedRecipe.getIngredients();
            IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredients);
//            ListAdapter ingredientsAdapter = new ListAdapter(getActivity(), ingredients, R.layout.ingredient);
            ingredientsRecyclerView.setAdapter(ingredientsAdapter);

            ArrayList<RecipeStep> recipeSteps = selectedRecipe.getSteps();

          StepsAdapter stepsAdapter = new StepsAdapter(getActivity(), recipeSteps);
//            ListAdapter stepsAdapter = new ListAdapter(getActivity(), recipeSteps, R.layout.step);
            stepsRecyclerView.setAdapter(stepsAdapter);
        }
        return rootView;
    }
}
