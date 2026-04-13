package com.example.sugarcanenurseryapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewOrdersActivity extends AppCompatActivity {

    TextView txtData;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        txtData = findViewById(R.id.txtData);
        db = new DBHelper(this);

        Cursor cursor = db.getOrders();
        StringBuilder sb = new StringBuilder();

        if (cursor.getCount() == 0) {
            txtData.setText("No Orders Found");
            return;
        }

        while (cursor.moveToNext()) {
            sb.append("🆔 Order ID: ").append(cursor.getInt(0)).append("\n");
            sb.append("🌱 Variety: ").append(cursor.getString(1)).append("\n");
            sb.append("📦 Quantity: ").append(cursor.getString(2)).append("\n");
            sb.append("👤 Customer: ").append(cursor.getString(3)).append("\n");
            sb.append("📌 Status: ").append(cursor.getString(4)).append("\n");
            sb.append("\n-------------------------\n\n");
        }

        txtData.setText(sb.toString());
    }
}