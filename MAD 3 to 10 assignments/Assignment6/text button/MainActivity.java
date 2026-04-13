package com.example.textbutton;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button textButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textButton = findViewById(R.id.textButton);

        textButton.setOnClickListener(v ->
                Toast.makeText(this, "Text Button Clicked!", Toast.LENGTH_SHORT).show()
        );
    }
}