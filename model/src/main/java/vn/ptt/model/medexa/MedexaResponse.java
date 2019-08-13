package vn.ptt.model.medexa;

import java.util.List;

import vn.ptt.model.BaseModel;

public class MedexaResponse extends BaseModel<MedexaHDomain> {
    public MedexaResponse(int code, List<MedexaHDomain> data) {
        super(code, data);
    }
}
