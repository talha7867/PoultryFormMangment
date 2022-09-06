package com.mttech.poultryform.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mttech.poultryform.R;

import java.util.ArrayList;

public class EggsFormAdapter extends RecyclerView.Adapter<EggsFormAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> breader_name, date,number_egg,egg_condition;

    public EggsFormAdapter(Context context, ArrayList<String> breader_name, ArrayList<String> date, ArrayList<String> number_egg, ArrayList<String> egg_condition) {
        this.context = context;
        this.breader_name = breader_name;
        this.date = date;
        this.number_egg = number_egg;
        this.egg_condition = egg_condition;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.eggs_items,parent,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bed_name.setText(breader_name.get(position));
        holder.condition.setText(egg_condition.get(position));
        holder.date.setText(date.get(position));
        holder.no_eggs.setText(number_egg.get(position));

    }

    @Override
    public int getItemCount() {
        return number_egg.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bed_name,condition,no_eggs,date;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bed_name = itemView.findViewById(R.id.bed_name);
            condition = itemView.findViewById(R.id.condition);
            no_eggs = itemView.findViewById(R.id.egg_no);
            date = itemView.findViewById(R.id.date);
        }
    }
}
