package ru.katakin.sample.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.Date;

import ru.katakin.sample.util.Parser;

public class AppPreferences {

    private final String PROPERTY_REG_ID = "registration_id";
    private final String LAST_REG_DATE = "reg_date";
    private final String PROPERTY_APP_VERSION = "app_version";

    private SharedPreferences sharedPreferences;
    private static AppPreferences preferences;

    public static synchronized AppPreferences getInstance(Context context) {
        return preferences != null ? preferences : (preferences = new AppPreferences(context));
    }

    private AppPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("MainData", Context.MODE_PRIVATE);
    }

    private synchronized void saveStringPreference(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (TextUtils.isEmpty(value)) {
            editor.remove(key);
        } else {
            editor.putString(key, value);
        }
        editor.apply();
    }

    private synchronized void saveIntPreference(String key, Integer value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value == null) {
            editor.remove(key);
        } else {
            editor.putInt(key, value);
        }
        editor.apply();
    }

    private synchronized void saveBooleanPreference(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value == null) {
            editor.remove(key);
        } else {
            editor.putBoolean(key, value);
        }
        editor.apply();
    }

    public Date getRegDate() {
        return Parser.stringToDate(sharedPreferences.getString(LAST_REG_DATE, ""));
    }

    public void setRegDate(Date today) {
        saveStringPreference(LAST_REG_DATE, Parser.dateToString(today));
    }

    public String getRegistrationId() {
        return sharedPreferences.getString(PROPERTY_REG_ID, "");
    }

    public void setRegistrationId(String registrationId) {
        saveStringPreference(PROPERTY_REG_ID, registrationId);
    }

    public int getAppVersion() {
        return sharedPreferences.getInt(PROPERTY_APP_VERSION, -1);
    }

    public void setAppVersion(int appVersion) {
        saveIntPreference(PROPERTY_APP_VERSION, appVersion);
    }
}
