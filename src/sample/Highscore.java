package sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Highscore implements Comparable<Highscore> {
    private String name;
    private int points;

    public Highscore(String name, int points){
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void win(){
        points += 2;
    }

    public void draw(){
        points++;
    }

    public static void addPointWin(String whoseNext, List<Highscore> highscore, Map<String, String> map){
        boolean alreadyinhs = false;
        for (int i = 0; i < highscore.size(); i++){
            if (highscore.get(i).getName().equals(map.get(whoseNext))) {
                highscore.get(i).win();
                alreadyinhs = true;
            }
        }
        if (!alreadyinhs) {
            highscore.add(new Highscore(map.get(whoseNext),2));
        }
        if (whoseNext.equals("X")) {
            //suche O
            alreadyinhs = false;
            for (int i = 0; i < highscore.size(); i++) {
                if (highscore.get(i).getName().equals(map.get("O"))) {
                    alreadyinhs = true;
                }
            }

            if (!alreadyinhs){
                highscore.add(new Highscore(map.get("O"),0));
            }
        } else {
            for (int i = 0; i < highscore.size(); i++) {
                if (highscore.get(i).getName().equals(map.get("X"))) {
                    alreadyinhs = true;
                }
            }
            if (!alreadyinhs){
                highscore.add(new Highscore(map.get("X"),0));
            }
        }
    }

    public static void addPointDraw(List<Highscore> highscore, Map<String, String> map){
        boolean alreadyinhsx = false;
        boolean alreadyinhso = false;
        for (int i = 0; i < highscore.size();i++){
            if (highscore.get(i).getName().equals(map.get("X"))) {
                alreadyinhsx = true;
                highscore.get(i).draw();
            }

            if (highscore.get(i).getName().equals(map.get("O"))) {
                alreadyinhso = true;
                highscore.get(i).draw();
            }
        }

        if (!alreadyinhsx) {
            highscore.add(new Highscore(map.get("X"),1));
        }

        if (!alreadyinhso) {
            highscore.add(new Highscore(map.get("O"),1));
        }
    }

    @Override
    public int compareTo(Highscore o) {
        return this.getPoints() - o.getPoints();
    }

    public static void importHighscore(List<Highscore> highscore) {
        try {
            RandomAccessFile raf = new RandomAccessFile("hs.csv","r");
            String row = "";
            while ((row = raf.readLine()) != null) {
                String[] temp = row.split(";");
                highscore.add(new Highscore(temp[0],Integer.valueOf(temp[1])));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportHighscore(List<Highscore> highscore) {
        Collections.sort(highscore);
        try {
            RandomAccessFile raf = new RandomAccessFile("hs.csv","rw");
            for (int i = highscore.size()-1; i >= 0; i--) {
                if (i == 0) {
                    raf.writeBytes(highscore.get(i).getName() + ";" + highscore.get(i).getPoints());
                } else {
                    raf.writeBytes(highscore.get(i).getName() + ";" + highscore.get(i).getPoints() + "\n");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException io) {
            System.out.println("File not found");
        }

        while (highscore.size() != 0) {
            highscore.remove(0);
        }
    }
}
