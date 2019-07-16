package vn.ptt.model.sysapi;

import java.util.List;

import vn.ptt.model.BaseModel;

public class SysApiModel extends BaseModel<UrlModel> {
    public SysApiModel(int code, List<UrlModel> data) {
        super(code, data);
    }
}
