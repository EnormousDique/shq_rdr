package ru.muwa.shq.quests;

import ru.muwa.shq.levels.demoLevel0.buildings.building5.entrance5.HackersPlace;
import ru.muwa.shq.quests.conditions.TaskCondition;
import ru.muwa.shq.zones.CompleteTaskZone;

import java.util.ArrayList;

public class HackerQuestNewAcquaintances extends Quest{

    public HackerQuestNewAcquaintances() {
        isTaken=true;
        description="Хачонок с рынка расскахал мне о каком-то <<мутном шмакере>> и дал адрес. Серая П-образная многоэтажка. 4-й подъезд, 4-й этаж. код 228к1488 ";
        name = "Знакомство с хакером";
        tasks = new ArrayList<>();
            tasks.add(new Task("Прийти на хату к Хакеру", new CompleteTaskZone(-200, -300, 100, 100, HackersPlace.getInstance())));
        tasks.add(new Task("Поговорить с хакером (шмакером?)", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                return false;
            }
        }));
    }
}