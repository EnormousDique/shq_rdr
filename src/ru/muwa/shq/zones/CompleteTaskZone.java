package ru.muwa.shq.zones;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.quests.Quest;
import ru.muwa.shq.quests.actions.Q2T1_Action;

public class CompleteTaskZone extends GameZone{

    public Level  level;
    public CompleteTaskZone(int x, int y, int width, int height, Level level) {
        super(x, y, width, height);
        this.level = level;
    }

}
