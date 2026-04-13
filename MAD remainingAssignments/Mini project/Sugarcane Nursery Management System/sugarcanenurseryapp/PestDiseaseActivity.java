package com.example.sugarcanenurseryapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class PestDiseaseActivity extends AppCompatActivity {

    EditText name, symptoms, treatment;
    Button btnSave;
    TextView txtData;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pest_disease);

        name = findViewById(R.id.editName);
        symptoms = findViewById(R.id.editSymptoms);
        treatment = findViewById(R.id.editTreatment);
        btnSave = findViewById(R.id.btnSavePest);
        txtData = findViewById(R.id.txtData);
        db = new DBHelper(this);

        btnSave.setOnClickListener(v -> {
            boolean result = db.addPest(
                    name.getText().toString(),
                    symptoms.getText().toString(),
                    treatment.getText().toString()
            );
            if (result) Toast.makeText(this, "Pest Info Added", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            loadData();
        });

        loadData();
    }

    private void loadData() {
        Cursor cursor = db.getPests();
        StringBuilder sb = new StringBuilder();
        while (cursor.moveToNext()) {
            sb.append("Name: ").append(cursor.getString(1)).append("\n");
            sb.append("Symptoms: ").append(cursor.getString(2)).append("\n");
            sb.append("Treatment: ").append(cursor.getString(3)).append("\n\n");
        }
        txtData.setText(sb.toString());
    }
}
