package com.app.passwordmanager.ui.home.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.passwordmanager.R;
import com.app.passwordmanager.data.database.AppDatabase;
import com.app.passwordmanager.data.database.SecureElement;
import com.app.passwordmanager.utils.AppConstants;
import com.app.passwordmanager.utils.Utility;
import com.google.android.material.textfield.TextInputEditText;

public class AddPreviewScreenActivity extends AppCompatActivity {

    Context context;
    AppDatabase appDatabase;

    String purpose;
    SecureElement data;
    boolean editMode = false;

    TextView heading;
    ImageView copyButton;
    ImageView addEditButton;
    TextInputEditText title;
    TextInputEditText password;
    TextInputEditText notes;
    TextView deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_preview_screen);

        context = this;
        appDatabase = AppDatabase.getInstance(context);
        data = new SecureElement();

        heading = findViewById(R.id.heading);
        copyButton = findViewById(R.id.copy);
        addEditButton = findViewById(R.id.add_edit_password);
        title = findViewById(R.id.title);
        password = findViewById(R.id.txt_passcode);
        notes = findViewById(R.id.notes);
        deleteButton = findViewById(R.id.delete_button);

        setData();
        setButtonClick();
    }

    private void setData() {
        Intent intent = getIntent();
        if (intent != null) {
            purpose = intent.getStringExtra("purpose");
            switch (purpose) {
                case AppConstants.ADD_PASSCODE:
                    heading.setText(getResources().getString(R.string.add_password));
                    addEditButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                    copyButton.setVisibility(View.GONE);
                    deleteButton.setVisibility(View.GONE);
                    setFieldEditable(true);
                    break;

                case AppConstants.EDIT_PASSCODE:
                    data = (SecureElement) intent.getSerializableExtra("data");
                    if (data != null) {
                        heading.setText(getResources().getString(R.string.edit_password));
                        title.setText(data.title);
                        password.setText(data.password);
                        notes.setText(data.notes);

                        addEditButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit));
                        copyButton.setVisibility(!editMode ? View.VISIBLE : View.GONE);
                        deleteButton.setVisibility(View.VISIBLE);
                    }
                    setFieldEditable(false);
                    break;
                default:
                    finish();
                    break;
            }
        }
    }

    private void setFieldEditable(boolean isEditable) {
        title.setEnabled(isEditable);
        password.setEnabled(isEditable);
        notes.setEnabled(isEditable);
    }

    private void setButtonClick() {

        addEditButton.setOnClickListener(v -> {
            if (validateFields()) {
                switch (purpose) {
                    case AppConstants.ADD_PASSCODE:
                        data.title = title.getText().toString().trim();
                        data.password = password.getText().toString().trim();
                        data.notes = notes.getText().toString().trim();

                        AppDatabase.databaseWriteExecutor.execute(() -> {
                            appDatabase.secureElementDao().insert(data);
                            runOnUiThread(() -> {
                                Utility.showToast(context, "Password insert successfully !");
                                finish();
                            });
                        });

                        break;

                    case AppConstants.EDIT_PASSCODE:
                        if (!editMode) {
                            editMode = true;
                            updateData();
                        } else {
                            data.title = title.getText().toString().trim();
                            data.password = password.getText().toString().trim();
                            data.notes = notes.getText().toString().trim();

                            AppDatabase.databaseWriteExecutor.execute(() -> {
                                appDatabase.secureElementDao().update(data);
                                runOnUiThread(() -> {
                                    editMode = false;
                                    updateData();
                                    Utility.showToast(context, "Password update successfully !");
                                });
                            });
                        }
                        break;
                }
            }
        });

        deleteButton.setOnClickListener(v -> {
            AppDatabase.databaseWriteExecutor.execute(() -> {
                appDatabase.secureElementDao().delete(data);
                runOnUiThread(() -> {
                    Utility.showToast(context, "Password delete successfully !");
                    finish();
                });
            });
        });

        copyButton.setOnClickListener(v -> {
            if (!data.password.isEmpty())
                Utility.copyToClipboard(context, data.password);
            else
                Utility.showToast(context, "Password is required !");
        });
    }

    private boolean validateFields() {
        if (title.getText().toString().trim().isEmpty()) {
            Utility.showToast(context, "Title is required !");
            title.requestFocus();
        } else if (password.getText().toString().isEmpty()) {
            Utility.showToast(context, "Password is required !");
            password.requestFocus();
        } else
            return true;
        return false;
    }

    private void updateData() {
        addEditButton.setImageDrawable(getResources().getDrawable(editMode ? R.drawable.ic_check : R.drawable.ic_edit));
        copyButton.setVisibility(editMode ? View.GONE : View.VISIBLE);
        setFieldEditable(editMode);
    }
}