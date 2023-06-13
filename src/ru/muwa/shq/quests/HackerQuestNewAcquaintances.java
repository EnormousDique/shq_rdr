package ru.muwa.shq.quests;

import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance5.L1B5P5F1;
import ru.muwa.shq.quests.actions.QuestAction;
import ru.muwa.shq.quests.conditions.TaskCondition;
import ru.muwa.shq.zones.CompleteTaskZone;

import java.io.IOException;
import java.util.ArrayList;

public class HackerQuestNewAcquaintances extends Quest{
    public HackerQuestNewAcquaintances() {
        tasks = new ArrayList<>();
        name = "НА хату к хакеру";
        try {
            tasks.add(new Task("Прийти на хату к Хакеру", new CompleteTaskZone(-200, -300, 150, 150, L1B5P5F1.getInstance())));
        } catch (IOException e) {
            System.out.println("немогу найти зону хакера");
        }

    }
}