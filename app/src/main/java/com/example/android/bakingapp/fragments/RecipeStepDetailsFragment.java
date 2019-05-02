package com.example.android.bakingapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.RecipeStep;

public class RecipeStepDetailsFragment extends Fragment {

    private RecipeStep selectedStep;

    private TextView tvTitle;
    private TextView tvDescription;



    public RecipeStepDetailsFragment() {
        selectedStep = new RecipeStep("", "", "", "");
    }



    public void setStep(RecipeStep recipeStep) {
        selectedStep = recipeStep;

        updateUI(); // todo: use the viewmodel?
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_step_details, container, false);

        tvTitle = rootView.findViewById(R.id.tv_title);
        tvDescription = rootView.findViewById(R.id.tv_description);

//        Bundle recipeBundle = getArguments();
//        if(recipeBundle != null) {
//            selectedStep = recipeBundle.getParcelable("SELECTED_STEP");
//        }

        updateUI();

        return rootView;
    }

    private void updateUI() {
        tvTitle.setText(selectedStep.getShortDescription());
        tvDescription.setText(selectedStep.getDescription());
    }
}
