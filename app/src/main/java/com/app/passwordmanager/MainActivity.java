package com.app.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.app.passwordmanager.data.preferences.PreferencesUtils;
import com.app.passwordmanager.ui.authenticate.LockScreenActivity;
import com.app.passwordmanager.ui.authenticate.LoginScreenActivity;

public class MainActivity extends AppCompatActivity {

    PreferencesUtils preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preference = new PreferencesUtils(this);

        if (preference.isLogin())
            openLockScreenActivity();
        else
            openLoginScreenActivity();
    }

    public void openLockScreenActivity() {
        Intent intent = new Intent(this, LockScreenActivity.class);
        startActivity(intent);
        finish();
    }

    public void openLoginScreenActivity() {
        Intent intent = new Intent(this, LoginScreenActivity.class);
        startActivity(intent);
        finish();
    }
}