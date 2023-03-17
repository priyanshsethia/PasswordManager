package com.app.passwordmanager.ui.authenticate;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;

import com.app.passwordmanager.R;
import com.app.passwordmanager.data.preferences.PreferencesUtils;
import com.app.passwordmanager.ui.home.HomeActivity;
import com.app.passwordmanager.utils.Utility;
import com.google.android.material.textfield.TextInputEditText;

public class LockScreenActivity extends AppCompatActivity {

    PreferencesUtils preferences;

    TextInputEditText passcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        preferences = new PreferencesUtils(this);

        passcode = findViewById(R.id.txt_passcode);

        passcode.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                unlock();
                handled = true;
            }
            return handled;
        });
    }

    private void unlock() {
        if (!passcode.getText().toString().isEmpty()) {
            int password = Integer.parseInt(passcode.getText().toString());

            if (password == preferences.getRegularPasscode())
                openHomeActivity();
            else {
                Utility.showToast(this, "Invalid passcode, please enter again !...");
                passcode.setText("");
            }
        }
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}