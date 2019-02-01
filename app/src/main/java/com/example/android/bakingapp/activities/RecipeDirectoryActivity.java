package com.example.android.bakingapp.activities;

import android.os.Bundle;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.fragments.RecipeFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class RecipeDirectoryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_directory);

        //create an instance of our fragment class
        RecipeFragment recipeFragment = new RecipeFragment();
        // and add it using the fragment manager (getSupportFragmentManager()) and beginTransaction() on it
        FragmentManager fragmentManager = getSupportFragmentManager();

        //todo: no need to do this here because the fragment is static
        //todo: but we will need it in the upcoming activity
//        fragmentManager.beginTransaction()
//                .add(R.id.fragment_recipe_directory,recipeFragment )
//                .commit();


    }

//    private void useDummyJson(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        try {
//            JSONArray dummyRecipesArray = new JSONArray(getResources().getString(R.string.recipes_json));
//            FragmentTransaction ft = fragmentManager.beginTransaction();
//            for(int i=0; i<dummyRecipesArray.length(); i++){
//                RecipeFragment recipeFragment = new RecipeFragment();
//                ft.add(R.id.recipe_card_container, recipeFragment);
//
//            }
//            ft.commit();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


//    private void getRecipesFromNetwork(){}
}
