package com.example.scouter.model;

import android.arch.lifecycle.ViewModelProviders;

import com.example.scouter.entity.Character.Krillin;
import com.example.scouter.entity.Character.LifeForm;
import com.example.scouter.entity.User;
import com.example.scouter.viewmodels.EditUserViewModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brandon and JC
 */


public class WebScrape {

    public static Map<String, ArrayList<LifeForm>> webScrape() {
        Map<String, ArrayList<LifeForm>> motherOfData = new HashMap<>();
        final String url =
                "https://dragonball.fandom.com/wiki/List_of_Power_Levels";

        try {
            final Document document = Jsoup.connect(url).get();
            String saga = "";
            for (Element row : document.select(
                    "table.wikitable tr")) {
                Elements data = row.select("td");
                // if the row contains a character
                if (data.get(0).text().equals("Saga")) {
                    continue;
                } else if (!data.get(0).hasText()) {
                    try {
                        LifeForm newGuy = new LifeForm(data.get(1).text(),
                                Double.parseDouble(data.get(2).text()
                                        .replaceAll("[^-.0123456789]","")));
                        motherOfData.get(saga).add(newGuy);
                    }
                        catch (NumberFormatException e) {}
                } else if (!data.get(1).hasText()) {
                    saga = data.get(0).text();
                    if (saga.equals("Red Ribbon Army Saga")
                            && motherOfData.containsKey(saga)) {
                        break;
                    }
                    motherOfData.put(saga, new ArrayList<LifeForm>());
                } else if (data.get(1).hasText()) {
                    LifeForm newGuy = new LifeForm(data.get(1).text(),
                            Double.parseDouble(data.get(2).text()
                                    .replaceAll("[^0123456789]","")));
                    motherOfData.remove(saga);
                    saga = saga + " " + data.get(0).text();
                    motherOfData.put(saga, new ArrayList<LifeForm>());
                    motherOfData.get(saga).add(newGuy);
                }
            }
            /**
            for (String s: motherOfData.keySet()){
                String key = s;
                String value = motherOfData.get(s).toString();
                System.out.println(key + " " + value);
            }*/
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return motherOfData;
    }

//    public static void assignSagas(Map<String, ArrayList<LifeForm>> motherOfData) {
//        for (Key saga : motherOfData.keySet()) {
//            for (LifeForm being : motherOfData.get(saga)) {
//                being.setSaga(saga);
//            }
//        }
//    }

    public static void main(String[] args) {
            Map<String, ArrayList<LifeForm>> motherOfData = new HashMap<>();
            final String url =
                    "https://dragonball.fandom.com/wiki/List_of_Power_Levels";

            try {
                final Document document = Jsoup.connect(url).get();
                String saga = "";
                for (Element row : document.select(
                        "table.wikitable tr")) {
                    Elements data = row.select("td");
                    // if the row contains a character
                    if (data.get(0).text().equals("Saga")) {
                        continue;
                    } else if (!data.get(0).hasText()) {
                        try {
                            LifeForm newGuy = new LifeForm(data.get(1).text(),
                                    Double.parseDouble(data.get(2).text()
                                            .replaceAll("[^-.0123456789]","")));
                            motherOfData.get(saga).add(newGuy);
                        }
                        catch (NumberFormatException e) {}
                    } else if (!data.get(1).hasText()) {
                        saga = data.get(0).text();
                        if (saga.equals("Red Ribbon Army Saga")
                                && motherOfData.containsKey(saga)) {
                            break;
                        }
                        motherOfData.put(saga, new ArrayList<LifeForm>());
                    } else if (data.get(1).hasText()) {
                        LifeForm newGuy = new LifeForm(data.get(1).text(),
                                Double.parseDouble(data.get(2).text()
                                        .replaceAll("[^0123456789]","")));
                        motherOfData.remove(saga);
                        saga = saga + " " + data.get(0).text();
                        motherOfData.put(saga, new ArrayList<LifeForm>());
                        motherOfData.get(saga).add(newGuy);
                    }
                }
                for (String s: motherOfData.keySet()){
                    String key = s;
                    String value = motherOfData.get(s).toString();
                    System.out.println(key + " " + value);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

        Map<String, ArrayList<LifeForm>> scrapedMap = WebScrape.webScrape();
        List<LifeForm> lifeForms = new ArrayList<>();
        for (String saga : scrapedMap.keySet()) {
            System.out.println("hey there");
            for (LifeForm lf : scrapedMap.get(saga)) {
                LifeForm newLifeForm =
                        new LifeForm(lf.getName(), lf.getPowerLevel(), saga);
                System.out.println(newLifeForm);
                lifeForms.add(newLifeForm);
            }
        }

        User user = new User("JC", 55, 55, 55);
        lifeForms.add(user);
        System.out.println(lifeForms);
        Collections.sort(lifeForms);

        int index = lifeForms.indexOf(user);
        if (index == 0) {
            user.setStrongerFoe(lifeForms.get(0));
            user.setWeakerFoe(new LifeForm("Tardigrade", 0, "Goeze Saga"));
        } else if (index == lifeForms.size()) {
            user.setStrongerFoe(new LifeForm("Polycephabrick", Double.POSITIVE_INFINITY, "Brick Boys"));
            user.setWeakerFoe(lifeForms.get(lifeForms.size() - 1));
        } else {
            user.setStrongerFoe(lifeForms.get(index + 1));
            user.setWeakerFoe(lifeForms.get(index - 1));
        }
        System.out.println(user.getWeakerFoe());
    }

}
