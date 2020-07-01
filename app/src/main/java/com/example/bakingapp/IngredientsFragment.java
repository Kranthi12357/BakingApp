package com.example.bakingapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class IngredientsFragment extends Fragment {
    RecyclerView recyclerView;
    Ingredients_adapter adapter;
    LinearLayoutManager linearLayoutManager;
    Context context;
    int id;
    items it;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public IngredientsFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_ingredients, container, false);

        recyclerView = root.findViewById(R.id.fragment_recyle);
        adapter = new Ingredients_adapter(getContext());
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        if(getActivity().getIntent().hasExtra("id")){
            id = getActivity().getIntent().getIntExtra("id",0);
            Bundle b = getActivity().getIntent().getBundleExtra("object");
            it = b.getParcelable("object");

        }
        ingredients(id);
        return root;
    }

    public void ingredients(int id){
        RecipeService recipeService = ServiceBuilder.BuildService(RecipeService.class);
        Call<ArrayList<items>> recipesCall = recipeService.recipesCall("baking.json");
        recipesCall.enqueue(new Callback<ArrayList<items>>() {
            @Override
            public void onResponse(Call<ArrayList<items>> call, Response<ArrayList<items>> response) {
                List<items> recipes = response.body();
                Log.e("hello","hello");
               List<ingredientss> ingredients = recipes.get(id).ingredients;
                adapter.setdata(ingredients);

            }

            @Override
            public void onFailure(Call<ArrayList<items>> call, Throwable t) {

            }
        });
    }


}