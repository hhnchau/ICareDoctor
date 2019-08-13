package vn.ptt.model;

import java.util.List;

import vn.ptt.model.service.Para;

public class RequestModel {
    private int intId;
    private String stringId;
    private int limit;
    private int offset;
    private List<Para> lstPara;

    public RequestModel(Builder builder) {
        this.intId = builder.intId;
        this.stringId = builder.stringId;
        this.limit = builder.limit;
        this.offset = builder.offset;
        this.lstPara = builder.lstPara;
    }

    public int getIntId() {
        return intId;
    }

    public String getStringId() {
        return stringId;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public List<Para> getLstPara() {
        return lstPara;
    }

    public static class Builder {
        private int intId;
        private String stringId;
        private int limit;
        private int offset;
        private List<Para> lstPara;

        public Builder intId(int id) {
            this.intId = id;
            return this;
        }

        public Builder stringId(String id) {
            this.stringId = id;
            return this;
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder lstPara(List<Para> lstPara) {
            this.lstPara = lstPara;
            return this;
        }

        public RequestModel build() {
            return new RequestModel(this);
        }
    }
}
