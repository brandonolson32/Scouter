package com.example.scouter.entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
        final String url =
                "https://dragonball.fandom.com/wiki/List_of_Power_Levels";

        try {
            final Document document = Jsoup.connect(url).get();

//            System.out.println(document.outerHtml());

            for (Element row : document.select(
                    "table.wikitable tr")) {

                int counter = 0;

                String saga = "doo doo";
                String character = "POOP";
                String powerLevel;
                String source;

                for (Element data : row.select("td")) {
                    if (counter == 0) {
                        saga = row.select("td").text();
                        counter++;
                    }
                    if (counter == 1) {
                        character = row.select("td").text();
                        counter++;
                    }
                    if (counter == 2) {
                        powerLevel = row.select("td").text();
                        counter++;
                    }
                    if (counter == 3) {
                        source = row.select("td").text();
                        counter++;
                    }
                }

                System.out.println(saga);

//                if (row.select("td").text().equals("")) {
//                    String ;
//                }
//                else {
//                    final String cell =
//                            row.select("td").text();
//
//                    System.out.println(cell);
//                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
