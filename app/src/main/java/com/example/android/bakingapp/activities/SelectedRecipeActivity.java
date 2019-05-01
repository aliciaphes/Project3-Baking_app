package com.example.android.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.fragments.RecipeDetailsFragment;
import com.example.android.bakingapp.fragments.RecipeStepDetailsFragment;
import com.example.android.bakingapp.models.Recipe;

public class SelectedRecipeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_recipe);

        //set title using selected recipe -> which we get from intent
        Intent selectedRecipeIntent = getIntent();
        Recipe selectedRecipe = selectedRecipeIntent.getParcelableExtra("CLICKED_RECIPE");
        setTitle(selectedRecipe.getName());


        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();

        Bundle recipeBundle = new Bundle();
        recipeBundle.putParcelable("SELECTED_RECIPE", selectedRecipe);
        recipeDetailsFragment.setArguments(recipeBundle);

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
