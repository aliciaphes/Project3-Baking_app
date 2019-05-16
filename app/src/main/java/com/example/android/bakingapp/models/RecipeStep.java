package com.example.android.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.example.android.bakingapp.viewmodel.RecyclerViewAdapterViewModel;

public class RecipeStep extends RecyclerViewAdapterViewModel implements Parcelable {

    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;


    public RecipeStep(String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }


    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }



    protected RecipeStep(Parcel in) {
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }


    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, String videoURL) {
        view.setVisibility(videoURL.length() == 0 ? View.INVISIBLE : View.VISIBLE);
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(shortDescription);
        parcel.writeString(description);
        parcel.writeString(videoURL);
        parcel.writeString(thumbnailURL);
    }



    public static final Creator<RecipeStep> CREATOR = new Creator<RecipeStep>() {
        @Override
        public RecipeStep createFromParcel(Parcel in) {
            return new RecipeStep(in);
        }

        @Override
        public RecipeStep[] newArray(int size) {
            return new RecipeStep[size];
        }
    };
}
