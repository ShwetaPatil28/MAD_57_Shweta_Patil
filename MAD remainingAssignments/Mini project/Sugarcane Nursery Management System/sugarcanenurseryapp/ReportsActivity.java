package com.example.sugarcanenurseryapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReportsActivity extends AppCompatActivity {

    TextView txtTrays, txtSuccess, txtOrders;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        txtTrays = findViewById(R.id.txtTrays);
        txtSuccess = findViewById(R.id.txtSuccess);
        txtOrders = findViewById(R.id.txtOrders);
        db = new DBHelper(this);

        txtTrays.setText("Total Trays: " + db.getTotalTrays());
        txtSuccess.setText("Successful Plants: " + db.getSuccessfulPlants());
        txtOrders.setText("Total Orders: " + db.getTotalOrders());
    }
}
