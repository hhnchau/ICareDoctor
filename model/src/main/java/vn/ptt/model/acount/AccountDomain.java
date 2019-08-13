package vn.ptt.model.acount;

import android.os.Parcel;
import android.os.Parcelable;

public class AccountDomain implements Parcelable {
    private String idUser;

    private String password;

    private String deviceId;

    private String nickName;

    private int active;

    private String datecr;

    public AccountDomain() {
    }

    protected AccountDomain(Parcel in) {
        idUser = in.readString();
        password = in.readString();
        deviceId = in.readString();
        nickName = in.readString();
        active = in.readInt();
        datecr = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idUser);
        dest.writeString(password);
        dest.writeString(deviceId);
        dest.writeString(nickName);
        dest.writeInt(active);
        dest.writeString(datecr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AccountDomain> CREATOR = new Creator<AccountDomain>() {
        @Override
        public AccountDomain createFromParcel(Parcel in) {
            return new AccountDomain(in);
        }

        @Override
        public AccountDomain[] newArray(int size) {
            return new AccountDomain[size];
        }
    };

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDatecr() {
        return datecr;
    }

    public void setDatecr(String datecr) {
        this.datecr = datecr;
    }
}
