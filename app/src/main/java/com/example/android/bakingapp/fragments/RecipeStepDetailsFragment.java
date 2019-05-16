package com.example.android.bakingapp.fragments;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.models.RecipeStep;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class RecipeStepDetailsFragment extends Fragment {

    private RecipeStep selectedStep;

    private TextView tvTitle;
    private TextView tvDescription;

    private SimpleExoPlayer exoPlayer;
    private PlayerView playerView;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;


    public RecipeStepDetailsFragment() {
        selectedStep = new RecipeStep("", "", "", "");
    }



    public void setStep(RecipeStep recipeStep) {
        selectedStep = recipeStep;

//        updateUI(); // todo: use the viewmodel?
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_step_details, container, false);

        tvTitle = rootView.findViewById(R.id.tv_title);
        tvDescription = rootView.findViewById(R.id.tv_description);

        playerView = rootView.findViewById(R.id.player_view);


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


    public void initializePlayer() { // todo: set to private?
        if (exoPlayer == null) {


//        MediaSource mediaSource = buildMediaSource();
//        exoPlayer.prepare(mediaSource, true, false);
            exoPlayer = ExoPlayerFactory.newSimpleInstance(
                    getActivity(),
                    new DefaultRenderersFactory(getActivity()),
                    new DefaultTrackSelector(),
                    new DefaultLoadControl());

//            playerView.setPlayer(exoPlayer);

//            exoPlayer.setPlayWhenReady(playWhenReady);
//            exoPlayer.seekTo(currentWindow, playbackPosition);
//            Uri uri = Uri.parse(selectedStep.getVideoURL());
//            if(uri.toString().length() > 0){
//                MediaSource mediaSource = buildMediaSource(uri);
//                exoPlayer.prepare(mediaSource, true, false);
//            }
        }
    }

    public void preparePlayer() {
        if (exoPlayer != null) {

            Uri uri = Uri.parse(selectedStep.getVideoURL());
            if (uri.toString().length() > 0) {
                uri = Uri.parse("asset:///4-press-crumbs-in-pie-plate-creampie.mp4"); //todo: temporary
                MediaSource mediaSource = buildMediaSource(uri);
                exoPlayer.prepare(mediaSource, true, false);

                playerView.setPlayer(exoPlayer);

                exoPlayer.setPlayWhenReady(true); //playWhenReady

                exoPlayer.addListener(new Player.EventListener() {
//                    @Override
//                    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
//                        switch (playbackState) {
//                            case Player.STATE_BUFFERING:
//                                break;
//                            case Player.STATE_ENDED:
//                                break;
//                            case Player.STATE_IDLE:
//                                break;
//                            case Player.STATE_READY:
//                                break;
//                            default:
//                                break;
//                        }
//                    }

                    @Override
                    public void onPlayerError(ExoPlaybackException error) {
                        String TAG = "tag";
                        switch (error.type) {
                            case ExoPlaybackException.TYPE_SOURCE:
                                Log.e(TAG, "TYPE_SOURCE: " + error.getSourceException().getMessage());
                                break;

                            case ExoPlaybackException.TYPE_RENDERER:
                                Log.e(TAG, "TYPE_RENDERER: " + error.getRendererException().getMessage());
                                break;

                            case ExoPlaybackException.TYPE_UNEXPECTED:
                                Log.e(TAG, "TYPE_UNEXPECTED: " + error.getUnexpectedException().getMessage());
                                break;
                        }
                    }
                });
                exoPlayer.seekTo(currentWindow, playbackPosition);
            }
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
//        return new ExtractorMediaSource.Factory(
//                // todo: restore this when using URL and not local file
////                new DefaultHttpDataSourceFactory(getString(R.string.app_name)))
//                new DefaultDataSourceFactory(getActivity(), getString(R.string.app_name)))
//                .createMediaSource(uri);

        MediaSource videoSource = new ExtractorMediaSource.Factory(
                new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(), getString(R.string.app_name))))
                .createMediaSource(uri);
        return videoSource;
    }




    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
            preparePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || exoPlayer == null)) {
            initializePlayer();
            preparePlayer();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            playbackPosition = exoPlayer.getCurrentPosition();
            currentWindow = exoPlayer.getCurrentWindowIndex();
            playWhenReady = exoPlayer.getPlayWhenReady();
            exoPlayer.release();
            exoPlayer = null;
        }
    }
}
