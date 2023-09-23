package ru.muwa.shq.minigames;

import ru.muwa.shq.quests.Quest;
import ru.muwa.shq.zones.MiniGameZone;

public class MQMinigame extends MiniGame {
    public Quest quest;
    public MQMinigame(Quest quest)
    {
        this.quest=quest;
    }
}
