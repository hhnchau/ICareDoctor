package vn.ptt.apicontroller.sys;

public interface Callback<T> {
    void response(T obj);
}
