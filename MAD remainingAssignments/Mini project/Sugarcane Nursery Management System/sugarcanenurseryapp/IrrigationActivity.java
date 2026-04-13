package com.example.sugarcanenurseryapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class IrrigationActivity extends AppCompatActivity {

    EditText date, notes;
    Button btnSave;
    TextView txtData;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irrigation);

        date = findViewById(R.id.editDate);
        notes = findViewById(R.id.editNotes);
        btnSave = findViewById(R.id.btnSaveIrrigation);
        txtData = findViewById(R.id.txtData);
        db = new DBHelper(this);

        btnSave.setOnClickListener(v -> {

            String d = date.getText().toString().trim();
            String n = notes.getText().toString().trim();

            if (d.isEmpty() || n.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = db.addIrrigation(d, n);

            if (result) {
                Toast.makeText(this, "✅ Irrigation Logged", Toast.LENGTH_SHORT).show();
                date.setText("");
                notes.setText("");
                loadData();
            } else {
                Toast.makeText(this, "❌ Error", Toast.LENGTH_SHORT).show();
            }
        });

        loadData();
    }

    private void loadData() {
        Cursor cursor = db.getIrrigation();
        StringBuilder sb = new StringBuilder();

        if (cursor.getCount() == 0) {
            txtData.setText("No Irrigation Records Found");
            return;
        }

        while (cursor.moveToNext()) {
            sb.append("📅 Date: ").append(cursor.getString(1)).append("\n");
            sb.append("📝 Notes: ").append(cursor.getString(2)).append("\n");
            sb.append("\n----------------------\n\n");
        }

        txtData.setText(sb.toString());
    }
}