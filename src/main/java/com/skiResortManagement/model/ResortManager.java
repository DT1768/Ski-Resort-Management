package com.skiResortManagement.model;

import java.util.ArrayList;

public class ResortManager {

    private int resortId;
    private String name;
    private int vertical;
    private ArrayList<Integer> seasons;

    public ResortManager(){

    }

    public ResortManager(int resortId, String name, int vertical, ArrayList<Integer> seasons){
        this.resortId = resortId;
        this.name = name;
        this.vertical = vertical;
        this.seasons = seasons;
    }

    public void setResortId(int resortId) {
        this.resortId = resortId;
    }

    public int getResortId() {
        return resortId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public int getVertical() {
        return vertical;
    }

    public void setSeasons(ArrayList<Integer> seasons){
        this.seasons = seasons;
    }

    public ArrayList<Integer> getSeasons() {
        return seasons;
    }

    public void addSeason(int season){
        this.seasons.add(season);
    }

    @Override
    public String toString() {
        return "{" +
                "resortId = " + resortId  +
                ", name ='" + name + '\'' +
                ", vertical = " + vertical +
                ", seasons =" + seasons +
                '}';
    }
}
