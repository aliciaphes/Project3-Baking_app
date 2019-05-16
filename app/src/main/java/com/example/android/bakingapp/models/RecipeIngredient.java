package com.example.android.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.bakingapp.viewmodel.RecyclerViewAdapterViewModel;

public class RecipeIngredient extends RecyclerViewAdapterViewModel implements Parcelable {

//    private ArrayList<String> ingredientList;
    private String ingredient;



    public RecipeIngredient() {
//        ingredientList = new ArrayList<>();
        ingredient = "";
    }

    public RecipeIngredient(String ingr) {
        ingredient = ingr;
    }

    protected RecipeIngredient(Parcel in) {
//        ingredientList = in.readArrayList(String.class.getClassLoader());
        ingredient = in.readString();
    }

//    public void add(String ingredient){
//        ingredientList.add(ingredient);
//    }

//    public String get(int index){
//        return ingredientList.get(index);
//    }
    public String getIngredient(){
        return ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeList(ingredientList);
        parcel.writeString(ingredient);
    }


//    public ArrayList<String> getIngredients() {
//        return ingredientList;
//    }


    public static final Creator<RecipeIngredient> CREATOR = new Creator<RecipeIngredient>() {
        @Override
        public RecipeIngredient createFromParcel(Parcel in) {
            return new RecipeIngredient(in);
        }

        @Override
        public RecipeIngredient[] newArray(int size) {
            return new RecipeIngredient[size];
        }
    };


}
