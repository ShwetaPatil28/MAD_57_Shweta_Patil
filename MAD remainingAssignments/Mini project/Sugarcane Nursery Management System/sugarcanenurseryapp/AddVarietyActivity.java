package com.example.sugarcanenurseryapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class AddVarietyActivity extends AppCompatActivity {

    EditText name, yield, maturity, price;
    Button btnSave;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_variety);

        name = findViewById(R.id.editName);
        yield = findViewById(R.id.editYield);
        maturity = findViewById(R.id.editMaturity);
        price = findViewById(R.id.editPrice);
        btnSave = findViewById(R.id.btnSaveVariety);
        db = new DBHelper(this);

        btnSave.setOnClickListener(v -> {

            if (name.getText().toString().isEmpty() ||
                    yield.getText().toString().isEmpty() ||
                    maturity.getText().toString().isEmpty() ||
                    price.getText().toString().isEmpty()) {

                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = db.addVariety(
                    name.getText().toString(),
                    yield.getText().toString(),
                    maturity.getText().toString(),
                    price.getText().toString()
            );

            if (result) {
                Toast.makeText(this, "🌾 Variety Added Successfully", Toast.LENGTH_SHORT).show();
                name.setText("");
                yield.setText("");
                maturity.setText("");
                price.setText("");
            } else {
                Toast.makeText(this, "❌ Error Adding Variety", Toast.LENGTH_SHORT).show();
            }
        });
    }
}