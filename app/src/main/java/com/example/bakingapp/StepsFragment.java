package com.example.bakingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StepsFragment extends Fragment implements steps_adapter.ListItemClickListneritem {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager ;
    steps_adapter steps_adapters;
    ArrayList<steps> steps_list;
    int id;
    items it;


    OnstepClickListner onstepClickListner;

    public interface OnstepClickListner {
        void onstepclick(int id,String url,String description,ArrayList<steps> size);
    }
    public StepsFragment(){
       // this.onstepClickListner = onstepClickListner;

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = LayoutInflater.from(getContext()).inflate(R.layout.steps_fragment,container,false);
        recyclerView = root.findViewById(R.id.steprecycle);
         steps_adapters = new steps_adapter(getContext(),this::stepclick);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(steps_adapters);
        recyclerView.setLayoutManager(linearLayoutManager);
        if(getActivity().getIntent().hasExtra("id")){
            id = getActivity().getIntent().getIntExtra("id",0);
            Bundle b = getActivity().getIntent().getBundleExtra("object");
            it = b.getParcelable("object");

        }
        steps( id);

        return root;
    }

    public void steps(int id){
        RecipeService recipeService = ServiceBuilder.BuildService(RecipeService.class);
        Call<ArrayList<items>> recipesCall = recipeService.recipesCall("baking.json");
        recipesCall.enqueue(new Callback<ArrayList<items>>() {
            @Override
            public void onResponse(Call<ArrayList<items>> call, Response<ArrayList<items>> response) {
                List<items> recipes = response.body();
                Log.e("hello","hello");
                List<steps> stepss = recipes.get(id).steps;
                steps_list = new ArrayList<steps>(stepss);
                steps_adapters.setdata(steps_list);
            }

            @Override
            public void onFailure(Call<ArrayList<items>> call, Throwable t) {

            }
        });
    }

    @Override
    public void stepclick(int id,String url ,String description,ArrayList<steps> size) {
        onstepClickListner.onstepclick(id,url,description,size);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            onstepClickListner = (OnstepClickListner) context;
        }
        catch (Exception e){

        }
    }
}
