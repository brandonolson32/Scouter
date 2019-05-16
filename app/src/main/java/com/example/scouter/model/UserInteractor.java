package com.example.scouter.model;

import com.example.scouter.entity.User;

/**
 * This class is the interactor for the User
 */
public class UserInteractor extends Interactor {

    private User user;
    private Repository repo;

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
}
