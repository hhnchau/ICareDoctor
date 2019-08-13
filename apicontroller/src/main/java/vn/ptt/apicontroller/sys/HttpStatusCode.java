package vn.ptt.apicontroller.sys;

import android.content.Context;

import retrofit2.Response;

public class HttpStatusCode {
    private static final int Ok = 200;
    private static final int Unauthorized = 401;

    public static boolean check(Context context, Response response) {
        if (response.code() == Ok)
            return true;
        else if (response.code() == Unauthorized) {
            //Alert.getInstance().show(context);
            return false;
        } else {
            //Alert.getInstance().show(context);
            return false;
        }
    }
}
