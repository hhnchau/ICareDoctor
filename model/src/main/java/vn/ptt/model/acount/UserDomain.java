package vn.ptt.model.acount;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class UserDomain implements Parcelable {
    private String idUser;

    private String firstName;

    private String lastName;

    private int gender;

    private String phone;

    private String email;

    private String avatar;

    private String address;

    private String birthday;

    private String datecr;

    public UserDomain() {
    }

    protected UserDomain(Parcel in) {
        idUser = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        gender = in.readInt();
        phone = in.readString();
        email = in.readString();
        avatar = in.readString();
        address = in.readString();
        birthday = in.readString();
        datecr = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idUser);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(gender);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(avatar);
        dest.writeString(address);
        dest.writeString(birthday);
        dest.writeString(datecr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserDomain> CREATOR = new Creator<UserDomain>() {
        @Override
        public UserDomain createFromParcel(Parcel in) {
            return new UserDomain(in);
        }

        @Override
        public UserDomain[] newArray(int size) {
            return new UserDomain[size];
        }
    };

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDatecr() {
        return datecr;
    }

    public void setDatecr(String datecr) {
        this.datecr = datecr;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserDomain{" +
                "idUser='" + idUser + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", datecr='" + datecr + '\'' +
                '}';
    }
}
