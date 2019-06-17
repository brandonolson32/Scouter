package com.example.scouter.entity;

import com.example.scouter.model.Repository;

import java.util.Collections;
import java.util.List;

public class CharacterSelector {
    private int powerLevel;
    public CharacterSelector(int pl) {
        powerLevel = pl;
    }

    /**
     * This method finds a character from DBZ comparable to the user
     * @param name name of the user
     * @param pl power level of the user
     * @return the character whose power level represents the user
     */
    public LifeForm getComparableLifeForm(String name, int pl) {
        LifeForm user = new LifeForm(name, pl);
        List<LifeForm> characters = Repository.getLifeForms();
        characters.add(user);
        Collections.sort(characters);
        int index = characters.indexOf(user);
        if (index == 0) {
            return characters.get(1);
        } else if (index == characters.size() - 1) {
            return characters.get(characters.size() - 2);
        } else {
            int random = 1 + (int) (Math.random() * (2));
            if (random == 1) {
                return characters.get(index - 1);
            } else {
                return characters.get(index + 1);
            }
        }
    }
}
