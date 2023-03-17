package com.app.passwordmanager.utils;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.app.passwordmanager.MainActivity;
import com.app.passwordmanager.R;
import com.app.passwordmanager.data.database.AppDatabase;
import com.app.passwordmanager.data.preferences.PreferencesUtils;

public class Utility {

    public static void showToast(Context context, String message) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastRoot = inflater.inflate(R.layout.custom_toast, null);
        ((TextView) toastRoot.findViewById(R.id.txt_message)).setText(message);
        Toast toast = new Toast(context);
        toast.setView(toastRoot);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL,
                0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void copyToClipboard(Context context, String passcode) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", passcode);
        clipboard.setPrimaryClip(clip);
        Utility.showToast(context, "Text copied to clipboard !");
    }

    public static void resetAll(Context context) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            AppDatabase.getInstance(context).clearAllTables();
            new PreferencesUtils(context).clearAllPreferences();
            Log.e("ResetAll", "Done");
        });

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
