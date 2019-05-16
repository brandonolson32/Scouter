package com.example.scouter.entity.Character;

public class LifeForm {

    private String name;
    private double powerLevel;

    public LifeForm (String name, double powerLevel) {
        this.name = name;
        this.powerLevel = powerLevel;
    }

    public String getName() {
        return name;
    }
}
