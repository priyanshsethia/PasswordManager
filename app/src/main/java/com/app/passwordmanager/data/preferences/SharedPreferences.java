package com.app.passwordmanager.data.preferences;

import android.app.Activity;
import android.content.Context;

import com.app.passwordmanager.utils.AppConstants;

public class SharedPreferences {

    private static android.content.SharedPreferences mSharedPref;
    public static final String PREFERENCE_NAME = "com.app.password_manager";
    private static final String PREF_REGULAR_PASSCODE = "regular_passcode";
    private static final String PREF_DURESS_PASSCODE = "duress_passcode";

    public static android.content.SharedPreferences getInstance(Context context) {
        if (mSharedPref == null)
            mSharedPref = context.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
        return mSharedPref;
    }

    /**
     * Read & Write different types data to Shared Preference
     */
    // String
    public void write(String key, String value) {
        android.content.SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    // Integer
    public void write(String key, Integer value) {
        android.content.SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).apply();
    }

    public Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    //Boolean
    public void write(String key, boolean value) {
        android.content.SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }

    public boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    /**
     * Getting & Setting data to SharedPreference, according to key-value.
     */
    public void setRegularPasscode(String value) {
        write(PREF_REGULAR_PASSCODE, value);
    }
    
    public String getRegularPasscode() {
        return read(PREF_REGULAR_PASSCODE, "");
    }
    
    public void setDuressPasscode(String value) {
        write(PREF_DURESS_PASSCODE, value);
    }
    
    public String getDuressPasscode() {
        return read(PREF_DURESS_PASSCODE, "");
    }
}
