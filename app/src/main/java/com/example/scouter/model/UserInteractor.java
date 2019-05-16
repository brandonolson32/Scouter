package com.example.scouter.model;

import android.util.Log;

import com.example.scouter.entity.User;

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
    public void addUser (User user) {
        repo = getRepo();
        repo.addUser(user);
    }

    /**
     * Updates the user object
     * @param user the User to update
     */
    public void updateUser (User user) {
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
}
