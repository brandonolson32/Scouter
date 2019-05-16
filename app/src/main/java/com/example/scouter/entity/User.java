package com.example.scouter.entity;

public class User extends LifeForm {
    private int squat;
    private int bench;
    private int deadlift;

    public User(String name, int s, int b, int d) {
        super(name, Scouter.computePowerLevel(s, b, d));
        squat = s;
        bench = b;
        deadlift = d;
    }

    public int getSquat() {
        return this.squat;
    }
    public int getBench() {
        return this.bench;
    }
    public int getDeadlift() {
        return this.deadlift;
    }

}
