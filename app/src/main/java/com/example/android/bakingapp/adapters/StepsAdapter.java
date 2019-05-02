package com.example.android.bakingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activities.SelectedRecipeActivity;
import com.example.android.bakingapp.models.RecipeStep;

import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ItemViewHolder> {

    private ArrayList<RecipeStep> stepList;

//    private SharedViewModel sharedViewModel; //todo: use a bundle??

    private SelectedRecipeActivity activity;


    public StepsAdapter(FragmentActivity activity, ArrayList<RecipeStep> itemList) {
        stepList = itemList;

        // todo: if we don't use viewModel, remove activity from the list of parameters
//        sharedViewModel = ViewModelProviders.of(activity).get(SharedViewModel.class);

        this.activity = (SelectedRecipeActivity)activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.step, parent, false);

        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        final RecipeStep recipeStep = stepList.get(position);
        String content = "";

        if(recipeStep.getVideoURL().length() == 0){
            holder.mImageView.setVisibility(View.INVISIBLE);
        }
        content = recipeStep.getShortDescription();

        if(content.length() > 0){
            holder.mTextView.setText(content);
        }


        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if(recipeStep.getVideoURL().length() > 0){ // only react if videourl is not null
                    // todo: replace fragment if phone or show info if tablet

////                        sharedViewModel.setStep(recipeStep);
//                        Bundle recipeBundle = new Bundle();
//                        recipeBundle.putParcelable("SELECTED_STEP", recipeStep);
//                        recipeDetailsFragment.setArguments(recipeBundle);

                    activity.toggleFragments(recipeStep);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private ImageView mImageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
            mImageView = itemView.findViewById(R.id.bullet_icon);
        }
    }
}
