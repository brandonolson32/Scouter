package com.example.scouter.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.ColorSpace;
import android.support.annotation.NonNull;

import com.example.scouter.entity.Character.LifeForm;
import com.example.scouter.entity.User;
import com.example.scouter.model.Model;
import com.example.scouter.model.UserInteractor;

import java.util.List;

/**
 * Class used to edit the User
 */
public class EditUserViewModel extends AndroidViewModel {

    private UserInteractor interactor;

    /**
     * Constructor for EditUserViewModel - calls constructor of superclass
     * @param application the current application
     */
    public EditUserViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getUserInteractor();
    }

    /**
     * Updates the current User
     * @param user the User to update
     */
    public void updateUser(User user) {
        interactor.updateUser(user);
    }

    /**
     * Adds a user
     * @param user the User to add
     */
    public void addUser(User user) {
        interactor.addUser(user);
        System.out.println(user);
        System.out.println("added user");
    }

    /**
     * Gets the current User
     * @return user the current User
     */
    public User getUser() {
        return interactor.getUser();
    }

    /**
     * Gets the User's id
     * @return int the User's id
     */
    public int getId() { return interactor.getId(); }

    /**
     * Gets the User's name
     * @return String the User's name
     */
    public String getName() { return interactor.getName(); }

    /**
     * Gets the User's power level
     * @return int the User's power level
     */
    public double getPowerLevel() { return interactor.getPowerLevel(); }

    /**
     * Sets the User's name
     * @param name the name to assign to the User
     */
    public void setName(String name) { interactor.setName(name); }

    /**
     * Sets the User's id to the input id
     * @param id the new id to assign to the User
     */
    public void setId(int id) {
        interactor.setId(id);
    }

    public LifeForm comparableLifeForm() {
        return interactor.getComparableLifeForm();
    }
}
