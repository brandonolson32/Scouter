package com.example.scouter.model;

import com.example.scouter.entity.LifeForm;
import com.example.scouter.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import android.icu.text.DecimalFormat;

/**
 * This class is an abstraction of the data storage for the business classes
 */
public class Repository {

    private static int next_id = 1;
    private User user;
    private static List<LifeForm> lifeForms = new ArrayList<>();
    private static List<LifeForm> encounteredLifeForms = new ArrayList<>();

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

    public List<LifeForm> getLifeformsList() {
        return lifeForms;
    }

    /**
     * Stores characters in the array
     */
    public void generateCharacters() {
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
                // Prevents a duplicates from being added
                if (lifeForm.getName().equals("Raditz") && lifeForm.getPowerLevel() == 1500) {
                    continue;
                }
                if (lifeForm.getName().equals("Turles") && lifeForm.getPowerLevel() == 19000) {
                    continue;
                }
                // End of duplicate prevention
                lifeForms.add(lifeForm);
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

    private void shuffle() {
        Collections.sort(lifeForms);
        Map<Double, List<LifeForm>> repeatPLs = new HashMap<>();
        List<LifeForm> duplicatePLLifeForms = new ArrayList<>();
        LifeForm current = lifeForms.get(0);

        for (int i = 1; i < lifeForms.size(); i++) {
            LifeForm lifeForm = lifeForms.get(i);
            if (current.getPowerLevel() == lifeForm.getPowerLevel()) {
                if (duplicatePLLifeForms.size() == 0) {
                    duplicatePLLifeForms.add(current);
                    duplicatePLLifeForms.add(lifeForm);
                } else {
                    duplicatePLLifeForms.add(lifeForm);
                }
            } else {
                if (duplicatePLLifeForms.size() > 0) {
                    repeatPLs.put(current.getPowerLevel(), duplicatePLLifeForms);
                    duplicatePLLifeForms = new ArrayList<>();
                }
                current = lifeForm;
            }
        }


        List<LifeForm> finalLifeForms = new ArrayList<>();

        for (int i = 0; i < lifeForms.size(); i++) {
            LifeForm lf = lifeForms.get(i);
            double pl = lf.getPowerLevel();

            if (repeatPLs.containsKey(pl)) {
                List<LifeForm> repeats = repeatPLs.get(pl);
                Collections.shuffle(repeats);

                for (int j = 0; j < repeats.size(); j++) {
                    if (!finalLifeForms.contains(repeats.get(j))) {
                        finalLifeForms.add(repeats.get(j));
                    }
                }
//                i += repeats.size() - 1;
            } else if (!finalLifeForms.contains(lf)) {
                finalLifeForms.add(lf);
            }
        }

        lifeForms.clear();

        lifeForms = finalLifeForms;
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
            if (lifeForm instanceof User) {
                iterator.remove();
            }
        }
        int id = Repository.getNextUniqueID();
        user.setId(id);
        lifeForms.add(user);
        this.user = user;
        lifeForms.add(new LifeForm("The Mountain", 10000,
                "Game of Thrones"));
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
        List<LifeForm> weakerAndStrongerLifeForms = new ArrayList<>();
        int index = lifeForms.indexOf(user);
        if (index == 0) {
            weakerAndStrongerLifeForms.add(new LifeForm("Stick", 0,
                    "Wilderness"));
            weakerAndStrongerLifeForms.add(lifeForms.get(1));
        } else {
            weakerAndStrongerLifeForms.add(lifeForms.get(index - 1));
            weakerAndStrongerLifeForms.add(lifeForms.get(index + 1));
        }
        user.setWeakerAndStrongerFoes(weakerAndStrongerLifeForms);
    }

    public void setEncountered(List<LifeForm> encountered) {
        encounteredLifeForms = encountered;
        Collections.sort(encounteredLifeForms);
    }

    public List<LifeForm> getEncounteredLifeForms() {
        return encounteredLifeForms;
    }
}
