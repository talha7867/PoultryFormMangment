package com.mttech.poultryform.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mttech.poultryform.Adapter.BreaderAdapter;
import com.mttech.poultryform.Adapter.FlocksFormAdapter;
import com.mttech.poultryform.Database.DataBaseHelper;
import com.mttech.poultryform.R;

import java.util.ArrayList;

public class FlocksForm extends AppCompatActivity {
    Dialog dialog;
    EditText flock_name, cage_no,purchase_date,hatch_date,cage_date,vacc_date;
    Button add_flocks;
    RecyclerView recyclerView;
    ImageButton txtBTN;
    FlocksFormAdapter flocksFormAdapter;
    ArrayList<String> Flock_Name, Cage_Number,Purchase_Date, Hatch_Date,Clean_Date ;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flocks_form);
        txtBTN = findViewById(R.id.textBtn);
        dialog = new Dialog(this,R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_flocks_dialouge);
        flock_name = dialog.findViewById(R.id.flock_name);
        cage_no = dialog.findViewById(R.id.cage_no);
        purchase_date = dialog.findViewById(R.id.purchase_date);
        hatch_date = dialog.findViewById(R.id.hatch_date);
        cage_date = dialog.findViewById(R.id.clean_date);
        vacc_date = dialog.findViewById(R.id.vacc_Date);
        add_flocks = dialog.findViewById(R.id.add_flock);
        recyclerView = findViewById(R.id.recyler_view);

        dataBaseHelper = new DataBaseHelper(this);

        Flock_Name = new ArrayList<>();
        Cage_Number = new ArrayList<>();
        Purchase_Date = new ArrayList<>();
        Hatch_Date = new ArrayList<>();
        Clean_Date = new ArrayList<>();
        displayData();





        txtBTN.setOnClickListener(view -> {
            dialog.show();
        });
        add_flocks.setOnClickListener(view -> {
            int cage_Number = Integer.parseInt(cage_no.getText().toString());
            String Flock_Name = flock_name.getText().toString();
            String Purchase_Date = purchase_date.getText().toString();
            String Hatch_Date = hatch_date.getText().toString();
            String Cage_Clean = cage_date.getText().toString();
            if (!Flock_Name.isEmpty()){

                if (dataBaseHelper.insert_floks(Flock_Name,cage_Number,Purchase_Date,Hatch_Date,Cage_Clean," 01/12/2022 ")){
                    Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
            }
        });


    }


    private void displayData(){
        Cursor res = dataBaseHelper.getAllFlocks();
        if (!(res.getCount() == 0)) {
            Flock_Name.clear();
            Cage_Number.clear();
            Purchase_Date.clear();
            Hatch_Date.clear();
            Clean_Date.clear();
            while (res.moveToNext()) {
                Flock_Name.add(res.getString(1));
                Cage_Number.add(res.getString(2));
                Purchase_Date.add(res.getString(3));
                Hatch_Date.add(res.getString(4));
                Clean_Date.add(res.getString(5));
            }
            flocksFormAdapter = new FlocksFormAdapter(this,Flock_Name,Cage_Number,Purchase_Date,Hatch_Date,Clean_Date);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(flocksFormAdapter);

        }
    }


}