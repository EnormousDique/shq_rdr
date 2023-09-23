package ru.muwa.shq.quests;

import ru.muwa.shq.levels.demoLevel0.DemoLevel0;
import ru.muwa.shq.zones.CompleteTaskZone;

import java.io.IOException;
import java.util.ArrayList;

public class HomeSweetHome extends Quest{
    private static CompleteTaskZone testzone;
    public HomeSweetHome() throws IOException {

        name = "ДОРОГА ДО ДОМУ";
        tasks = new ArrayList<>();
        try {
             testzone = new CompleteTaskZone(299, 225, 70, 70, DemoLevel0.getInstance());
        }
        catch (Exception e) {
            System.out.println("нихуя не инициалиазхицаварваловся");
        }

      //  Player.get().quests.add(testzone);
        }

    }

