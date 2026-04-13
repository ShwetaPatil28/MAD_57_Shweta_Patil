package com.example.spinner;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        Spinner sp = findViewById(R.id.spinner);
        Button btn = findViewById(R.id.btn);

        String[] data = {"BCA","BBA","B.Tech"};

        ArrayAdapter<String> ad = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, data);

        sp.setAdapter(ad);

        btn.setOnClickListener(v -> {
            Toast.makeText(this,"Selected: "+sp.getSelectedItem(),
                    Toast.LENGTH_SHORT).show();
        });
    }
}