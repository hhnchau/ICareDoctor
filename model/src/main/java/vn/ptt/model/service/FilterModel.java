package vn.ptt.model.service;

import android.support.annotation.NonNull;

import java.util.List;

public class FilterModel {
    private int offset;
    private int limit;
    private String method;
    private List<Para> lstPara;

    public FilterModel(int _offset, int _limit, Method _method, List<Para> _lstPara) {
        offset = _offset;
        limit = _limit;
        method = _method.name();
        lstPara = _lstPara;
    }

    public FilterModel(int _offset, int _limit, List<Para> _lstPara) {
        offset = _offset;
        limit = _limit;
        lstPara = _lstPara;
    }

    public FilterModel(List<Para> _lstPara) {
        lstPara = _lstPara;
    }

}
