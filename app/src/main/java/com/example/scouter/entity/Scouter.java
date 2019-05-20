package com.example.scouter.entity;
import static java.lang.Math.pow;

/**
 * This class is a service provider that computes the User's powerLevel
 */
public class Scouter {
    public static int computePowerLevel(int squat, int bench, int deadlift) {
        if (squat < 0 || bench < 0 || deadlift < 0) {
            return -1;
        } else if (squat > pow(10, 5) || bench > pow(10, 5) || deadlift > pow(10, 5)) {
            return (int) pow(10, 9);
        } else if (squat < 970 && bench < 540 && deadlift < 1041) {
            return (int) Math.round(10000 - (pow(970 - squat, 2) + pow(540 - bench, 2)
                    + pow(1041 - deadlift, 2))/223.25);
        } else {
            return (int) Math.round(10000 + (pow(970 - squat, 2) + pow(540 - bench, 2)
                    + pow(1041 - deadlift, 2))/223.25);
        }
    }
}
