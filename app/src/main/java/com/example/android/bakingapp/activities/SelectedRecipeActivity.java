package com.example.android.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.fragments.RecipeDetailsFragment;
import com.example.android.bakingapp.fragments.RecipeStepDetailsFragment;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.RecipeStep;

public class SelectedRecipeActivity extends AppCompatActivity {

    private boolean mTwoPane;
    private FragmentManager fragmentManager;
    private RecipeDetailsFragment recipeDetailsFragment;
    private RecipeStepDetailsFragment recipeStepDetailsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_recipe);

        //set title using selected recipe -> which we get from intent
        Intent selectedRecipeIntent = getIntent();
        Recipe selectedRecipe = selectedRecipeIntent.getParcelableExtra("CLICKED_RECIPE");
        setTitle(selectedRecipe.getName());


        recipeDetailsFragment = new RecipeDetailsFragment();

        Bundle recipeBundle = new Bundle();
        recipeBundle.putParcelable("SELECTED_RECIPE", selectedRecipe);
        recipeDetailsFragment.setArguments(recipeBundle);

        fragmentManager = getSupportFragmentManager();

        recipeStepDetailsFragment = new RecipeStepDetailsFragment(); // create it for later

        if(findViewById(R.id.fragment_recipe_step_details) != null) {

            mTwoPane = true;

            recipeStepDetailsFragment.setTwoPane(mTwoPane);

            // sw600dp layout will be used:
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_recipe_container, recipeDetailsFragment)
                    .add(R.id.fragment_recipe_step_details, recipeStepDetailsFragment)
                    .commit();
        } else {
            mTwoPane = false;

            recipeStepDetailsFragment.setTwoPane(mTwoPane);

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_recipe_container, recipeDetailsFragment)
                    .commit();
        }
    }

    public void toggleFragments(RecipeStep recipeStep){
        if(fragmentManager != null){

            recipeStepDetailsFragment.setStep(recipeStep);

            if(mTwoPane){

                recipeStepDetailsFragment.preparePlayer();

            } else { // modify visibility:

                // todo: put current fragment in the stack????
//                fragmentManager.putFragment(Bundle bundle, String key, Fragment fragment);

                // simulate fragment replacement by showing or hiding:
                FragmentTransaction ft = fragmentManager.beginTransaction();

                // Hide recipeDetailsFragment
                if (recipeDetailsFragment.isAdded()) {
                    ft.hide(recipeDetailsFragment);
                }
                if (recipeStepDetailsFragment.isAdded()) { // if the fragment is already in container
                    ft.show(recipeStepDetailsFragment);
                } else { // fragment needs to be added to frame container
                    ft.add(R.id.fragment_recipe_container, recipeStepDetailsFragment);
                }
                ft.commit();
            }
        }
    }


    @Override
    public void onBackPressed() {
        //if details fragment is showing (when not in two-pane mode), simulate going back by showing another fragment
        if (!mTwoPane && recipeStepDetailsFragment != null && recipeStepDetailsFragment.isVisible()) {
            fragmentManager.beginTransaction()
            .hide(recipeStepDetailsFragment)
            .show(recipeDetailsFragment)
            .commit();
        }
        else {
            super.onBackPressed();
        }
    }
}
