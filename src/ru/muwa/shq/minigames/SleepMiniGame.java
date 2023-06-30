package ru.muwa.shq.minigames;

import ru.muwa.shq.engine.g.hud.MiniGameHUD;

import java.awt.*;

public class SleepMiniGame extends MiniGame{
    public SleepMiniGame()
    {}
    public SleepMGButton sleep = new SleepMGButton(MiniGameHUD.x+10,MiniGameHUD.y+300,"Спать");
    public SleepMGButton save = new SleepMGButton(MiniGameHUD.x+220,MiniGameHUD.y+400,"Сохранить");

    public static class SleepMGButton extends Rectangle {
        public String text = "";
        public SleepMGButton(int x, int y, String text){
            super(x,y,50,50);
            this.text=text;
        }
    }
}
