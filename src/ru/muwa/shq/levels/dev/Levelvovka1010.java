package ru.muwa.shq.levels.dev;

import ru.muwa.shq.entities.gameObjects.containers.GarbageChute;
import ru.muwa.shq.entities.gameObjects.obstacles.crates.Crate2;
import ru.muwa.shq.levels.Level;

import java.io.IOException;

public class Levelvovka1010 extends Level
{
    public Levelvovka1010()
    {
        try
        {
            containers.add(new GarbageChute (120,128));
        }

        catch (IOException e)
        {
            System.out.println("Произошло исклчение при инциализации объектов уровня");;
        }
    }



}

