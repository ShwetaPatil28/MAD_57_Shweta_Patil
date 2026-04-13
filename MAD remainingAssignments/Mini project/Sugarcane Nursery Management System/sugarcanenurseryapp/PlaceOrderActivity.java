package com.example.sugarcanenurseryapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class PlaceOrderActivity extends AppCompatActivity {

    EditText variety, quantity, customer;
    Button btnPlace;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        variety = findViewById(R.id.editVariety);
        quantity = findViewById(R.id.editQuantity);
        customer = findViewById(R.id.editCustomer);
        btnPlace = findViewById(R.id.btnPlaceOrder);
        db = new DBHelper(this);

        btnPlace.setOnClickListener(v -> {

            if (variety.getText().toString().isEmpty() ||
                    quantity.getText().toString().isEmpty() ||
                    customer.getText().toString().isEmpty()) {

                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = db.placeOrder(
                    variety.getText().toString(),
                    quantity.getText().toString(),
                    customer.getText().toString()
            );

            if (result) {
                Toast.makeText(this, "📦 Order Placed Successfully", Toast.LENGTH_SHORT).show();
                variety.setText("");
                quantity.setText("");
                customer.setText("");
            } else {
                Toast.makeText(this, "❌ Error Placing Order", Toast.LENGTH_SHORT).show();
            }
        });
    }
}