package com.example.android.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    ArrayList<String> mDataset;//todo: cambiar

    public RecipeAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.recipe, parent, false);

        RecipeViewHolder vh = new RecipeViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        String element = mDataset.get(position);
        holder.mTextView.setText(element);

    }


    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        //todo: add here all the UI elements of the recipe


        private RecipeViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.recipe_title);
            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    // item clicked
                }
            });
        }
    }

}
