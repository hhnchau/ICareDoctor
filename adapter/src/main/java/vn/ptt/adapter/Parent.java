package vn.ptt.adapter;

import android.os.Parcelable;

import java.util.List;

public class Parent<T extends Parcelable> {
    private boolean isExpandable;
    private List<T> lstChild;
    private String parentName;

    public List<T> getLstChild() {
        return lstChild;
    }

    public void setLstChild(List<T> lstChild) {
        this.lstChild = lstChild;
    }

    public boolean hasChild() {
        return lstChild != null && lstChild.size() > 0;
    }

    public int countChild() {
        return lstChild.size();
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
