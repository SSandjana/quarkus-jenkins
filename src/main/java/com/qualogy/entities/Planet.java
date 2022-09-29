package com.qualogy.entities;

public class Planet {

    private String name;
    private int distanceFromEarth;

    public Planet() {
    }

    public Planet(String name, int distanceFromEarth) {
        this.name = name;
        this.distanceFromEarth = distanceFromEarth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistanceFromEarth() {
        return distanceFromEarth;
    }

    public void setDistanceFromEarth(int distanceFromEarth) {
        this.distanceFromEarth = distanceFromEarth;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", distanceFromEarth=" + distanceFromEarth +
                '}';
    }
}
