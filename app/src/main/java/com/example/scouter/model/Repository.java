package com.example.scouter.model;

import com.example.scouter.entity.LifeForm;
import com.example.scouter.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.icu.text.DecimalFormat;

/**
 * This class is an abstraction of the data storage for the business classes
 */
public class Repository {

    private static int next_id = 1;
    private User user;
    private static List<LifeForm> lifeForms = new ArrayList<>();

    /**
     * Make a new Repository object
     */
    public Repository() {
        generateCharacters();
    }

    /***
     * Generate unique numbers to be used as keys
     */
    private static int getNextUniqueID() {
        next_id = next_id + 1;
        return next_id;
    }

    /**
     * Stores characters in the array
     */
    public void generateCharacters() {
//        try {
//            // Create an object of filereader
//            // class with CSV file as a parameter.
//            FileReader filereader = new FileReader(
//                    "DBZ_Database.csv");
//
//            // create csvReader object passing
//            // file reader as a parameter
//            CSVReader csvReader = new CSVReader(filereader);
//            String[] nextLine;
//
//            // headers
//            csvReader.readNext();
//
//            // we are going to read data line by line
//            while ((nextLine = csvReader.readNext()) != null) {
//                String name = nextLine[1];
//                double powerLevel = Double.parseDouble(nextLine[2]);
//                String saga = nextLine[0];
//                LifeForm lifeForm = new LifeForm(name, powerLevel, saga);
//                lifeForms.add(lifeForm);
//            }
//            Collections.sort(lifeForms);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }

        String file = "assets/DBZ_Database.csv";
        InputStream in = getClass().getClassLoader()
                .getResourceAsStream(file);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(in));

            // headers
            reader.readLine();
            // do reading, usually loop until end of file reading
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                String line = nextLine.replace("\"", "");
                String[] lineArr = line.split(",");
//                System.out.println(lineArr[2]);

                String name = lineArr[1];
                double powerLevel = 0;

                try {
                    powerLevel = DecimalFormat.getNumberInstance()
                            .parse(lineArr[2]).doubleValue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                double powerLevel = Double.parseDouble(lineArr[2]);
                String saga = lineArr[0];
                LifeForm lifeForm = new LifeForm(name, powerLevel, saga);
                lifeForms.add(lifeForm);
                System.out.println(lifeForm.toString());
            }
            Collections.sort(lifeForms);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
        generateCharacters();
        for (LifeForm lifeForm : lifeForms) {
            System.out.println(lifeForm.toString());
        }
        int index = lifeForms.indexOf(user);
        if (index == 0) {
            user.setStrongerFoe(lifeForms.get(1));
            user.setWeakerFoe(new LifeForm("Tardigrade", 0, "Goeze Saga"));
        } else if (index == lifeForms.size() - 1) {
            user.setStrongerFoe(new LifeForm("Polycephabrick", Double.POSITIVE_INFINITY, "Brick Boys"));
            user.setWeakerFoe(lifeForms.get(lifeForms.size() - 1));
        } else {
            user.setStrongerFoe(lifeForms.get(index + 1));
            user.setWeakerFoe(lifeForms.get(index - 1));
        }
    }
}
