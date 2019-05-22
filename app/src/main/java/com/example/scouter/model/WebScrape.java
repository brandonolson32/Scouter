package com.example.scouter.model;

import com.example.scouter.entity.Character.LifeForm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shane - shanelee.co.uk
 */


public class WebScrape {

    String saga;
    String character;
    String powerLevel;
    String source;

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
                if (!data.get(0).hasText()) {
                    try {
                        LifeForm newGuy = new LifeForm(data.get(1).text(), Integer.parseInt(data.get(2).text().replaceAll("[^.0123456789]","")));
                        motherOfData.get(saga).add(newGuy);
                    }
                    catch (NumberFormatException e) {}
                } else {
                    saga = data.get(0).text();
                    motherOfData.put(saga, new ArrayList<LifeForm>());
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

    }

}
