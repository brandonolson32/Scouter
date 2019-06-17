package com.example.scouter.entity;

import java.util.List;

public class User extends LifeForm {
    private int id;
    private double squat;
    private double bench;
    private double deadlift;
    private List<LifeForm> weakerAndStrongerFoes;

    public User(String name, double s, double b, double d) {
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

    /**
     * Sets the name of a User to the input name
     * @param name the name to set the User's to
     */
    public void setName(String name) { this.name = name; }

    public double getSquat() {
        return this.squat;
    }
    public double getBench() {
        return this.bench;
    }
    public double getDeadlift() {
        return this.deadlift;
    }
    public List<LifeForm> getWeakerAndStrongerFoes() { return this.weakerAndStrongerFoes; }
    public void setWeakerAndStrongerFoes(List<LifeForm> weakerAndStrongerFoes) { this.weakerAndStrongerFoes = weakerAndStrongerFoes; }
}
