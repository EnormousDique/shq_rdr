package ru.muwa.shq.levels.dev;

import ru.muwa.shq.entities.gameObjects.obstacles.crates.Crate1;
import ru.muwa.shq.levels.Level;

import java.io.IOException;

public class LevelVovka1010 extends Level {
    public LevelVovka1010 (){
        try {
            objects.add(new Crate1 (12,28));
            objects.add(new Crate1(12,20));
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }
}
