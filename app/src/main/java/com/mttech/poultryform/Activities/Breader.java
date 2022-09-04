package com.mttech.poultryform.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.mttech.poultryform.Adapter.BreaderAdapter;
import com.mttech.poultryform.R;

public class Breader extends AppCompatActivity {
    RecyclerView recyclerView;
    BreaderAdapter breaderAdapter;
    Dialog dialog;
    EditText breader_name, breader_no;
    Button insert;
    ImageButton textBtn;


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

        textBtn = findViewById(R.id.textBtn);
        textBtn.setOnClickListener(view -> {
            dialog.show();
        });



    }
}