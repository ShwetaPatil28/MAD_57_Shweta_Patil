package com.example.contextmenu;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // Register context menu
        registerForContextMenu(textView);

        // Contextual Action Mode
        textView.setOnLongClickListener(v -> {
            startActionMode(actionModeCallback);
            return true;
        });
    }

    // Context Menu (Text)
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.copy) {
            Toast.makeText(this, "Copy Clicked", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.paste) {
            Toast.makeText(this, "Paste Clicked", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }

    // Contextual Action Mode (Icons)
    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_contextual, menu);
            mode.setTitle("Select Action");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            if (item.getItemId() == R.id.delete) {
                Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                mode.finish();
            } else if (item.getItemId() == R.id.share) {
                Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                mode.finish();
            }

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {}
    };
}