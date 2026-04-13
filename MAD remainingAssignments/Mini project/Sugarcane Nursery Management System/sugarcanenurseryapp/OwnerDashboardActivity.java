package com.example.sugarcanenurseryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class OwnerDashboardActivity extends AppCompatActivity {

    Button btnAddTray, btnViewTrays, btnAddVariety, btnViewOrders, btnIrrigation, btnPest, btnReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);

        btnAddTray = findViewById(R.id.btnAddTray);
        btnViewTrays = findViewById(R.id.btnViewTrays);
        btnAddVariety = findViewById(R.id.btnAddVariety);
        btnViewOrders = findViewById(R.id.btnViewOrders);
        btnIrrigation = findViewById(R.id.btnIrrigation);
        btnPest = findViewById(R.id.btnPest);
        btnReports = findViewById(R.id.btnReports);

        btnAddTray.setOnClickListener(v -> startActivity(new Intent(this, AddTrayActivity.class)));
        btnViewTrays.setOnClickListener(v -> startActivity(new Intent(this, ViewTraysActivity.class)));
        btnAddVariety.setOnClickListener(v -> startActivity(new Intent(this, AddVarietyActivity.class)));
        btnViewOrders.setOnClickListener(v -> startActivity(new Intent(this, ViewOrdersActivity.class)));
        btnIrrigation.setOnClickListener(v -> startActivity(new Intent(this, IrrigationActivity.class)));
        btnPest.setOnClickListener(v -> startActivity(new Intent(this, PestDiseaseActivity.class)));
        btnReports.setOnClickListener(v -> startActivity(new Intent(this, ReportsActivity.class)));

    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
