package com.example.bakingapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class fragmentsAdapter extends FragmentStatePagerAdapter {

    public int nooftabs;
    Context context;
    int id;
    public fragmentsAdapter(@NonNull FragmentManager fm, int nooftabs, Context context,int id) {
        super(fm);
        this.context = context;
        this.id = id;
        this.nooftabs = nooftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new IngredientsFragment();
            case 1: return new StepsFragment();
            default:return null;
        }
    }
    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0: return "Ingredients";
            case 1:return "Steps";
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return nooftabs;
    }
}
