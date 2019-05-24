package com.example.scouter.model;

import com.example.scouter.entity.Character.Farmer;
import com.example.scouter.entity.Character.Frieza;
import com.example.scouter.entity.Character.Goku;
import com.example.scouter.entity.Character.Krillin;
import com.example.scouter.entity.Character.LifeForm;
import com.example.scouter.entity.Character.MrPopo;
import com.example.scouter.entity.Character.Nail;
import com.example.scouter.entity.Character.Piccolo;
import com.example.scouter.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This class is an abstraction of the data storage for the business classes
 */
public class Repository {

    private static int next_id = 1;
    private User user;
    private static List<LifeForm> lifeForms;

    /**
     * Make a new Repository object
     */
    public Repository() {
        this.generateCharacters();
    }

    /***
     * Generate unique numbers to be used as keys
     */
    private static int getNextUniqueID() {
        next_id = next_id + 1;
        return next_id;
    }

    /**
     * Make a new Repository object
     */
    public Repository() {
        lifeForms = generateCharacters();
    }

    /**
     * Stores characters in the array
     */
    public static List<LifeForm> generateCharacters() {
        ArrayList<LifeForm> lifeForms = new ArrayList<>();
        Map<String, ArrayList<LifeForm>> scrapedMap = WebScrape.webScrape();

        for (String saga : scrapedMap.keySet()) {
            for (LifeForm lf : scrapedMap.get(saga)) {
                LifeForm newLifeForm =
                        new LifeForm(lf.getName(), lf.getPowerLevel(), saga);
                lifeForms.add(newLifeForm);
            }
        }
        lifeForms.add(user);
        Collections.sort(lifeForms);
        for (LifeForm lifeForm : lifeForms) {
            System.out.println(lifeForm.toString());
        }
        return lifeForms;
    }

    public static List<LifeForm> getLifeForms() {
        return lifeForms;
    }
    /**
     * get the user
     * @return the user of the app
     */
    public User getUser() {
        return user;
    }

    public void addUser(User user) {
        int id = Repository.getNextUniqueID();
        user.setId(id);
        lifeForms.add(user);
        this.user = user;
    }

    public void updateUser(User user) {
        this.user = user;
    }

    /**
     * Gets the User's name
     * @return String the name of the User
     */
    public String getUserName() {
        return user.getName();
    }

    /**
     * Gets the current User's id
     * @return int the id of the user
     */
    public int getUserId() {
        return user.getId();
    }

    /**
     * Gets the User's power level
     * @return int the power level of the user
     */
    public double getUserPowerLevel() {
        return user.getPowerLevel();
    }

    /**
     * Sets the User's name to the input name
     * @param name the name to assign to the User
     */
    public void setUserName(String name) {
        user.setName(name);
    }

    /**
     * Sets the User's ID
     * @param id The id of the user
     */
    public void setId(int id) {
        user.setId(id);
    }

    public void getWeakerStronger() {
        this.generateCharacters();
        //int index = lifeForms.indexOf(user);
        int index = 35;
        if (index == 0) {
            user.setStrongerFoe(lifeForms.get(0));
            user.setWeakerFoe(new LifeForm("Tardigrade", 0, "Goeze Saga"));
        } else if (index == lifeForms.size()) {
            user.setStrongerFoe(new LifeForm("Polycephabrick", Double.POSITIVE_INFINITY, "Brick Boys"));
            user.setWeakerFoe(lifeForms.get(lifeForms.size() - 1));
        } else {
            user.setStrongerFoe(lifeForms.get(index + 1));
            user.setWeakerFoe(lifeForms.get(index - 1));
        }
    }
}
