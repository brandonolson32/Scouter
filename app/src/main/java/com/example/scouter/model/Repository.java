package com.example.scouter.model;

import com.example.scouter.entity.LifeForm;
import com.example.scouter.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

//        lifeForms.clear();

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

                String name = lineArr[1];
                double powerLevel = 0;

                try {
                    powerLevel = DecimalFormat.getNumberInstance()
                            .parse(lineArr[2]).doubleValue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String saga = lineArr[0];
                LifeForm lifeForm = new LifeForm(name, powerLevel, saga);
                lifeForms.add(lifeForm);
//                System.out.println(lifeForm.toString());
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
        shuffle();
    }

    public void shuffle() {
        for (int i = 0; i < lifeForms.size(); i++) {
            System.out.println(lifeForms.get(i));
        }

        LifeForm curr = lifeForms.get(0);
        for (int i = 1; i < lifeForms.size(); i++) {
            if (curr.getPowerLevel() == lifeForms.get(i).getPowerLevel()) {
                Random rand = new Random();
                int flip = rand.nextInt(2);
                if (flip == 0) {
                    lifeForms.set(i - 1, lifeForms.get(i));
                    lifeForms.set(i, curr);
                }
                curr = lifeForms.get(i);
            }
        }

        for (int i = 0; i < lifeForms.size(); i++) {
            System.out.println(lifeForms.get(i));
        }


//        List<LifeForm> duplicatePLLifeForms = new ArrayList<>();
//        for (LifeForm lifeForm : lifeForms) {
//            for (LifeForm lifeForm2 : lifeForms) {
//                if (lifeForm.equals(lifeForm2)) {
//                    continue;
//                } else if (lifeForm.getPowerLevel() == lifeForm2.getPowerLevel()) {
//                    duplicatePLLifeForms.add(lifeForm);
//                }
//            }
//        }
//
//
//
//        for (LifeForm lifeForm : duplicatePLLifeForms) {
//            System.out.println(lifeForm.toString());
//        }
//
//
//
//
//
//        Map<Double, List<LifeForm>> repeatPLs = new HashMap<>();
//
//        for (LifeForm lifeForm : duplicatePLLifeForms) {
//            if (!repeatPLs.containsKey(lifeForm.getPowerLevel())) {
//                repeatPLs.put(lifeForm.getPowerLevel(), new ArrayList<LifeForm>());
//                repeatPLs.get(lifeForm.getPowerLevel()).add(lifeForm);
//            } else {
//                repeatPLs.get(lifeForm.getPowerLevel()).add(lifeForm);
//            }
//        }
//
//        List<LifeForm> finalLifeForms = new ArrayList<>();
//
//        for (LifeForm lifeForm : lifeForms) {
//            if (!duplicatePLLifeForms.contains(lifeForm) && !finalLifeForms.contains(lifeForm)) {
//                finalLifeForms.add(lifeForm);
//            } else {
//                List<LifeForm> duplicateList = repeatPLs.get(lifeForm.getPowerLevel());
//
//                Random rand = new Random();
//                int randNumbs = duplicateList.size();
//
//                while (!repeatPLs.get(lifeForm.getPowerLevel()).isEmpty()) {
//                    int index = rand.nextInt(randNumbs);
//                    LifeForm tempLifeForm = duplicateList.get(index);
//                    if (!finalLifeForms.contains(tempLifeForm)) {
//                        finalLifeForms.add(tempLifeForm);
//                        repeatPLs.get(lifeForm.getPowerLevel()).remove(tempLifeForm);
//                    }
//                }
//            }
//        }
//
//        lifeForms = finalLifeForms;
//
//        for (LifeForm lifeForm : lifeForms) {
//            System.out.println(lifeForm.toString());
//        }
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

        for (Iterator<LifeForm> iterator = lifeForms.iterator(); iterator.hasNext();) {
            LifeForm lifeForm = iterator.next();
            if(lifeForm instanceof User) {
                iterator.remove();
            }
        }
        int id = Repository.getNextUniqueID();
        user.setId(id);
        lifeForms.add(user);
        this.user = user;
        shuffle();
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
        List<LifeForm> weakerAndStrongerLifeForms = new ArrayList<>();
        int index = lifeForms.indexOf(user);
        if (index == 0) {
            weakerAndStrongerLifeForms.add(new LifeForm("Tardigrade", 0, "Goeze Saga"));
            weakerAndStrongerLifeForms.add(lifeForms.get(1));
        } else if (index == lifeForms.size() - 1) {
            weakerAndStrongerLifeForms.add(lifeForms.get(lifeForms.size() - 1));
            weakerAndStrongerLifeForms.add(new LifeForm("Polycephabrick", Double.POSITIVE_INFINITY, "Brick Boys"));
        } else {
            weakerAndStrongerLifeForms.add(lifeForms.get(index - 1));
            weakerAndStrongerLifeForms.add(lifeForms.get(index + 1));
        }
        user.setWeakerAndStrongerFoes(weakerAndStrongerLifeForms);
    }
}
