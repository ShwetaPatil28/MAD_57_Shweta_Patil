package com.example.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLinkedIn, btnEmail, btnResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        btnLinkedIn = findViewById(R.id.btnLinkedIn);
        btnEmail = findViewById(R.id.btnEmail);
        btnResume = findViewById(R.id.btnResume);

        // LinkedIn
        btnLinkedIn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.linkedin.com/in/YOUR_PROFILE_ID"));
            startActivity(intent);
        });

        // Email
        btnEmail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:yourmail@gmail.com"));
            startActivity(emailIntent);
        });

        // Resume
        btnResume.setOnClickListener(v -> {
            Intent resumeIntent = new Intent(Intent.ACTION_VIEW);
            resumeIntent.setData(Uri.parse("https://yourresumelink.com"));
            startActivity(resumeIntent);
        });

        Button btnBackToLogin = findViewById(R.id.btnBackToLogin);

        btnBackToLogin.setOnClickListener(v -> {

            Intent intent = getPackageManager()
                    .getLaunchIntentForPackage("com.example.profileopen");

            if (intent != null) {
                startActivity(intent);
                finish();
            }

        });
    }
}