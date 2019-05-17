package com.example.scouter.entity.Character;

public class LifeForm implements Comparable<LifeForm> {

    protected String name;
    private int powerLevel;

    public LifeForm(String name, int powerLevel) {
        this.name = name;
        this.powerLevel = powerLevel;
    }

    public String getName() {
        return name;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    @Override
    public int hashCode() {
        return this.powerLevel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        LifeForm lf = (LifeForm) obj;
        return lf.name.equals(this.name) && lf.powerLevel == this.powerLevel;
    }

    @Override
    public int compareTo(LifeForm lf) {
        if (this.powerLevel > lf.powerLevel) {
            return 1;
        } else if (this.powerLevel == lf.powerLevel) {
            return 0;
        } else {
            return -1;
        }
    }
}
