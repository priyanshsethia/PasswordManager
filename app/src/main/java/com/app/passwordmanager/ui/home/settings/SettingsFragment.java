package com.app.passwordmanager.ui.home.settings;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.passwordmanager.R;
import com.app.passwordmanager.data.preferences.PreferencesUtils;
import com.app.passwordmanager.utils.Utility;


public class SettingsFragment extends Fragment {

    Context context;
    PreferencesUtils preferences;

    View changePasscode;
    View developerInfo;
    View reportBug;
    View buildInfo;
    TextView resetAll;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        preferences = new PreferencesUtils(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changePasscode = view.findViewById(R.id.change_password);
        developerInfo = view.findViewById(R.id.developer_info);
        reportBug = view.findViewById(R.id.report_bug);
        buildInfo = view.findViewById(R.id.build_info);
        resetAll = view.findViewById(R.id.reset_all);

        setButtonClick();
    }

    private void setButtonClick() {

        changePasscode.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChangePasscodeScreenActivity.class);
            startActivity(intent);
        });

        developerInfo.setOnClickListener(v -> {
            Intent intent = new Intent(context, DeveloperInfoScreenActivity.class);
            startActivity(intent);
        });

        reportBug.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "sethiapriyansh@gmail.com", null));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Report an bug");
            intent.putExtra(Intent.EXTRA_TEXT, "Report an bug : Password Manager");
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });

        buildInfo.setOnClickListener(v -> {
            // TODO : After update build version.
        });

        resetAll.setOnClickListener(v -> {
            Utility.resetAll(context);
        });


    }
}