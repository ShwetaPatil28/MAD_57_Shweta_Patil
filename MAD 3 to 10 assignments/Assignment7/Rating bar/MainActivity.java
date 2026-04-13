package com.example.ratingbar;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        RatingBar rb = findViewById(R.id.rb);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> {
            Toast.makeText(this,
                    "Rating: "+rb.getRating(),
                    Toast.LENGTH_SHORT).show();
        });
    }
}