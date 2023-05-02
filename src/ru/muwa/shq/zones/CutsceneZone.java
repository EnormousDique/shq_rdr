package ru.muwa.shq.zones;

import ru.muwa.shq.engine.animations.cutscenes.Cutscene;
import ru.muwa.shq.quests.conditions.TaskCondition;

public class CutsceneZone extends GameZone
{
    private Cutscene cutscene;
    private boolean beenPlayed;

    private boolean isOnCondition;
    private TaskCondition condition;
    public boolean isBeenPlayed() {
        return beenPlayed;
    }
    public void setBeenPlayed(boolean beenPlayed) {
        this.beenPlayed = beenPlayed;
    }
    public Cutscene getCutscene() {
        return cutscene;
    }
    public CutsceneZone(int x, int y, int width, int height, Cutscene cutscene) {
        super(x, y, width, height);
        this.cutscene = cutscene;
    }

}
