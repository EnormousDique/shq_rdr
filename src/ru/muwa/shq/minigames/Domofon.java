package ru.muwa.shq.minigames;

import ru.muwa.shq.levels.Level;

import java.awt.*;
import java.util.ArrayList;

public class Domofon extends MiniGame{

    public ArrayList<DomofonButton> buttons;
    public  String key;

    public String input = "";
    public Level level;
    public int whereX, whereY;

    public Domofon(String key, Level l, int whereX , int whereY)
    {
        this.key=key;
        this.level=l;
        this.whereX=whereX;
        this.whereY=whereY;

        buttons=new ArrayList<>();

        buttons.add(new DomofonButton(5,10,"1"));
        buttons.add(new DomofonButton(5,60,"4"));
        buttons.add(new DomofonButton(5,110,"7"));
        buttons.add(new DomofonButton(55,10,"2"));
        buttons.add(new DomofonButton(55,60,"5"));
        buttons.add(new DomofonButton(55,110,"8"));
        buttons.add(new DomofonButton(105,10,"3"));
        buttons.add(new DomofonButton(105,60,"6"));
        buttons.add(new DomofonButton(105,110,"9"));
        buttons.add(new DomofonButton(5,160,"Х"));
        buttons.add(new DomofonButton(55,160,"В"));
        buttons.add(new DomofonButton(105,160,"k"));
    }


    public static class DomofonButton extends Rectangle {
        public String text = "";
        public DomofonButton(int x, int y, String text){
            super(x,y,50,50);
            this.text=text;
        }
    }
}
