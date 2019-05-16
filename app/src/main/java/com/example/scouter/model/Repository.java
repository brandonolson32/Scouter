package com.example.scouter.model;

import com.example.scouter.entity.Character.LifeForm;
import com.example.scouter.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is an abstraction of the data storage for the business classes
 */
public class Repository {

    private static int next_id = 1;
    private User user;
    private final List<LifeForm> lifeForms; // All the lifeForms

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
     * get the user
     * @return the user of the app
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the current User's id
     * @return int the id of the user
     */
    public int getUserId() {
        return user.getId(); }

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
}
