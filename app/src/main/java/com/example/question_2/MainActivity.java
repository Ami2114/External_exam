package com.example.question_2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Button;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Switch soundToggle, vibrationToggle, ledToggle, bannerToggle, contentToggle, lockScreenToggle;
    private Button saveButton;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize toggles and button
        soundToggle = findViewById(R.id.sound_toggle);
        vibrationToggle = findViewById(R.id.vibration_toggle);
        ledToggle = findViewById(R.id.led_toggle);
        bannerToggle = findViewById(R.id.banner_toggle);
        contentToggle = findViewById(R.id.content_toggle);
        lockScreenToggle = findViewById(R.id.lock_screen_toggle);
        saveButton = findViewById(R.id.save_button);

        preferences = getSharedPreferences("NotificationPreferences", MODE_PRIVATE);

        // Load stored preferences
        loadPreferences();

        // Handle Save button click
        saveButton.setOnClickListener(view -> showSaveConfirmation());
    }

    private void loadPreferences() {
        soundToggle.setChecked(preferences.getBoolean("sound", false));
        vibrationToggle.setChecked(preferences.getBoolean("vibration", false));
        ledToggle.setChecked(preferences.getBoolean("led", false));
        bannerToggle.setChecked(preferences.getBoolean("banner", false));
        contentToggle.setChecked(preferences.getBoolean("content", false));
        lockScreenToggle.setChecked(preferences.getBoolean("lock_screen", false));
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sound", soundToggle.isChecked());
        editor.putBoolean("vibration", vibrationToggle.isChecked());
        editor.putBoolean("led", ledToggle.isChecked());
        editor.putBoolean("banner", bannerToggle.isChecked());
        editor.putBoolean("content", contentToggle.isChecked());
        editor.putBoolean("lock_screen", lockScreenToggle.isChecked());
        editor.apply();
    }

    private void showSaveConfirmation() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_confirmation, null);
        bottomSheetDialog.setContentView(sheetView);

        Button confirmButton = sheetView.findViewById(R.id.confirm_button);
        Button cancelButton = sheetView.findViewById(R.id.cancel_button);

        confirmButton.setOnClickListener(view -> {
            savePreferences();
            bottomSheetDialog.dismiss();
        });

        cancelButton.setOnClickListener(view -> bottomSheetDialog.dismiss());

        bottomSheetDialog.show();
    }
}
