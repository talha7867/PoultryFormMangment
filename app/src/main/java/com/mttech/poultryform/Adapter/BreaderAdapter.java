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

public class BreaderAdapter extends RecyclerView.Adapter<BreaderAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> breader_id, breader_name, breader_number;

    public BreaderAdapter(Context context, ArrayList<String> breader_id, ArrayList<String> breader_name, ArrayList<String> breader_number) {
        this.context = context;
        this.breader_id = breader_id;
        this.breader_name = breader_name;
        this.breader_number = breader_number;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.breader_items,parent,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(breader_id.get(position)));
        holder.name.setText(breader_name.get(position));
        holder.number.setText(String.valueOf(breader_number.get(position)));


    }


    @Override
    public int getItemCount() {
        return breader_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,number;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.breader_id);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.no);



        }
    }
}
