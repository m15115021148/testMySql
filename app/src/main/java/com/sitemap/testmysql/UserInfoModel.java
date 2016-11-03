package com.sitemap.testmysql;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfoModel implements Parcelable{
    private String userNick;
    private String userAge;
    private String userSex;
    private String userLove;

    public UserInfoModel(){}

    protected UserInfoModel(Parcel in) {
        userNick = in.readString();
        userAge = in.readString();
        userSex = in.readString();
        userLove = in.readString();
    }

    public static final Creator<UserInfoModel> CREATOR = new Creator<UserInfoModel>() {
        @Override
        public UserInfoModel createFromParcel(Parcel in) {
            return new UserInfoModel(in);
        }

        @Override
        public UserInfoModel[] newArray(int size) {
            return new UserInfoModel[size];
        }
    };

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserLove() {
        return userLove;
    }

    public void setUserLove(String userLove) {
        this.userLove = userLove;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userNick);
        dest.writeString(userAge);
        dest.writeString(userSex);
        dest.writeString(userLove);
    }
}
