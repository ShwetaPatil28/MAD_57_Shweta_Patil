package com.example.sugarcanenurseryapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password;
    Spinner roleSpinner;
    Button btnRegister;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        roleSpinner = findViewById(R.id.spinnerRole);
        btnRegister = findViewById(R.id.btnRegister);
        db = new DBHelper(this);

        // Spinner data
        String[] roles = {"Owner", "Customer"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

        btnRegister.setOnClickListener(v -> {

            String e = email.getText().toString().trim();
            String p = password.getText().toString().trim();
            String role = roleSpinner.getSelectedItem().toString();

            if (e.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean result = db.registerUser(e, p, role);

            if (result) {
                Toast.makeText(this, "✅ Registered as " + role, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "❌ Registration Failed (User may exist)", Toast.LENGTH_LONG).show();
            }
        });
    }
}