package com.example.bakingapp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipeService  {

    @GET(value = "{baking}")
    Call<ArrayList<items>> recipesCall(@Path("baking") String baking);
}
