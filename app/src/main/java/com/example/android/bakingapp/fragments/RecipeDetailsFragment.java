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
import com.example.android.bakingapp.activities.SelectedRecipeActivity;
import com.example.android.bakingapp.adapters.GenericAdapter;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.RecipeIngredient;
import com.example.android.bakingapp.models.RecipeStep;

import java.util.ArrayList;

public class RecipeDetailsFragment extends Fragment {

//    private SelectedRecipeActivity activity;

    public RecipeDetailsFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        activity = (SelectedRecipeActivity)getActivity();

        View rootView = inflater.inflate(R.layout.recipe_details_card, container, false);

        RecyclerView ingredientsRecyclerView = rootView.findViewById(R.id.recycler_view_ingredients);
        RecyclerView stepsRecyclerView = rootView.findViewById(R.id.recycler_view_steps);

        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bundle recipeBundle = getArguments();
        if(recipeBundle != null) {
            Recipe selectedRecipe = recipeBundle.getParcelable("SELECTED_RECIPE");

//            ArrayList<String> ingredients = selectedRecipe.getIngredients();
            ArrayList<RecipeIngredient> ingredients = selectedRecipe.getIngredients();

//            IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(ingredients);
//            ingredientsRecyclerView.setAdapter(ingredientsAdapter);
            //USING GENERIC ADAPTER:
            GenericAdapter ingredientsAdapter = new GenericAdapter<>(R.layout.ingredient);
            ingredientsAdapter.addItems(ingredients);
            ingredientsRecyclerView.setAdapter(ingredientsAdapter);



            final ArrayList<RecipeStep> recipeSteps = selectedRecipe.getSteps();

            //USING GENERIC ADAPTER:
//            StepsAdapter stepsAdapter = new StepsAdapter(getActivity(), recipeSteps);
//            stepsRecyclerView.setAdapter(stepsAdapter);
            GenericAdapter stepsAdapter = new GenericAdapter<>(R.layout.step);
            stepsAdapter.addItems(recipeSteps);
            stepsAdapter.setOnListItemViewClickListener(new GenericAdapter.OnListItemViewClickListener() {
                @Override
                public void onClick(View view, int position) {
                    RecipeStep recipeStep = recipeSteps.get(position);
                    if(recipeStep.getVideoURL().length() > 0){ // only react if videourl is not null
                        // todo: replace fragment if phone or show info if tablet

                        SelectedRecipeActivity activity = (SelectedRecipeActivity)getActivity();
                        activity.toggleFragments(recipeStep);
                    }
                }
            });
            stepsRecyclerView.setAdapter(stepsAdapter);
        }
        return rootView;
    }
}
