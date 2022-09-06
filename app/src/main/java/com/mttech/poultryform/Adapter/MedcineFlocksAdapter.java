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

public class MedcineFlocksAdapter extends RecyclerView.Adapter<MedcineFlocksAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> medicne,flocks,quantity;

    public MedcineFlocksAdapter(Context context, ArrayList<String> medicne, ArrayList<String> flocks, ArrayList<String> quantity) {
        this.context = context;
        this.medicne = medicne;
        this.flocks = flocks;
        this.quantity = quantity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.medflock_items,parent,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.med.setText(medicne.get(position));
        holder.flock.setText(flocks.get(position));
        holder.quan.setText(quantity.get(position));

    }


    @Override
    public int getItemCount() {
        return quantity.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView med,flock,quan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            med = itemView.findViewById(R.id.med_id);
            flock = itemView.findViewById(R.id.flock_id);
            quan = itemView.findViewById(R.id.quantity_id);
        }
    }
}
