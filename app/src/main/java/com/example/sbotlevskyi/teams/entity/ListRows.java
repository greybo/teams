package com.example.sbotlevskyi.teams.entity;

public class ListRows {

    private Player player;
    private String nameTeam;
    private int typeTeam;

    public Player getPlayer() {
        return player;
    }

    public ListRows(Player player,int typeTeam) {
        this.player = player;
        this.typeTeam = typeTeam;
    }

    public ListRows(String nameTeam) {
        this.nameTeam = nameTeam;

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public int getTypeTeam() {
        return typeTeam;
    }

    public void setTypeTeam(int typeTeam) {
        this.typeTeam = typeTeam;
    }
}
