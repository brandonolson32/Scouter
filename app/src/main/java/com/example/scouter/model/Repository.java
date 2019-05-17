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
    private static List<LifeForm> lifeForms; // All the lifeForms

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
        lifeForms = new ArrayList<>();
    }

    /**
     * Stores characters in the array
     */
    public void generateCharacters() {
        lifeForms.add(new Goku());
        lifeForms.add(new Farmer());
        lifeForms.add(new Frieza());
        lifeForms.add(new Krillin());
        lifeForms.add(new MrPopo());
        lifeForms.add(new Nail());
        lifeForms.add(new Piccolo());
        Collections.sort(lifeForms);
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
     * Sets the User's name to the input name
     * @param name the name to assign to the User
     */
    public void setUserName(String name) {
        user.setName(name);
    }

    /**
     * Gets the current User's id
     * @return int the id of the user
     */
    public int getUserId() {
        return user.getId();
    }

    /**
     * Sets the User's ID
     * @param id The id of the user
     */
    public void setId(int id) {
        user.setId(id);
    }
}
