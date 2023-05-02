package ru.muwa.shq.zones;

import ru.muwa.shq.quests.actions.QuestAction;

public class ActionZone extends GameZone{
    private QuestAction action;
    public boolean expired =false;

    public QuestAction getAction(){return action;}
    public ActionZone(int x, int y, int width, int height, QuestAction action) {
        super(x, y, width, height);
        this.action = action;
    }
}
