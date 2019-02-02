package com.example.android.bakingapp.activities;

import android.os.Bundle;

import com.example.android.bakingapp.R;

import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class SelectedRecipeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo: setTitle using selected recipe
        setContentView(R.layout.activity_selected_recipe);

    }
}
