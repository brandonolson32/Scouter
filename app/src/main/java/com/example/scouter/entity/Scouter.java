package com.example.scouter.entity;
import com.example.scouter.model.Repository;

import java.util.List;

import static java.lang.Math.pow;

/**
 * This class is a service provider that computes the User's powerLevel
 */

public class Scouter {
    public static double computePowerLevel(double squat, double bench, double deadlift) {
        double deviation = (pow(970 - squat, 2) + pow(551 - bench, 2)
                + pow(904 - deadlift, 2))/206.1717;
        double userPL;
        if (squat < 970 && bench < 551 && deadlift < 904) {
            userPL = Math.round(10000 - deviation);
            if (userPL < 8000) {
                double percentile = userPL / 8000;
                double lower = Repository.getLifeForms().get((int) Math.round(100 * percentile)).getPowerLevel();
                double higher = Repository.getLifeForms().get((int) Math.round(100 * percentile + 1)).getPowerLevel();
                userPL = lower + percentile * (higher - lower);
            }
            return userPL;
        } else {
            return Math.round(10000 + deviation);
        }
    }
}
