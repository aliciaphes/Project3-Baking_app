package com.example.android.bakingapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeDirectoryActivity extends AppCompatActivity {

//    private ArrayList<JSONObject> recipes;
    private ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_directory);



        //todo: no need to do this here because the fragment is static
        //create an instance of our fragment class
//        RecipeDirectoryFragment recipeFragment = new RecipeDirectoryFragment();
        // and add it using the fragment manager (getSupportFragmentManager()) and beginTransaction() on it
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .add(R.id.fragment_recipe_directory,recipeFragment )
//                .commit();


        //todo: https://developer.android.com/training/basics/fragments/communicating

        useDummyJson();

    }

    private void useDummyJson(){
        recipes = new ArrayList<>();
        try {
            JSONArray dummyRecipesArray = new JSONArray(getResources().getString(R.string.recipes_json));
            Recipe recipe;
            for(int i=0; i<dummyRecipesArray.length(); i++){
                recipe = new Recipe();
                JSONObject jsonRecipe = (JSONObject) dummyRecipesArray.get(i);
                recipe.setName(jsonRecipe.getString("name"));
                JSONArray ingredients = jsonRecipe.getJSONArray("ingredients");
                recipe.setIngredients(ingredients);
                recipe.setSteps(jsonRecipe.getJSONArray("steps"));
                recipe.setServings(jsonRecipe.getInt("servings"));
                recipe.setImageURL(jsonRecipe.getString("image"));
                recipes.add(recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    public ArrayList<JSONObject> getRecipes() {
public ArrayList<Recipe> getRecipes() {
    return recipes;
}


//    private void getRecipesFromNetwork(){}
}
