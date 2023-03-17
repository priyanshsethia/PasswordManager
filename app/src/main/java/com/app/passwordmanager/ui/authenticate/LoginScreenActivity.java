package com.app.passwordmanager.ui.authenticate;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.passwordmanager.R;
import com.app.passwordmanager.data.preferences.PreferencesUtils;
import com.app.passwordmanager.ui.home.HomeActivity;
import com.app.passwordmanager.utils.Utility;
import com.google.android.material.textfield.TextInputEditText;

public class LoginScreenActivity extends AppCompatActivity {

    PreferencesUtils preferences;

    TextView user_greetings;
    TextInputEditText user_name;
    TextInputEditText regular_passcode;
    TextInputEditText confirm_passcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        preferences = new PreferencesUtils(this);

        user_greetings = findViewById(R.id.greeting);
        user_name = findViewById(R.id.txt_user_name);
        regular_passcode = findViewById(R.id.txt_regular_passcode);
        confirm_passcode = findViewById(R.id.txt_confirm_passcode);

        user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    user_greetings.setText("Hey " + s.toString().trim() + " ! 👋");
                else
                    user_greetings.setText(getResources().getString(R.string.hey_user));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirm_passcode.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                saveUserDetails();
                handled = true;
            }
            return handled;
        });

    }

    private void saveUserDetails() {
        if (validateFields()) {
            if (validatePasscodes()) {
                preferences.setUserName(user_name.getText().toString().trim());
                preferences.setRegularPasscode(Integer.parseInt(regular_passcode.getText().toString()));
                preferences.setIsLogin(true);
                openHomeActivity();
            } else
                Utility.showToast(this, "Confirm Passcode is not matching !");
        }
    }

    private boolean validateFields() {
        if (user_name.getText().toString().trim().isEmpty()) {
            Utility.showToast(this, "Name is required !");
            user_name.requestFocus();
        } else if (regular_passcode.getText().toString().isEmpty()) {
            Utility.showToast(this, "Regular Passcode is required !");
            regular_passcode.requestFocus();
        } else if (confirm_passcode.getText().toString().isEmpty()) {
            Utility.showToast(this, "Confirm Passcode is required !");
            regular_passcode.requestFocus();
        } else
            return true;
        return false;
    }

    private boolean validatePasscodes() {
        int regularPasscode = Integer.parseInt(regular_passcode.getText().toString());
        int confirmPasscode = Integer.parseInt(confirm_passcode.getText().toString());
        return regularPasscode == confirmPasscode;
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}