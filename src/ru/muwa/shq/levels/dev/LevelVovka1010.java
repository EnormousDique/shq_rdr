package ru.muwa.shq.levels.dev;

import ru.muwa.shq.entities.gameObjects.containers.GarbageChute;
import ru.muwa.shq.entities.gameObjects.obstacles.crates.Crate1;
import ru.muwa.shq.levels.Level;

import java.io.IOException;

public class LevelVovka1010 extends Level {
    public LevelVovka1010 (){
        try {
            objects.add(new GarbageChute (320,428));

        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }
}