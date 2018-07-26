package com.example.sbotlevskyi.teams.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Teams implements Parcelable {

    private String nameTeam1;
    private String nameTeam2;
    private String scoreTeam1;
    private String scoreTeam2;
    private ArrayList<Player> playersTeam1;
    private ArrayList<Player> playersTeam2;
    private int[] sequenceTeam1;
    private int[] sequenceTeam2;

    public Teams(String nameTeam1, String nameTeam2, String scoreTeam1, String scoreTeam2,
                 ArrayList<Player> playersTeam1, ArrayList<Player> playersTeam2) {
        this.nameTeam1 = nameTeam1;
        this.nameTeam2 = nameTeam2;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.playersTeam1 = playersTeam1;
        this.playersTeam2 = playersTeam2;
    }

    protected Teams(Parcel in) {
        nameTeam1 = in.readString();
        nameTeam2 = in.readString();
        scoreTeam1 = in.readString();
        scoreTeam2 = in.readString();
        playersTeam1 = in.readArrayList(Player.class.getClassLoader());
        playersTeam2 = in.readArrayList(Player.class.getClassLoader());
        sequenceTeam1= in.createIntArray();
        sequenceTeam2= in.createIntArray();
    }

    public static final Creator<Teams> CREATOR = new Creator<Teams>() {
        @Override
        public Teams createFromParcel(Parcel in) {
            return new Teams(in);
        }

        @Override
        public Teams[] newArray(int size) {
            return new Teams[size];
        }
    };

    public String getNameTeam1() {
        return nameTeam1;
    }

    public void setNameTeam1(String nameTeam1) {
        this.nameTeam1 = nameTeam1;
    }

    public String getNameTeam2() {
        return nameTeam2;
    }

    public void setNameTeam2(String nameTeam2) {
        this.nameTeam2 = nameTeam2;
    }

    public String getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(String scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public String getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(String scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public ArrayList<Player> getPlayersTeam1() {
        return playersTeam1;
    }

    public void setPlayersTeam1(ArrayList<Player> playersTeam1) {
        this.playersTeam1 = playersTeam1;
    }

    public ArrayList<Player> getPlayersTeam2() {
        return playersTeam2;
    }

    public void setPlayersTeam2(ArrayList<Player> playersTeam2) {
        this.playersTeam2 = playersTeam2;
    }

    public int[] getSequenceTeam1() {
        return sequenceTeam1;
    }

    public void setSequenceTeam1(int[] sequenceTeam1) {
        this.sequenceTeam1 = sequenceTeam1;
    }

    public int[] getSequenceTeam2() {
        return sequenceTeam2;
    }

    public void setSequenceTeam2(int[] sequenceTeam2) {
        this.sequenceTeam2 = sequenceTeam2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameTeam1);
        parcel.writeString(nameTeam2);
        parcel.writeString(scoreTeam1);
        parcel.writeString(scoreTeam2);
        parcel.writeList(playersTeam1);
        parcel.writeList(playersTeam2);
        parcel.writeIntArray(sequenceTeam1);
        parcel.writeIntArray(sequenceTeam2);
    }
}
