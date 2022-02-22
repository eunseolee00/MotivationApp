package com.example.motivationapp;

public class course {
    private String name;
    private int startHour;
    private int startMin;

    private int endHour;
    private int endMin;

    boolean[] week;

    course(){
        name = "";

        startHour = 0;
        startMin = 0;

        endHour = 0;
        endMin = 0;
        week = new boolean[5];
    }

    public int getEndHour() {
        return endHour;
    }

    public int getEndMin() {
        return endMin;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getStartMin() {
        return startMin;
    }

    public String getName() {
        return name;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setEndMin(int endMin) {
        this.endMin = endMin;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setStartMin(int startMin) {
        this.startMin = startMin;
    }

    public void setName(String name) {
        this.name = name;
    }
}
