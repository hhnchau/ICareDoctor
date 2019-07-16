package vn.ptt.icaredoctor;

import android.app.Application;

import java.util.Map;
import java.util.Objects;

import vn.ptt.apicontroller.ApiController;
import vn.ptt.apicontroller.sys.Api;
import vn.ptt.apicontroller.sys.Callback;
import vn.ptt.model.sysapi.UrlModel;

public class MyApplication extends Application {
    private static Map<String, UrlModel> urlModelMap;

    public static Map<String, UrlModel> getUrlModelMap() {
        return urlModelMap;
    }

    public void setUrlModelMap(Map<String, UrlModel> map) {
        urlModelMap = map;
    }

    public static String getUrl(String key) {
        if (getUrlModelMap() != null && getUrlModelMap().get(key) != null) {
            return Objects.requireNonNull(getUrlModelMap().get(key)).getFullpath();
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Api.getInstance().create();


        ApiController.getInstance().getSysApi(new Callback() {
            @SuppressWarnings("unchecked")
            @Override
            public void response(Object obj) {
                setUrlModelMap((Map<String, UrlModel>) obj);
            }
        });

    }
}
