package com.example.scouter.entity;
import static java.lang.Math.pow;

public class Scouter {
    public double computerPowerLevel(int squat, int bench, int deadlift) {
        if (squat < 0 || bench < 0 || deadlift < 0) {
            return -1.0;
        } else if (squat > pow(10, 5) || bench > pow(10, 5) || deadlift > pow(10, 5)) {
            return pow(10, 9);
        } else if (squat < 970 && bench < 540 && deadlift < 1041) {
            return 10000 - (pow(970 - squat, 2) + pow(540 - bench, 2) + pow(1041 - deadlift, 2))/223.25;
        } else {
            return 10000 + (pow(970 - squat, 2) + pow(540 - bench, 2) + pow(1041 - deadlift, 2))/223.25;
        }
    }
}
