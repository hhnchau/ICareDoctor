package vn.ptt.icaredoctor;

import android.app.Application;

import vn.ptt.apicontroller.sys.Api;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Api.getInstance().create();
    }
}
