package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.SelectedRecipeActivity;
import com.example.android.bakingapp.models.Recipe;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RecipeDirectoryAdapter extends RecyclerView.Adapter<RecipeDirectoryAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> recipes;



public RecipeDirectoryAdapter(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }


    @Override
    public RecipeDirectoryAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.recipe_card, parent, false);

        RecipeViewHolder vh = new RecipeViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        // - get element at this position
        // - replace the contents of the view with that element


        Recipe recipe = recipes.get(position);
        String name = recipe.getName();
//        String name = "";
//        int index = position % recipes.size();
//        try {
//            name = recipes.get(index).getString("name");
//        name = recipe.getString("name");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
////        holder.mTextView.setText(name + "\n" + element); //todo: do not concatenate
        holder.mTextView.setText(name);
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;


        private RecipeViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.recipe_title);
            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    int position = getAdapterPosition();
                    Recipe recipe = recipes.get(position);
                    if (recipe != null) {
                        Context context = v.getContext();
                        Intent selectedRecipeIntent = new Intent(context, SelectedRecipeActivity.class);
                        selectedRecipeIntent.putExtra("CLICKED_RECIPE", recipe);
                        context.startActivity(selectedRecipeIntent);
                    }
                }
            });
        }
    }

}
