package com.example.android.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    String[] mDataset;//todo: cambiar

    public RecipeAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
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

        String element = mDataset[position];
        holder.mTextView.setText(element);

    }


    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        //todo: add here all the UI elements of the recipe


        private RecipeViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.recipe_title);
        }
    }

}
