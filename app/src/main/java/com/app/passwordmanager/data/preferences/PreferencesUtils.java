package com.app.passwordmanager.data.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesUtils {

    private static SharedPreferences mSharedPref;
    public static final String PREFERENCE_NAME = "com.app.password_manager";
    private static final String PREF_USER_NAME = "user_name";
    private static final String PREF_REGULAR_PASSCODE = "regular_passcode";
    private static final String PREF_IS_LOGIN = "is_login";

    public PreferencesUtils(Context context) {
        if (mSharedPref == null)
            mSharedPref = context.getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);
    }

    public void clearAllPreferences() {
        mSharedPref.edit().clear().apply();
    }

    /**
     * Read & Write different types data to Shared Preference
     */
    // String
    private void write(String key, String value) {
        Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value).apply();
    }

    private String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    // Integer
    private void write(String key, Integer value) {
        Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).apply();
    }

    private Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    //Boolean
    private void write(String key, boolean value) {
        Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value).apply();
    }

    private boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    /**
     * Getting & Setting data to SharedPreference, according to key-value.
     */
    public void setUserName(String value) {
        write(PREF_USER_NAME, value);
    }

    public String getUserName() {
        return read(PREF_USER_NAME, "");
    }

    public void setRegularPasscode(int value) {
        write(PREF_REGULAR_PASSCODE, value);
    }

    public int getRegularPasscode() {
        return read(PREF_REGULAR_PASSCODE, 0);
    }

    public void setIsLogin(boolean value) {
        write(PREF_IS_LOGIN, value);
    }

    public boolean isLogin() {
        return read(PREF_IS_LOGIN, false);
    }
}
