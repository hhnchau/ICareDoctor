package vn.ptt.utils;

import android.content.Context;
import android.provider.Settings;

public class Utils {
    public static String getIdDevice(Context context){
       return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}
