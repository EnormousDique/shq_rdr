package ru.muwa.shq.levels.dev;

import ru.muwa.shq.entities.gameObjects.obstacles.crates.Crate2;
import ru.muwa.shq.levels.Level;

import java.io.IOException;

public class DevLevel1 extends Level
{
    public DevLevel1()
    {
        try
        {
            objects.add(new Crate2(228,228));
        }

        catch (IOException e)
        {
            System.out.println("Произошло исклчение при инциализации объектов уровня");;
        }
    }



}

