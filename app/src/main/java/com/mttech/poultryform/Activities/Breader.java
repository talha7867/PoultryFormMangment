package com.mttech.poultryform.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mttech.poultryform.Adapter.BreaderAdapter;
import com.mttech.poultryform.Database.DataBaseHelper;
import com.mttech.poultryform.R;

import java.util.ArrayList;

public class Breader extends AppCompatActivity {
    RecyclerView recyclerView;
    BreaderAdapter breaderAdapter;
    Dialog dialog;
    EditText breader_name, breader_no;
    Button insert;
    ImageButton textBtn;
    DataBaseHelper dataBaseHelper;
    ArrayList<String> id , name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breader);
        recyclerView = findViewById(R.id.recyler_view);
        dialog = new Dialog(this,R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_breader_dialouge);
        breader_name = dialog.findViewById(R.id.breader_name);
        breader_no = dialog.findViewById(R.id.breader_no);
        insert = dialog.findViewById(R.id.insert);

        dataBaseHelper = new DataBaseHelper(this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        number = new ArrayList<>();
        displayData();



//
//        arrayList = dataBaseHelper.getBreader();



        textBtn = findViewById(R.id.textBtn);
        textBtn.setOnClickListener(view -> {
            dialog.show();
        });
          insert.setOnClickListener(view -> {
              int Number = Integer.parseInt(breader_no.getText().toString());
              String Name = breader_name.getText().toString();
              if (!Name.isEmpty()){
                  if (dataBaseHelper.insert_breader(Name,Number)){
                      Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                      breader_no.getText().clear();
                      breader_name.getText().clear();
                      dialog.dismiss();
                  }
              }


          });


    }

    private void displayData(){
        Cursor res = dataBaseHelper.getAllBreader();
        if (!(res.getCount() == 0)) {
            id.clear();
            name.clear();
            number.clear();
            while (res.moveToNext()) {
                id.add(res.getString(0));
                name.add(res.getString(1));
                number.add(res.getString(2));
            }
            breaderAdapter = new BreaderAdapter(this,id,name,number);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(breaderAdapter);



        }
    }
}