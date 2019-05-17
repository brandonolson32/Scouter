package com.example.scouter.model;

/**
 * main model class
 */
public final class Model {

    /** the data repository */
    private final Repository myRepository;

    private final UserInteractor userInteractor;

    /** Singleton Pattern Code
     *  this allows us to get access to this class
     *  anywhere, which will allow our View models to access
     *  the "back end"  more easily
     */
    private static final Model instance = new Model();

    /**
     * returns the current instance of the model
     * @return Model the instance
     */
    public static Model getInstance() { return instance; }

    /**
     * Make a new Model instance
     */
    private Model() {
        myRepository = new Repository();
        userInteractor = new UserInteractor(myRepository);
    }

    /**
     * Returns the current UserInteractor
     * @return UserInteractor current UserInteractor
     */
    public UserInteractor getUserInteractor() {
        return userInteractor;
    }
}
