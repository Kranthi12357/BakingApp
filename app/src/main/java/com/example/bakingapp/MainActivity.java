package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BakingAdapter.ListItemClickListner {

    private static final String OBJECT = "object" ;
    RecyclerView recyclerView;
    BakingAdapter adapter;
    GridLayoutManager gridLayoutManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new BakingAdapter(this,this::recipeclick);
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        RecipeService recipeService = ServiceBuilder.BuildService(RecipeService.class);
        Call<ArrayList<items>> recipesCall = recipeService.recipesCall("baking.json");

        Log.e("hello wrold","yeah");
        recipesCall.enqueue(new Callback<ArrayList<items>>() {
            @Override
            public void onResponse(Call<ArrayList<items>> call, Response<ArrayList<items>> response) {
                ArrayList<items> recipes = response.body();
                Log.e("recipes",recipes.toString());
                adapter.setdata(recipes);
            }

            @Override
            public void onFailure(Call<ArrayList<items>> call, Throwable t) {
                Log.e("throwable",t.toString());
                Log.e("call",call.toString());
                }
        });
    }

    @Override
    public void recipeclick(int id,items it) {
        Bundle b = new Bundle();
        b.putParcelable(OBJECT,  it);
        Intent intent = new Intent(this,detail_recipe.class);
        intent.putExtra("id",id-1);
        intent.putExtra("object",b);
        startActivity(intent);
    }




}