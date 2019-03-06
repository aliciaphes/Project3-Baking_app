package com.example.android.bakingapp.activities;

import android.os.Bundle;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.fragments.RecipeDetailsFragment;
import com.example.android.bakingapp.fragments.RecipeStepDetailsFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class SelectedRecipeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo: setTitle using selected recipe
        setContentView(R.layout.activity_selected_recipe);


        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragment_recipe_step_details) != null) {
            // sw600dp layout will be used:
            RecipeStepDetailsFragment recipeStepDetailsFragment = new RecipeStepDetailsFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_recipe_details, recipeDetailsFragment)
                    .add(R.id.fragment_recipe_step_details, recipeStepDetailsFragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_recipe_details, recipeDetailsFragment)
                    .commit();
        }

    }
}
