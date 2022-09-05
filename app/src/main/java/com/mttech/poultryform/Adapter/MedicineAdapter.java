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

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> Medicine , Formula, Qunatity, Level;

    public MedicineAdapter(Context context, ArrayList<String> medicine, ArrayList<String> formula, ArrayList<String> qunatity, ArrayList<String> level) {
        this.context = context;
        Medicine = medicine;
        Formula = formula;
        Qunatity = qunatity;
        Level = level;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.medicine_items,parent,false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.Med.setText(Medicine.get(position));
        holder.levl.setText(Level.get(position));
        holder.frmula.setText(Formula.get(position));
        holder.quantity.setText(Qunatity.get(position));

    }


    @Override
    public int getItemCount() {
        return Medicine.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Med,frmula,quantity,levl;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Med = itemView.findViewById(R.id.med_name);
            frmula = itemView.findViewById(R.id.formula);
            quantity = itemView.findViewById(R.id.quantity);
            levl = itemView.findViewById(R.id.level);


        }
    }
}
