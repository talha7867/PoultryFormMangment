package com.mttech.poultryform.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.mttech.poultryform.Adapter.MedcineFlocksAdapter;
import com.mttech.poultryform.Database.DataBaseHelper;
import com.mttech.poultryform.R;

import java.util.ArrayList;
import java.util.List;

public class MedicineFlocksForm extends AppCompatActivity {
    RecyclerView recyclerView;
    Dialog dialog;
    ImageButton txtBTN;
    MedcineFlocksAdapter medcineFlocksAdapter;
    List<String> med_name;
    List<String> floc_name;
    Spinner flock_spinner,med_spinner;
    DataBaseHelper dataBaseHelper;
    EditText quan;
    Button conusme;
    ArrayList<String> Medicine,FLocks,Quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_flocks_form);

        recyclerView = findViewById(R.id.recyler_view);
        txtBTN = findViewById(R.id.textBtn);
        dialog = new Dialog(this,R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_med_flock_dialouge);
        flock_spinner = dialog.findViewById(R.id.flock_spinner);
        med_spinner = dialog.findViewById(R.id.med_spinner);
        quan = dialog.findViewById(R.id.quantity);
        conusme = dialog.findViewById(R.id.conume);
        dataBaseHelper = new DataBaseHelper(this);
        med_name = new ArrayList<>();
        Medicine = new ArrayList<>();
        floc_name = new ArrayList<>();
        FLocks = new ArrayList<>();
        Quantity = new ArrayList<>();

        txtBTN.setOnClickListener(view -> {

            dialog.show();
        });
        conusme.setOnClickListener(view -> {
            String Med = med_spinner.getSelectedItem().toString();
            String FLoc = flock_spinner.getSelectedItem().toString();
            String Quan = quan.getText().toString();
            if (dataBaseHelper.insert_flocksMedcine(Quan,FLoc,Med)){
                dialog.dismiss();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }

        });


        displayMedicine();
        displayFlocks();
        displayMed_Flocks();

    }
    public void displayMedicine(){
        Cursor res = dataBaseHelper.getAllMedicine();
        if (!(res.getCount()==0)){
            med_name.clear();
            while (res.moveToNext()){
                med_name.add(res.getString(1));
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MedicineFlocksForm.this, android.R.layout.simple_spinner_item, med_name);
            dataAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            med_spinner.setAdapter(dataAdapter);
            med_spinner.getSelectedItem().toString();

        }

    }
    public void displayFlocks(){
        Cursor res = dataBaseHelper.getAllFlocks();
        if (!(res.getCount()==0)){
            floc_name.clear();
            while (res.moveToNext()){
                floc_name.add(res.getString(1));
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MedicineFlocksForm.this, android.R.layout.simple_spinner_item, floc_name);
            dataAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            flock_spinner.setAdapter(dataAdapter);
            flock_spinner.getSelectedItem().toString();

        }
    }
    public  void displayMed_Flocks(){
        Cursor res = dataBaseHelper.getflocks_med();
        if (!(res.getCount()==0)){
            Medicine.clear();
            FLocks.clear();
            Quantity.clear();
            while (res.moveToNext()){
                Quantity.add(res.getString(1));
                FLocks.add(res.getString(2));
                Medicine.add(res.getString(3));
            }
            medcineFlocksAdapter = new MedcineFlocksAdapter(this,Quantity,FLocks,Medicine);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(medcineFlocksAdapter);

        }
    }
}