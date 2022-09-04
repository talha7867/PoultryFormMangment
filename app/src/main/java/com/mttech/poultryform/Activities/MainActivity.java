package com.mttech.poultryform.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.mttech.poultryform.R;

public class MainActivity extends AppCompatActivity {
    CardView cardView,cardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView = findViewById(R.id.cardView);
        cardView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,Breader.class);
            startActivity(intent);
        });

        cardView2 = findViewById(R.id.cardView2);
        cardView2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,FlocksForm.class);
            startActivity(intent);
        });

    }
}