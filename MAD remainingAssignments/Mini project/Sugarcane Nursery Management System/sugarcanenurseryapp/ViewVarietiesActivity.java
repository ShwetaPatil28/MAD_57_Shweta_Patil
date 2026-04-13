package com.example.sugarcanenurseryapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewVarietiesActivity extends AppCompatActivity {

    TextView txtData;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_varieties);

        txtData = findViewById(R.id.txtData);
        db = new DBHelper(this);

        loadData();
    }

    private void loadData() {
        Cursor cursor = db.getVarieties();
        StringBuilder sb = new StringBuilder();

        if (cursor.getCount() == 0) {
            txtData.setText("No Varieties Found");
            return;
        }

        while (cursor.moveToNext()) {
            sb.append("🌾 Name: ").append(cursor.getString(1)).append("\n");
            sb.append("📊 Yield: ").append(cursor.getString(2)).append("\n");
            sb.append("⏳ Maturity: ").append(cursor.getString(3)).append("\n");
            sb.append("💰 Price: ").append(cursor.getString(4)).append("\n");
            sb.append("\n----------------------\n\n");
        }

        txtData.setText(sb.toString());
    }
}