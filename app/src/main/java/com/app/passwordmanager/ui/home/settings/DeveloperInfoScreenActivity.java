package com.app.passwordmanager.ui.home.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.passwordmanager.R;

public class DeveloperInfoScreenActivity extends AppCompatActivity {

    View linkedIn_profile;
    View github_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_info_screen);

        linkedIn_profile = findViewById(R.id.social_media);
        github_profile = findViewById(R.id.github);

        setClickListener();
    }

    private void setClickListener() {

        linkedIn_profile.setOnClickListener(v -> {
            Intent viewIntent = new Intent("android.intent.action.VIEW",
                    Uri.parse("https://www.linkedin.com/in/priyanshsethia/"));
            startActivity(viewIntent);
        });

        github_profile.setOnClickListener(v -> {
            Intent viewIntent = new Intent("android.intent.action.VIEW",
                    Uri.parse("https://github.com/priyanshsethia"));
            startActivity(viewIntent);
        });
    }
}