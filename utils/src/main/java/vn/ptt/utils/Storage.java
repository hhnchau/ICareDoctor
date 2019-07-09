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

    public void setUserName(String userName) {
        editor.putString("user-name", userName);
        editor.commit();
    }

    public String getUserName() {
        return preferences.getString("user-name", "");
    }

}
