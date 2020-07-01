package com.example.bakingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.ArrayList;

import retrofit2.http.Url;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link player_fragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class player_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private SimpleExoPlayer simpleExoPlayer;
    PlayerView playerView;
    TextView textView;
    Context context;
      int id;
    String url;
    steps s;
    items it ;
    Button button1,button2;
    public player_fragment() {

    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
   **/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void getitems() {
        if (getActivity().getIntent().hasExtra("item")) {
            Bundle i = getActivity().getIntent().getBundleExtra("item");
            it = i.getParcelable("item");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_player_fragment, container, false);

         playerView = root.findViewById(R.id.playerview);
         textView = root.findViewById(R.id.recipedesc);
        button1 = root.findViewById(R.id.button2);
        button2 = root.findViewById(R.id.button3);
            getitems();
        if(s!=null) {
            intialurl();
            setTextView();
        }
        hideButton();
        if(getActivity().findViewById(R.id.step_detail_container) != null){
            button1.setVisibility(View.GONE);
            button2.setVisibility(View.GONE);
        }
        else {
            button2.setOnClickListener(new View.OnClickListener() {
                player_fragment fragment = new player_fragment();

                @Override
                public void onClick(View v) {
                    if (id < it.steps.size()) {
                         {
                            id++;
                            fragment.setid(id);
                            fragment.setStep(it.steps.get(id));
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.player, fragment).commit();
                        }
                    }
                }
            });
            button1.setOnClickListener(new View.OnClickListener() {
                player_fragment fragment = new player_fragment();

                @Override
                public void onClick(View v) {
                    if (id < it.steps.size()) {


                            id--;
                            fragment.setid(id);
                            fragment.setStep(it.steps.get(id));
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.player, fragment).commit();

                    }
                }
            });
        }
      //  Intializeplayer(url);
      // createExoplayer();
        return root;
    }

    public void setStep(steps s){
        this.s = s;
    }
    public void setId(int id){
        this.id = id;
    }
    public void intialurl(){

      this.url =  s.videoURL;
    }
    public void hideButton(){
        if(id == it.steps.size()-1){
            button2.setVisibility(View.GONE);
        }
        if(id == 0){
            button1.setVisibility(View.GONE);
        }

    }

    public void setTextView(){
        textView.setText(s.description);
    }
    public void setid(int id){
        this.id = id;

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
    public void seturl(String url){
        this.url = url;
    }

    public void IntializePlayer(){
        if(simpleExoPlayer == null){
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),
                    new DefaultTrackSelector(),new DefaultLoadControl());
            playerView.setPlayer(simpleExoPlayer);
        }
        MediaSource mediaSource = buildMediaSource(Uri.parse(url));
        simpleExoPlayer.prepare(mediaSource,false,false);
        simpleExoPlayer.setPlayWhenReady(true);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(), "exoplayer-codelab");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }
    private void releasePlayer(){
        if(simpleExoPlayer!= null){
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        IntializePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
       IntializePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();

    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }
}