package com.example.bakingapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Ingredients_adapter extends RecyclerView.Adapter<Ingredients_adapter.ViewHolder> {

    Context context;
    List<ingredientss> data;
    public Ingredients_adapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public Ingredients_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.ingredients_card,parent,false);

        return new ViewHolder(root);
    }

    public void setdata(List<ingredientss> data){
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull Ingredients_adapter.ViewHolder holder, int position) {
       ingredientss ingredients =  data.get(position);
       holder.textView1.setText(String.valueOf(ingredients.quantity));
       holder.textView2.setText(ingredients.measure);
       holder.textView3.setText(ingredients.ingredient);

    }

    @Override
    public int getItemCount() {
        if(data ==null){
            return  0;
        }
        else {
            Log.e("len",String.valueOf(data.size()));
            return data.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2,textView3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.quantity);
            textView2 = itemView.findViewById(R.id.measure);
            textView3 = itemView.findViewById(R.id.ingredient);
        }
    }
}
