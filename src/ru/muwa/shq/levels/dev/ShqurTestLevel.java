package ru.muwa.shq.levels.dev;

import ru.muwa.shq.engine.spawner.Kladmen;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.containers.*;

import java.io.IOException;

public class ShqurTestLevel extends Level {
    private static ShqurTestLevel instance;

    public static ShqurTestLevel getInstance()
    {
        if (instance==null) return new ShqurTestLevel(); else return instance;
    }
    private ShqurTestLevel()
    {
        instance = this;
        containers.add(new PostBox(100,100));
        try {
            containers.add(new Heater(400,100));
            containers.add(new WindowSill(1000,150));
            containers.add(new GarbageChute(550,100));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Kladmen.register(this);

    }
}
