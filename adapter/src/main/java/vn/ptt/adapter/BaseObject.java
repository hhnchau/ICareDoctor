package vn.ptt.adapter;

import android.os.Parcelable;

public class BaseObject<T extends Parcelable>{
    private T data;

    private BaseType type;

    private int resId;

    public BaseObject() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseType getType() {
        return type;
    }

    public void setType(BaseType type) {
        this.type = type;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
