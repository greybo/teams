package com.example.sbotlevskyi.teams.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    private String number;
    private String name;
    private boolean isGoalkeeper;


    public Player(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public Player(String number, String name, boolean isGoalkeeper) {
        this.number = number;
        this.name = name;
        this.isGoalkeeper = isGoalkeeper;
    }

    protected Player(Parcel in) {
        number = in.readString();
        name = in.readString();
        isGoalkeeper = in.readByte() == 1;
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

    public boolean isGoalkeeper() {
        return isGoalkeeper;
    }

    public void setGoalkeeper(boolean goalkeeper) {
        isGoalkeeper = goalkeeper;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(number);
        parcel.writeString(name);
        parcel.writeByte((byte) (isGoalkeeper ? 1 : 0));
    }
}
