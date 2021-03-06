package com.example.scouter.model;

/**
 * Basic Interactor class
 */
public class Interactor {

    private Repository myRepo;

    protected Interactor(Repository repo) {
        myRepo = repo;
    }

    protected Repository getRepo() {
        return myRepo;
    }
}
