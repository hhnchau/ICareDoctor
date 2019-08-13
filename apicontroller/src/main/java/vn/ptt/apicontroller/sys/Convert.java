package vn.ptt.apicontroller.sys;

import com.google.gson.Gson;

public class Convert {
    public static <T> T s2o(Object fromValue, Class<T> toValueType) {
        return new Gson().fromJson(fromValue.toString(), toValueType);
    }

    public static String o2s(){
        Gson gson = new Gson();
        String s = gson.toJson("");
        return s;
    }
}
