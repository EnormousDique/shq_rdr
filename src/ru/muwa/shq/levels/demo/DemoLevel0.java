package ru.muwa.shq.levels.demo;

import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.buildings.ninefloor.NineFloorRoof;
import ru.muwa.shq.objects.buildings.ninefloor.NineFloorWall;
import ru.muwa.shq.objects.street.Pavement;

public class DemoLevel0 extends Level
{

    public DemoLevel0()
    {
        super();
        objects.add(new Pavement(0, GameScreen.SCREEN_HEIGHT-Pavement.img.getHeight()));
        startPosX = 10;
        startPosY = GameScreen.SCREEN_HEIGHT - 150;
    }

}
