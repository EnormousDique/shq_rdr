package ru.muwa.shq.zones;

import ru.muwa.shq.engine.animations.cutscenes.Cutscene;

public class CutsceneZone extends GameZone
{
    private Cutscene cutscene;
    private boolean beenPlayed;

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
