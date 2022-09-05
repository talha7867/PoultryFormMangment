package com.mttech.poultryform.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StatFs;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mttech.poultryform.Adapter.MedicineAdapter;
import com.mttech.poultryform.Database.DataBaseHelper;
import com.mttech.poultryform.R;

import java.util.ArrayList;

public class MedicineForm extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton txtBTN;
    Dialog dialog;
    EditText medicine_name, formula,quantity,level;
    Button add_medicine;
    DataBaseHelper dataBaseHelper;
    ArrayList<String> Medicine , Formula, Qunatity, Level;
    MedicineAdapter medicineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_form);
        txtBTN = findViewById(R.id.textBtn);
        recyclerView = findViewById(R.id.recyler_view);
        dialog = new Dialog(this,R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_medicine_dialouge);
        medicine_name = dialog.findViewById(R.id.medicine_name);
        formula = dialog.findViewById(R.id.formula);
        quantity = dialog.findViewById(R.id.qunatity);
        level = dialog.findViewById(R.id.level);
        add_medicine = dialog.findViewById(R.id.add_medicine);
        dataBaseHelper = new DataBaseHelper(this);
        Medicine = new ArrayList<>();
        Formula = new ArrayList<>();
        Qunatity = new ArrayList<>();
        Level = new ArrayList<>();
        displayData();

        txtBTN.setOnClickListener(view -> {
            dialog.show();
        });

        add_medicine.setOnClickListener(view -> {
            String Medicine_Name = medicine_name.getText().toString().trim();
            String Formula = formula.getText().toString().trim();
            String Quantity = quantity.getText().toString().trim();
            String Level = level.getText().toString().trim();

                 if (dataBaseHelper.insert_medicine(Medicine_Name,Formula,Quantity,Level)){
                     Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

                     dialog.dismiss();
                     finish();
                     overridePendingTransition(0, 0);
                     startActivity(getIntent());
                     overridePendingTransition(0, 0);
                 }






        });





    }

    private void displayData() {

        Cursor res = dataBaseHelper.getAllMedicine();
        if (!(res.getCount()==0)){
            Medicine.clear();
            Formula.clear();
            Qunatity.clear();
            Level.clear();
            while (res.moveToNext()){
                Medicine.add(res.getString(1));
                Formula.add(res.getString(2));
                Qunatity.add(res.getString(3));
                Level.add(res.getString(4));
            }
            medicineAdapter = new MedicineAdapter(this,Medicine,Formula,Qunatity,Level);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(medicineAdapter);

        }

    }

}