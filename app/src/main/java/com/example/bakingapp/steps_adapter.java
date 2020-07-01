package com.example.bakingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class steps_adapter  extends RecyclerView.Adapter<steps_adapter.ViewHolder> {
    Context context;
    ArrayList<steps> steps;
    ListItemClickListneritem listItemClickListneritem;

    public steps_adapter(Context context,ListItemClickListneritem listItemClickListneritem){
        this.listItemClickListneritem = listItemClickListneritem;
        this.context =context;
    }
    public interface ListItemClickListneritem{
         void stepclick(int id, String description,String url,ArrayList<steps> size);
    }
    @NonNull
    @Override
    public steps_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.steps_cardview,parent,false);
        return new ViewHolder(root);
    }
    public void setdata(ArrayList<steps> stepsList){
        steps = stepsList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull steps_adapter.ViewHolder holder, int position) {
        steps step  = steps.get(position);
        holder.textView.setText(String.valueOf(step.id));
        holder.textView1.setText(step.shortDescription);
    }

    @Override
    public int getItemCount() {
        if(steps == null){
            return  0;
        }else {
            return steps.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        TextView textView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.stepno);
            textView1 = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int id= steps.get(getAdapterPosition()).id;
            String url = steps.get(getAdapterPosition()).videoURL;
            String description = steps.get(getAdapterPosition()).description;
            listItemClickListneritem.stepclick(id,url,description,steps);
        }
    }
}
