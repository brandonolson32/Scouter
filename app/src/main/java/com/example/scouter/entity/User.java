package com.example.scouter.entity;

import com.example.scouter.entity.Character.LifeForm;

public class User extends LifeForm {
    private int id;
    private int squat;
    private int bench;
    private int deadlift;

    public User(String name, int s, int b, int d) {
        super(name, Scouter.computePowerLevel(s, b, d));
        squat = s;
        bench = b;
        deadlift = d;
    }

    /**
     * Gets the LifeForm's id
     * @return int the id of the LifeForm
     */
    public int getId() { return id; }

    /**
     * Sets the LifeForm's id to the input id
     * @param id to assign to the LifeForm
     */
    public void setId(int id) {
        this.id = id;
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
