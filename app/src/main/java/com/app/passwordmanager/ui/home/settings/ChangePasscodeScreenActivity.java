package com.app.passwordmanager.ui.home.settings;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.passwordmanager.R;
import com.app.passwordmanager.data.preferences.PreferencesUtils;
import com.app.passwordmanager.utils.Utility;
import com.google.android.material.textfield.TextInputEditText;

public class ChangePasscodeScreenActivity extends AppCompatActivity {

    PreferencesUtils preferences;

    int currentPasscode;
    int newPasscode;
    int confirmPasscode;

    ImageView doneButton;
    TextInputEditText current_passcode;
    TextInputEditText new_passcode;
    TextInputEditText confirm_passcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passcode_screen);

        preferences = new PreferencesUtils(this);

        doneButton = findViewById(R.id.done);
        current_passcode = findViewById(R.id.txt_current_passcode);
        new_passcode = findViewById(R.id.txt_new_passcode);
        confirm_passcode = findViewById(R.id.txt_confirm_passcode);

        doneButton.setOnClickListener(v -> changePasscode());
    }

    private void changePasscode() {
        if (validateFields()) {

            currentPasscode = Integer.parseInt(current_passcode.getText().toString());
            newPasscode = Integer.parseInt(new_passcode.getText().toString());
            confirmPasscode = Integer.parseInt(confirm_passcode.getText().toString());

            if (validateCurrentPasscodes()) {
                if (!validateIsSameAsOld()) {
                    if (validateNewPasscodes()) {
                        preferences.setRegularPasscode(newPasscode);
                        Utility.showToast(this, "Passcode changed successfully !");
                        finish();
                    } else {
                        Utility.showToast(this, "Confirm Passcode is not matching !");
                        confirm_passcode.requestFocus();
                    }
                } else {
                    Utility.showToast(this, "New Passcode cannot be same as Current Passcode !");
                    new_passcode.requestFocus();
                }
            } else {
                Utility.showToast(this, "Incorrect Current Passcode !");
                current_passcode.requestFocus();
            }
        }
    }

    private boolean validateFields() {
        if (current_passcode.getText().toString().trim().isEmpty()) {
            Utility.showToast(this, "Current Passcode is required !");
            current_passcode.requestFocus();
        } else if (new_passcode.getText().toString().isEmpty()) {
            Utility.showToast(this, "New Passcode is required !");
            new_passcode.requestFocus();
        } else if (confirm_passcode.getText().toString().isEmpty()) {
            Utility.showToast(this, "Confirm Passcode is required !");
            confirm_passcode.requestFocus();
        } else
            return true;
        return false;
    }

    private boolean validateCurrentPasscodes() {
        return preferences.getRegularPasscode() == currentPasscode;
    }

    private boolean validateIsSameAsOld() {
        return newPasscode == currentPasscode;
    }


    private boolean validateNewPasscodes() {
        return newPasscode == confirmPasscode;
    }
}