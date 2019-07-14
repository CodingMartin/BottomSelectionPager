package com.longtu.wanya;

import android.os.Parcel;

import com.longtu.wanya.bottomselection.BottomCommonSelection;

public class TestBean implements BottomCommonSelection {

    public String title;
    public String id;


    public TestBean(String title, String id) {
        this.title = title;
        this.id = id;
    }

    @Override
    public String getKey() {
        return id;
    }

    @Override
    public int getIcon() {
        return 0;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubTitle() {
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.id);
    }

    protected TestBean(Parcel in) {
        this.title = in.readString();
        this.id = in.readString();
    }

    public static final Creator<TestBean> CREATOR = new Creator<TestBean>() {
        @Override
        public TestBean createFromParcel(Parcel source) {
            return new TestBean(source);
        }

        @Override
        public TestBean[] newArray(int size) {
            return new TestBean[size];
        }
    };
}
