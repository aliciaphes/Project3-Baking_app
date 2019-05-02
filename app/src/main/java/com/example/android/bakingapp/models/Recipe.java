package com.example.android.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Recipe implements Parcelable {

    //todo: id?
    private String name;
    private int servings;
    private String imageURL;
    private ArrayList<String> ingredients;

//    private ArrayList<String> steps; //will contain the stringified json
    private ArrayList<RecipeStep> steps;


    public Recipe() {
        name = "";
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
    }

    protected Recipe(Parcel in) {
        name = in.readString();
//        ingredients = (ArrayList<String>)in.readArrayList(ArrayList.class.getClassLoader());
        ingredients = in.readArrayList(String.class.getClassLoader());
//        steps = (ArrayList<RecipeStep>)in.readArrayList(ArrayList.class.getClassLoader());
        steps = in.readArrayList(RecipeStep.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeList(ingredients);
        parcel.writeList(steps);
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(JSONArray ingredients) {

        int quantity;
        String measure;
        String ingredient;
        String finalIngredient;

        for(int i=0; i<ingredients.length(); i++) {
            try {
                JSONObject ingredientObject = ingredients.getJSONObject(i);
                quantity = ingredientObject.getInt("quantity");
                measure = ingredientObject.getString("measure");
                ingredient = ingredientObject.getString("ingredient");
//            String.format("%d %s %s", quantity, measure, ingredient);
                finalIngredient = quantity + " " + measure + " " + ingredient;
                this.ingredients.add(finalIngredient);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSteps(JSONArray steps) {
        for(int i=0; i<steps.length(); i++) {
            try {
                JSONObject step = (JSONObject) steps.get(i);
                String shortDescription = step.getString("shortDescription");
                String description = step.getString("description");
                String videoURL = step.getString("videoURL");
                String thumbnailURL = step.getString("thumbnailURL");
                RecipeStep recipeStep = new RecipeStep(shortDescription, description, videoURL, thumbnailURL);
                this.steps.add(recipeStep);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public ArrayList<RecipeStep> getSteps() {
        return steps;
    }




    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };


}
