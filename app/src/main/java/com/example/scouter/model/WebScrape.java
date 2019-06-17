package com.example.scouter.model;

import com.example.scouter.entity.LifeForm;
import com.opencsv.CSVWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brandon and JC
 */


public class WebScrape {

    public static void main(String[] args) {
        webScrape("/Users/brandonolson/cs2340/Scouter/app/" +
                "src/main/assets/DBZ_Database.csv");
    }

    public static void webScrape(String filePath) {

        File file = new File(filePath);


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
                        double powerLevelFinal = 0;
                        if (data.get(2).text().contains("-")) {
                            String powerLevelString = data.get(2).text()
                                    .replaceAll("[^-.0123456789]", "");
                            String[] powerLevelStringArr = powerLevelString.split("-");
                            double powerLevel1 = Double.parseDouble(powerLevelStringArr[0]);
                            double powerLevel2 = Double.parseDouble(powerLevelStringArr[1]);
                            powerLevelFinal = (powerLevel1 + powerLevel2)/2;
                        } else {
                            if (data.get(2).text().equals("Unmeasurable")) {
                                    powerLevelFinal = Double.MAX_VALUE;
                            } else {
                                powerLevelFinal = Double.parseDouble(data.get(2).text()
                                        .replaceAll("[^-.0123456789]", ""));
                            }
                        }
                        LifeForm newGuy = new LifeForm(data.get(1).text(), powerLevelFinal);
                        motherOfData.get(saga).add(newGuy);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
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
                                    .replaceAll("[^0123456789]", "")));
                    motherOfData.remove(saga);
                    saga = saga + " " + data.get(0).text();
                    motherOfData.put(saga, new ArrayList<LifeForm>());
                    motherOfData.get(saga).add(newGuy);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List <LifeForm> lifeForms = new ArrayList<>();
        for (String saga : motherOfData.keySet()) {
            for (LifeForm lf : motherOfData.get(saga)) {
                LifeForm newLifeForm =
                        new LifeForm(lf.getName(), lf.getPowerLevel(), saga);
                lifeForms.add(newLifeForm);
            }
        }
        Collections.sort(lifeForms);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "Saga", "Name", "Power Level" };
            writer.writeNext(header);

            // add data to csv
            for (LifeForm lifeForm : lifeForms) {
                String saga = lifeForm.getSaga();
                String name = lifeForm.getName();
                String powerLevel = String.valueOf(lifeForm.getPowerLevel());
                String[] nextLine = { saga, name, powerLevel };
                writer.writeNext(nextLine);
            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
