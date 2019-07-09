package vn.ptt.apicontroller.sys;

import java.util.List;

public interface LCallback<T> {
    void response(List<T> list);
}
