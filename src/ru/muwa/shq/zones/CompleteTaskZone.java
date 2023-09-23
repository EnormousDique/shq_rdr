package ru.muwa.shq.zones;

import ru.muwa.shq.levels.Level;

public class CompleteTaskZone extends GameZone{

    public Level  level;
    public CompleteTaskZone(int x, int y, int width, int height, Level level) {
        super(x, y, width, height);
        this.level = level;
    }

}
