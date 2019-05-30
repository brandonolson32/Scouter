package com.example.scouter.model;

import android.util.Log;

import com.example.scouter.entity.LifeForm;
import com.example.scouter.entity.User;
import java.util.List;

/**
 * This class is the interactor for the User
 */
public class UserInteractor extends Interactor {

    private User user;
    private Repository repo;

    /**
     * Constructor for the UserInteractor class
     * Calls the constructor of its superclass, Repository
     * @param repo the current repository
     */
    public UserInteractor(Repository repo) {
        super(repo);
    }

    /**
     * Gets the current User object
     * @return the current User
     */
    public User getUser() {
        repo = getRepo();
        user = repo.getUser();
        return user;
    }

    /**
     * Adds another User instance
     * @param user the User to add
     */
    public void addUser(User user) {
        repo = getRepo();
        repo.addUser(user);
    }

    /**
     * Updates the user object
     * @param user the User to update
     */
    public void updateUser(User user) {
        repo = getRepo();
        repo.updateUser(user);
        Log.i("APP", "Interactor: updating user: " + user);
    }

    /**
     * Gets the User's id
     * @return int the id of the User
     */
    public int getId() {
        repo = getRepo();
        return repo.getUserId();
    }

    /**
     * Gets the User's name
     * @return String the name of the user
     */
    public String getName() {
        repo = getRepo();
        return repo.getUserName();
    }

    /**
     * Gets the User's power level
     * @return int the power level of the user
     */
    public double getPowerLevel() {
        repo = getRepo();
        return repo.getUserPowerLevel();
    }

    /**
     * Sets the user's ID
     * @param id The id of the User
     */
    public void setId(int id) {
        repo = getRepo();
        repo.setId(id);
    }

    /**
     * Sets the User's name to the input name
     * @param name the name to assign to the User
     */
    public void setName(String name) {
        repo = getRepo();
        repo.setUserName(name);
    }

    public void getComparableLifeForm() {
        repo = getRepo();
        repo.getWeakerStronger();
    }

    public List<LifeForm> getWeakerAndStrongerFoes() {
        return user.getWeakerAndStrongerFoes();
    }
}
