package com.example.liumeng.quanminfu2.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liumeng on 2016/12/16 on 11:11
 * 序列化例子
 */
public class Studengt implements Parcelable {
    private int age;
    private String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.age);
        dest.writeString(this.name);
    }

    public Studengt() {
    }

    private Studengt(Parcel in) {
        this.age = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Studengt> CREATOR = new Parcelable.Creator<Studengt>() {
        public Studengt createFromParcel(Parcel source) {
            return new Studengt(source);
        }

        public Studengt[] newArray(int size) {
            return new Studengt[size];
        }
    };
}
