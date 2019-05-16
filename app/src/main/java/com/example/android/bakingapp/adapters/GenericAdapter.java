package com.example.android.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.BR;
import com.example.android.bakingapp.models.Recipe;
import com.example.android.bakingapp.models.RecipeIngredient;
import com.example.android.bakingapp.models.RecipeStep;
import com.example.android.bakingapp.viewmodel.RecyclerViewAdapterViewModel;

import java.util.ArrayList;
import java.util.List;

public class GenericAdapter<T> extends RecyclerView.Adapter<GenericAdapter.GenericViewHolder> {

    private ArrayList/*<T>*/ items;
    private OnListItemViewClickListener onListItemViewClickListener;
    private int layoutId;


//    public GenericAdapter(@LayoutRes int layoutId, ArrayList<T> listOfItems) {
//        this.layoutId = layoutId;
//        this.items = listOfItems;
//    }
    public GenericAdapter(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        this.items = new ArrayList<>();
    }



    public void addItems(List items) {
        if(items != null) {
            this.items.clear();
            this.items.addAll(items);
            this.notifyDataSetChanged();
        }
    }




    @NonNull
    @Override
    public GenericViewHolder/*<T>*/ onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewDataBinding binding;
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false);

        //todo: we are sending the same object kinda
        GenericViewHolder holder = new GenericViewHolder(/*binding.getRoot(), */binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder/*<T>*/ holder, int position) {

        RecyclerViewAdapterViewModel itemViewModel = (RecyclerViewAdapterViewModel)items.get(position);
        itemViewModel.adapterPosition = position;
        itemViewModel.onListItemViewClickListener = this.onListItemViewClickListener;

        holder.bind(itemViewModel);
    }





    @Override
    public int getItemCount() {
        if(items != null){
            return items.size();
        }
        return 0;
    }


    public void setOnListItemViewClickListener(OnListItemViewClickListener onListItemViewClickListener){
        this.onListItemViewClickListener = onListItemViewClickListener;
    }



    static class GenericViewHolder/*<T>*/ extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;


        public GenericViewHolder(/*@NonNull View itemView, */ViewDataBinding b) {
            super(b.getRoot());
            binding = b;
        }

        private void bind(RecyclerViewAdapterViewModel itemViewModel) {
            if(itemViewModel instanceof Recipe) {
                binding.setVariable(BR.recipe, itemViewModel);
            } else if(itemViewModel instanceof RecipeIngredient) {
                binding.setVariable(BR.recipeIngredient, itemViewModel);
            } else if(itemViewModel instanceof RecipeStep) {
                binding.setVariable(BR.step, itemViewModel);
            }
            binding.executePendingBindings();
        }

    }


    public interface OnListItemViewClickListener{
        void onClick(View view, int position);
    }
}
