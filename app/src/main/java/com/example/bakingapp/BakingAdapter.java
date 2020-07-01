package com.example.bakingapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BakingAdapter extends RecyclerView.Adapter<BakingAdapter.ViewHolder> {

ArrayList<items> recipes;
Context context;
ListItemClickListner listItemClickListner;
private int  last = -1;
public interface ListItemClickListner{
    public void recipeclick(int id,items it);
}

public BakingAdapter(Context context,ListItemClickListner listItemClickListner){
    this.listItemClickListner = listItemClickListner;
    this.context = context;
}
public void setdata(ArrayList<items> recipes){
    this.recipes = recipes;
    notifyDataSetChanged();

}

    @NonNull
    @Override
    public BakingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(context).inflate(R.layout.cooking_items,parent,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull BakingAdapter.ViewHolder holder, int position) {
       items recipe =  recipes.get(position);
       holder.textView.setText(recipe.name);
    }

    @Override
    public int getItemCount() {
        if(recipes != null){
            return recipes.size();
        }
        else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int id = recipes.get(getAdapterPosition()).id;
            items it = recipes.get(getAdapterPosition());
            last = getAdapterPosition();
            provider.intialize(context,last +1, (recipes.get(getAdapterPosition()).ingredients),recipes,recipes.get(getAdapterPosition()).name);
           notifyDataSetChanged();
            listItemClickListner.recipeclick(id,it);
        }
    }
}
