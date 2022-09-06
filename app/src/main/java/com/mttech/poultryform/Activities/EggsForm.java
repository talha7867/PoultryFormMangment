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
import android.widget.Toast;

import com.mttech.poultryform.Adapter.EggsFormAdapter;
import com.mttech.poultryform.Adapter.FlocksFormAdapter;
import com.mttech.poultryform.Database.DataBaseHelper;
import com.mttech.poultryform.R;

import java.util.ArrayList;
import java.util.List;

public class EggsForm extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton txtBTN;
    Dialog dialog;
     Spinner spinner;
     EditText date,number_egg,egg_condition;
     Button add_egg;
     List<String> breader_name;
     DataBaseHelper dataBaseHelper;
     EggsFormAdapter eggsFormAdapter;
     ArrayList<String> bed_nam,eeg_date,number_eeg,condition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eggs_form);

        recyclerView = findViewById(R.id.recyler_view);
        txtBTN = findViewById(R.id.textBtn);
        dialog = new Dialog(this,R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_egg_dialouge);
        spinner = dialog.findViewById(R.id.spinner);
        date = dialog.findViewById(R.id.date);
        number_egg = dialog.findViewById(R.id.number_egg);
        egg_condition = dialog.findViewById(R.id.egg_condition);
        add_egg = dialog.findViewById(R.id.add_egg);
        breader_name = new ArrayList<>();
        bed_nam = new ArrayList<>();
        eeg_date = new ArrayList<>();
        number_eeg = new ArrayList<>();
        condition = new ArrayList<>();
//        breader_name = new ArrayAdapter<String>(EggsForm.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        dataBaseHelper = new DataBaseHelper(this);

        txtBTN.setOnClickListener(view -> {

            dialog.show();
        });

        add_egg.setOnClickListener(view -> {
            String breader=  spinner.getSelectedItem().toString();
            String Dates = date.getText().toString();
            String Eggs = number_egg.getText().toString();
            String Conditons = egg_condition.getText().toString();

            if (dataBaseHelper.insert_eggs(Dates,Eggs,Conditons,breader)){
                Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }




        });



         displayBreader();
        displayEggsForm();


    }

    public void displayBreader(){
        Cursor res = dataBaseHelper.getAllBreader();
        if (!(res.getCount()==0)){
            breader_name.clear();
            while (res.moveToNext()){
                breader_name.add(res.getString(1));
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(EggsForm.this, android.R.layout.simple_spinner_item, breader_name);
            dataAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
            spinner.getSelectedItem().toString();

        }

    }

    public void displayEggsForm(){
        Cursor res = dataBaseHelper.getAllEggs();
        if (!(res.getCount() == 0)) {
            eeg_date.clear();
            number_eeg.clear();
            condition.clear();
            bed_nam.clear();

            while (res.moveToNext()) {
                eeg_date.add(res.getString(1));
                number_eeg.add(res.getString(2));
                condition.add(res.getString(3));
                bed_nam.add(res.getString(4));



            }
            eggsFormAdapter = new EggsFormAdapter(this,eeg_date,number_eeg,condition,bed_nam);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(eggsFormAdapter);

        }
    }

}