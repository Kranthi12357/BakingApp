package com.example.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

   static String ingredients_widget;
    static String recipename_widget;
  static   ArrayList<items> itemm;
    static int ide;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int[] appWidgetId, String ingre, String recipename, int id, ArrayList<items> items) {


        ingredients_widget = ingre;
        recipename_widget = recipename;
        ide = id;
        itemm = items;
        Intent intent = new Intent(context,detail_recipe.class);
        Bundle b = new Bundle();
        b.putParcelable("object",  b);
        intent.putExtra("id",id-1);
        intent.putExtra("object",b);

       // PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_texts,ingre);
        views.setTextViewText(R.id.recipe_name,recipename);
     //   views.setOnClickPendingIntent(R.id.name,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, new int[] {appWidgetId},ingredients_widget,recipename_widget,ide,itemm);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static void upadatetilte(Context context,AppWidgetManager appWidgetManager , int widgetid){



    }


    }




