package com.example.scouter.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is an abstraction of the data storage for the business classes
 */
public class Repository {

    private static int next_id = 1;

    /***
     * Generate unique numbers to be used as keys
     */
    private static int getNextUniqueID() {
        next_id = next_id + 1;
        return next_id;
    }
}
