package vn.ptt.model.history;

import java.util.List;

import vn.ptt.model.BaseModel;

public class HistoryClinicResponse extends BaseModel<HistoryClinicDomain> {

    public HistoryClinicResponse(int code, List<HistoryClinicDomain> data) {
        super(code, data);
    }
}
