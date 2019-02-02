package com.example.android.bakingapp.activities;

import android.os.Bundle;

import com.example.android.bakingapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDirectoryActivity extends AppCompatActivity {

    private ArrayList<JSONObject> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_directory);



        //todo: no need to do this here because the fragment is static
        //todo: but we will need it in the upcoming activity
        //create an instance of our fragment class
//        RecipeDirectoryFragment recipeFragment = new RecipeDirectoryFragment();
        // and add it using the fragment manager (getSupportFragmentManager()) and beginTransaction() on it
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(R.id.fragment_recipe_directory,recipeFragment )
//                .commit();


        //todo: https://developer.android.com/training/basics/fragments/communicating
        recipes = new ArrayList<>();
        try {
            JSONArray dummyRecipesArray = new JSONArray(getResources().getString(R.string.recipes_json));
            for(int i=0; i<dummyRecipesArray.length(); i++){
                JSONObject recipe = (JSONObject) dummyRecipesArray.get(i);
                recipes.add(recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

//    private void useDummyJson(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        try {
//            JSONArray dummyRecipesArray = new JSONArray(getResources().getString(R.string.recipes_json));
//            FragmentTransaction ft = fragmentManager.beginTransaction();
//            for(int i=0; i<dummyRecipesArray.length(); i++){
//                RecipeDirectoryFragment recipeFragment = new RecipeDirectoryFragment();
//                ft.add(R.id.recipe_card_container, recipeFragment);
//
//            }
//            ft.commit();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public ArrayList<JSONObject> getRecipes() {
        return recipes;
    }


//    private void getRecipesFromNetwork(){}
}
