package com.example.profileopen;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    String correctUsername = "shweta";
    String correctPassword = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            String user = etUsername.getText().toString();
            String pass = etPassword.getText().toString();

            if(user.equals(correctUsername) && pass.equals(correctPassword)) {

                // Launch Profile App
                PackageManager pm = getPackageManager();
                Intent intent = pm.getLaunchIntentForPackage("com.example.profile");

                if(intent != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this,
                            "Profile App not installed",
                            Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this,
                        "Invalid Username or Password",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}