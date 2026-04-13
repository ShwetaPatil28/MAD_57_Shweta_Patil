package com.example.sugarcanenurseryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button btnLogin, btnRegister;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        db = new DBHelper(this);

        btnLogin.setOnClickListener(v -> {
            String e = email.getText().toString();
            String p = password.getText().toString();

            if (db.loginUser(e, p)) {
                String role = db.getUserRole(e);
                if (role.equals("Owner")) {
                    startActivity(new Intent(this, OwnerDashboardActivity.class));
                } else {
                    startActivity(new Intent(this, CustomerDashboardActivity.class));
                }
            } else {
                Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}
