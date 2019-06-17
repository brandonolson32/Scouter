package com.example.scouter.entity;
import static java.lang.Math.pow;

/**
 * This class is a service provider that computes the User's powerLevel
 */

public class Scouter {
    public static double computePowerLevel(double squat, double bench, double deadlift) {
        if (squat < 970 && bench < 540 && deadlift < 1041) {
            return Math.round(10000 - (pow(970 - squat, 2) + pow(540 - bench, 2)
                    + pow(1000 - deadlift, 2))/223.25);
        } else {
            return Math.round(10000 + (pow(970 - squat, 2) + pow(540 - bench, 2)
                    + pow(1000 - deadlift, 2))/223.25);
        }
    }
}
