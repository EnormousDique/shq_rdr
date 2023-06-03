package ru.muwa.shq.quests;

import ru.muwa.shq.levels.demo.indoors.WhiteBlueTallBuildingFloor4;
import ru.muwa.shq.quests.conditions.TaskCondition;
import ru.muwa.shq.zones.CompleteTaskZone;

import java.io.IOException;
import java.util.ArrayList;

public class ButcherQuestPacan extends Quest{

    public ButcherQuestPacan()
    {
        isTaken = true;
        owner="butcher";
        name="krazha pacana";
        tasks = new ArrayList<>();
        CompleteTaskZone ctzQ5T1 = null;
        try {
            ctzQ5T1 = new CompleteTaskZone(100,100,100,100, WhiteBlueTallBuildingFloor4.getInstance());
        } catch (IOException e) {
            System.out.println("неудалось получить доступ к 4-му  этажу  синего дома");
        }
        this.addTask("Прокрасться в хату к пацанёнку.",ctzQ5T1);
        this.addTask("Выкрасть пацана", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                //TODO: Реализовать проверку на украденного пацана.
                return false;
            }
        });
    }
}
