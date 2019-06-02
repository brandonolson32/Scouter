package com.example.scouter.entity;

import android.icu.text.DecimalFormat;

public class LifeForm implements Comparable<LifeForm> {

    protected String name;
    private double powerLevel;
    private String saga;

    public LifeForm(String name, double powerLevel) {
        this.name = name;
        this.powerLevel = powerLevel;
    }

    public LifeForm(String name, double powerLevel, String saga) {
        this.name = name;
        this.powerLevel = powerLevel;
        this.saga = saga;
    }

    public String getName() {
        return name;
    }

    public double getPowerLevel() {
        return powerLevel;
    }

    public String getSaga() {
        return saga;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        return result;
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
        if (lf == null) {
            return 0;
        }
        if (this.powerLevel > lf.powerLevel) {
            return 1;
        } else if (this.powerLevel == lf.powerLevel) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#,###,###");

        if (saga == null || saga.equals("")) {
            return this.name + " - " + formatter.format(powerLevel);
        }
        return this.saga + ": " + this.name + " - " + formatter.format(powerLevel);
    }

    // returns [lowercase letters in name]_[lowercase letters in saga]
    public String imageName() {
        return this.name.replaceAll("[^A-Za-z]+", "").toLowerCase() + "_"
                + this.saga.replaceAll("[^A-Za-z]+", "").toLowerCase();
    }
}
