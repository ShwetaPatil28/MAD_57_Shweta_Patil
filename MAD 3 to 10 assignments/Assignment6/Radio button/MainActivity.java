package com.example.radio;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        RadioGroup rg = findViewById(R.id.rg);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> {

            int id = rg.getCheckedRadioButtonId();

            if(id == -1){
                Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton rb = findViewById(id);
                Toast.makeText(this, "Selected: " + rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}