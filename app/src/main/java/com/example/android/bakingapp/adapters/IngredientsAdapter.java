package com.example.android.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {


    private ArrayList ingredients;

    public IngredientsAdapter(ArrayList ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.ingredient, parent, false);

        IngredientViewHolder vh = new IngredientViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        JSONObject element = (JSONObject) ingredients.get(position);


        int quantity = 0;
        String measure = "";
        String ingredient = "";
        try {
            quantity = element.getInt("quantity");
            measure = element.getString("measure");
            ingredient = element.getString("ingredient");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.mTextView.setText(String.format("%d %s %s", quantity, measure, ingredient));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.ingredient);
        }
    }
}
