package com.example.bakingapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.widget.RemoteViewsService;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class provider extends IntentService {
    public static final String ACTION =
            "com.example.android.mygarden.action.water_plants";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private static Context contexts;
    String ingre;
    private static int ids;
    private static List<ingredientss> ingredientssess;
    private static ArrayList<items> itemss;
    private static String recipenames;
    public provider() {
        super("provider");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("helloks","sullicheku");
        if(intent != null){
           String action =  intent.getAction();
           if(action.equals("com.example.android.bakingapp.action.update")){
               update();
           }
        }
    }

    private void update() {
        Log.e("helosss","sulli");

         String ingre = "";
         for(int i = 0;i<ingredientssess.size();i++ ){
           ingre +=   ingredientssess.get(i).ingredient;
         }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int []appwidgetids = appWidgetManager.getAppWidgetIds(new ComponentName(this,provider.class));
        NewAppWidget.updateAppWidget(contexts,appWidgetManager,appwidgetids,ingre,recipenames,ids,itemss);
    }

    public static void intialize(Context context,int id,List<ingredientss> ingredientsses,ArrayList<items> items,String recipename){
        ingredientssess = ingredientsses;
        ids = id;
        itemss = items;
        recipenames = recipename;
        contexts = context;
        Intent intent = new Intent(contexts,provider.class);
        intent.setAction("com.example.android.bakingapp.action.update");
        context.startService(intent);

    }
}
