package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.SelectedRecipeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RecipeDirectoryAdapter extends RecyclerView.Adapter<RecipeDirectoryAdapter.RecipeViewHolder> {

    private ArrayList<JSONObject> recipes;
    private ArrayList<String> mDataset;//todo: cambiar

    public RecipeDirectoryAdapter(ArrayList<String> myDataset, ArrayList<JSONObject> recipes) {
        mDataset = myDataset;
        this.recipes = recipes;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
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
        // - get element from dataset at this position
        // - replace the contents of the view with that element

        String element = mDataset.get(position);
        String name = "";
        int index = position % recipes.size();
        try {
            name = recipes.get(index).getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.mTextView.setText(name + "\n" + element); //todo: do not concatenate

    }


    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        //todo: add here all the UI elements of the recipe


        private RecipeViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.recipe_title);
            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Context context = v.getContext();
                    Intent selectedRecipeIntent = new Intent(context, SelectedRecipeActivity.class);
                    context.startActivity(selectedRecipeIntent);
                }
            });
        }
    }

}
