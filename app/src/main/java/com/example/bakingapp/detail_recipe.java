package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class detail_recipe extends AppCompatActivity  implements StepsFragment.OnstepClickListner {

    items it;
    int id;
    private boolean mpane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);
        if(getIntent().hasExtra("id")){
            id = getIntent().getIntExtra("id",0);
            Bundle b = getIntent().getBundleExtra("object");
            it = b.getParcelable("object");

        }
        if( findViewById(R.id.step_detail_container)!= null) {
            mpane = true;
            if (savedInstanceState == null) {
                    player_fragment pf = new player_fragment();
                pf.setid(id);
                pf.seturl(it.steps.get(0).videoURL);
                pf.setStep(it.steps.get(id));
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().add(R.id.step_detail_container,pf).commit();
            }
        }
        else {
            ViewPager viewPager = findViewById(R.id.viewpager);
            mpane = false;
            fragmentsAdapter fa = new fragmentsAdapter(getSupportFragmentManager(), 2, getApplicationContext(), id);
            viewPager.setAdapter(fa);

            TabLayout tabLayout = findViewById(R.id.tablayout);
            tabLayout.setupWithViewPager(viewPager);

        }

    }
    @Override
    public void onstepclick(int ide, String url, String description, ArrayList<steps> size) {
        if (!mpane) {
            Bundle b = new Bundle();
            b.putParcelable("item", it);
            Intent intent = new Intent(this, player_activity.class);
            intent.putExtra("description", description);
            intent.putExtra("id", ide);
            intent.putExtra("item", b);
            intent.putExtra("url", url);
            Log.e("sulli", it.steps.toString());
            startActivity(intent);
        }
        else {
            player_fragment pf = new player_fragment();
            pf.setid(ide);
            pf.seturl(url);
            pf.setStep(it.steps.get(ide));
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.step_detail_container,pf).commit();
        }
    }
}