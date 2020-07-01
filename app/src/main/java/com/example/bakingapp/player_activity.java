package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class player_activity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_activity);
        int id = 0;
        items ij = null;
        String description = null;
        String url = null;
        Bundle i = getIntent().getBundleExtra("item");
        ij = i.getParcelable("item");
        Log.e("ij", ij.toString());
        if (getIntent().hasExtra("id") && getIntent().hasExtra("description") && getIntent().hasExtra("url")) {
            id = getIntent().getIntExtra("id", 0);
            description = getIntent().getStringExtra("description");
            url = getIntent().getStringExtra("url");

        }
        if (savedInstanceState == null) {
            player_fragment fragment = new player_fragment();
            fragment.setid(id);
            fragment.seturl(url);
            fragment.setStep(ij.steps.get(id));
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().add(R.id.player, fragment).commit();
        }
    }


}