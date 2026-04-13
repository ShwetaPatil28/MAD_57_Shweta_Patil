package com.example.sugarcanenurseryapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

// Notification imports
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AddTrayActivity extends AppCompatActivity {

    EditText trayNo, seedType, date;
    Spinner statusSpinner;
    Button btnSave;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tray);
        getSupportActionBar().show();
        trayNo = findViewById(R.id.editTrayNo);
        seedType = findViewById(R.id.editSeedType);
        date = findViewById(R.id.editDate);
        statusSpinner = findViewById(R.id.spinnerStatus);
        btnSave = findViewById(R.id.btnSaveTray);

        db = new DBHelper(this);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != android.content.pm.PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                        101
                );
            }
        }

        // Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.tray_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        // Button Click
        btnSave.setOnClickListener(v -> {

            if (trayNo.getText().toString().isEmpty() ||
                    seedType.getText().toString().isEmpty() ||
                    date.getText().toString().isEmpty()) {

                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ Alert Dialog (Unit 4)
            new android.app.AlertDialog.Builder(this)
                    .setTitle("Confirm Save")
                    .setMessage("Do you want to save tray?")
                    .setPositiveButton("Yes", (dialog, which) -> saveTray())
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    // ✅ SAVE FUNCTION
    private void saveTray() {

        boolean result = db.addTray(
                trayNo.getText().toString(),
                seedType.getText().toString(),
                date.getText().toString(),
                statusSpinner.getSelectedItem().toString()
        );

        if (result) {
            Toast.makeText(this, "🌱 Tray Added Successfully", Toast.LENGTH_SHORT).show();

            showNotification();

            trayNo.setText("");
            seedType.setText("");
            date.setText("");
        } else {
            Toast.makeText(this, "❌ Error Adding Tray", Toast.LENGTH_SHORT).show();
        }
    }

    // ✅ NOTIFICATION (Unit 5)
    private void showNotification() {

        String channelId = "channel1";

        android.app.NotificationManager manager =
                (android.app.NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Android 8+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            android.app.NotificationChannel channel =
                    new android.app.NotificationChannel(channelId, "Default",
                            android.app.NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, channelId)
                        .setContentTitle("Tray Added")
                        .setContentText("New tray added successfully")
                        .setSmallIcon(android.R.drawable.ic_menu_info_details)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        try {
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU ||
                    checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                            == android.content.pm.PackageManager.PERMISSION_GRANTED) {

                NotificationManagerCompat.from(this).notify(1, builder.build());
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(0, 1, 0, "Logout"); // id = 1
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        if (item.getItemId() == 1) {

            android.widget.Toast.makeText(this, "Logged Out", android.widget.Toast.LENGTH_SHORT).show();

            // Redirect to Login Screen
            startActivity(new android.content.Intent(this, LoginActivity.class));
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}