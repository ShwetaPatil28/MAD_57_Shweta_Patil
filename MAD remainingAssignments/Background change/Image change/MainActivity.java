package com.example.pythonmarksapp;
import com.example.pythonmarksapp.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        b1.setOnClickListener(v -> mainLayout.setBackgroundResource(R.drawable.bg1));
        b2.setOnClickListener(v -> mainLayout.setBackgroundResource(R.drawable.bg2));
        b3.setOnClickListener(v -> mainLayout.setBackgroundResource(R.drawable.bg3));
    }
}
