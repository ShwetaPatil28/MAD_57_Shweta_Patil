package com.example.buttons;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        ToggleButton toggle = findViewById(R.id.toggleBtn);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> {

            if(toggle.isChecked())
                Toast.makeText(this, "Notifications ON", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Notifications OFF", Toast.LENGTH_SHORT).show();
        });
    }
}