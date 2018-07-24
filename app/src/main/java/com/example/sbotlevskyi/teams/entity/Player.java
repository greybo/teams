package com.example.sbotlevskyi.teams.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    public String number;
    public String name;

    public Player(String number, String name) {
        this.number = number;
        this.name = name;
    }

    protected Player(Parcel in) {
        number = in.readString();
        name = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(number);
        parcel.writeString(name);
    }
}
