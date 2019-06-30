package com.example.scouter.entity;
import com.example.scouter.model.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

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
                List<LifeForm> noDuplicates = new ArrayList<>();
                List<LifeForm> lifeForms = Repository.getLifeForms().subList(0, 99);
                int count = -1;
                for (LifeForm lf : lifeForms) {
                    if (lf instanceof User) {
                        continue;
                    }
                    if (count == -1) {
                        noDuplicates.add(lf);
                        count++;
                    } else {
                        if (noDuplicates.get(count).getPowerLevel() == lf.getPowerLevel()) {
                            continue;
                        } else {
                            count++;
                            noDuplicates.add(lf);
                        }
                    }
                }
                int size = noDuplicates.size();
                double percentile = userPL / 8000;
                double lower = noDuplicates.get((int) (percentile * size)).getPowerLevel();
                double higher = noDuplicates.get((int) (percentile * size + 1)).getPowerLevel();
                userPL = lower + percentile * (higher - lower);
            }
            return userPL;
        } else {
            return Math.round(10000 + deviation);
        }
    }
}
