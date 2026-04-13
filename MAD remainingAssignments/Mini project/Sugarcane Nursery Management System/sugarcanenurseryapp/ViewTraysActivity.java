package com.example.sugarcanenurseryapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewTraysActivity extends AppCompatActivity {

    TextView txtData;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trays);

        txtData = findViewById(R.id.txtData);
        db = new DBHelper(this);

        loadData();
    }

    private void loadData() {
        Cursor cursor = db.getTrays();
        StringBuilder sb = new StringBuilder();

        if (cursor.getCount() == 0) {
            txtData.setText("No Tray Data Found");
            return;
        }

        while (cursor.moveToNext()) {
            sb.append("🔢 Tray No: ").append(cursor.getString(1)).append("\n");
            sb.append("🌾 Seed: ").append(cursor.getString(2)).append("\n");
            sb.append("📅 Date: ").append(cursor.getString(3)).append("\n");
            sb.append("📌 Status: ").append(cursor.getString(4)).append("\n");
            sb.append("\n----------------------\n\n");
        }

        txtData.setText(sb.toString());
    }
}