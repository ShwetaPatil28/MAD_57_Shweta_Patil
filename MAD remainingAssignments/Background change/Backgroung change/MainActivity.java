package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3;
    View mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        mainLayout = findViewById(R.id.main);

        b1.setOnClickListener(v -> mainLayout.setBackgroundColor(Color.RED));
        b2.setOnClickListener(v -> mainLayout.setBackgroundColor(Color.GREEN));
        b3.setOnClickListener(v -> mainLayout.setBackgroundColor(Color.BLUE));
    }
}
