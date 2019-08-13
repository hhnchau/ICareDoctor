package vn.ptt.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Storage {
    private static Storage instance;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public static Storage getInstance(Context context) {
        if (instance == null) {
            instance = new Storage();
            preferences = context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE);
            editor = preferences.edit();
            editor.apply();
        }
        return instance;
    }

    public void setIdUser(String idUser) {
        editor.putString("id-user", idUser);
        editor.commit();
    }

    public String getIdUser() {
        return preferences.getString("id-user", "");
    }

    public void setToken(String token) {
        editor.putString("token", token);
        editor.commit();
    }

    public String getToken() {
        return preferences.getString("token", "");
    }

    public void setUserDomain(String userDomain) {
        editor.putString("user-domain", userDomain);
        editor.apply();
    }

    public String getUserDomain() {
        return preferences.getString("user-domain", "");
    }

    public void setPatId(String patId) {
        editor.putString("pat-id", patId);
        editor.commit();
    }

    public String getPatId() {
        return preferences.getString("pat-id", null);
    }
}
