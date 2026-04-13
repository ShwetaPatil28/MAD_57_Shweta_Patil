package com.example.sugarcanenurseryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerDashboardActivity extends AppCompatActivity {

    Button btnViewVarieties, btnPlaceOrder, btnPest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        btnViewVarieties = findViewById(R.id.btnViewVarieties);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        btnPest = findViewById(R.id.btnPest);

        btnViewVarieties.setOnClickListener(v -> startActivity(new Intent(this, ViewVarietiesActivity.class)));
        btnPlaceOrder.setOnClickListener(v -> startActivity(new Intent(this, PlaceOrderActivity.class)));
        btnPest.setOnClickListener(v -> startActivity(new Intent(this, PestDiseaseActivity.class)));
    }
}
