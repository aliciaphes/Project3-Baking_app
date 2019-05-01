package com.example.android.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {


    private ArrayList itemList;
    private int layout;



    public ListAdapter(ArrayList itemList, int layout) {
        this.itemList = itemList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(layout, parent, false);

        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        String ingredient = (String) itemList.get(position);
        holder.mTextView.setText(ingredient);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
        }
    }
}
