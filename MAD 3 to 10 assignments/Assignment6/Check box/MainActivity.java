package com.example.checkbox;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        CheckBox c1 = findViewById(R.id.c1);
        CheckBox c2 = findViewById(R.id.c2);
        CheckBox c3 = findViewById(R.id.c3);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> {

            String result = "";

            if(c1.isChecked())
                result += "Reading ";

            if(c2.isChecked())
                result += "Gaming ";

            if(c3.isChecked())
                result += "Music ";

            if(result.equals(""))
                Toast.makeText(this, "Select at least one", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Selected: " + result, Toast.LENGTH_SHORT).show();
        });
    }
}