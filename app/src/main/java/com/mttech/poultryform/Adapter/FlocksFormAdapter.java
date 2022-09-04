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

public class FlocksFormAdapter extends RecyclerView.Adapter<FlocksFormAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> flock_nam, cage_No,Purchase_date,Hatch_date,Clean_date;

    public FlocksFormAdapter(Context context,  ArrayList<String> flock_nam, ArrayList<String> cage_No, ArrayList<String> purchase_date, ArrayList<String> hatch_date, ArrayList<String> clean_date) {
        this.context = context;

        this.flock_nam = flock_nam;
        this.cage_No = cage_No;
        Purchase_date = purchase_date;
        Hatch_date = hatch_date;
        Clean_date = clean_date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flocks_items,parent,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.flocks_name.setText(flock_nam.get(position));
        holder.cage_no.setText(cage_No.get(position));
        holder.purchase_date.setText(Purchase_date.get(position));
        holder.hatch_date.setText(Hatch_date.get(position));
        holder.clean_date.setText(Clean_date.get(position));


    }


    @Override
    public int getItemCount() {
        return flock_nam.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView flocks_name,cage_no,purchase_date,hatch_date,clean_date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            flocks_name = itemView.findViewById(R.id.flocks_name);
            cage_no = itemView.findViewById(R.id.cage_no);
            purchase_date = itemView.findViewById(R.id.purhase_date);
            hatch_date = itemView.findViewById(R.id.hatch_Date);
            clean_date = itemView.findViewById(R.id.cage_Date);




        }
    }
}
