package ru.muwa.shq.quests;

import jdk.jshell.spi.SPIResolutionException;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;
import ru.muwa.shq.levels.demo.indoors.Hub;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.CompleteTaskZone;
import ru.muwa.shq.zones.InteractiveEnterZone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestUtility {
    private QuestUtility(){};

    //КВЕСТ1

    private static Quest quest1 = new Quest();
    public static Quest getQuest1(){return quest1;}

    private static CompleteTaskZone ctzQ1T1, ctzQ1T2;


    public static void startQuest1(){


        try{
            ctzQ1T1   = new CompleteTaskZone(190,290,100,100, FatBuildingFloor1.getInstance());
        } catch (IOException e) {
            System.out.println("ИНИНЦИАЛИЗАЦИЯ КВЕСТА 1 ПРОИЗОШЛА ОШИБКА");
        }

        try{
            ctzQ1T2 = new CompleteTaskZone(100,100,100,100, Hub.getInstance());
        } catch (IOException e) {
            System.out.println("ИНИНЦИАЛИЗАЦИЯ КВЕСТА 1 ПРОИЗОШЛА ОШИБКА");
        }

        quest1.addTask("Дойти до дома", ctzQ1T1);
        quest1.addTask("Поговорить с мамой", ctzQ1T2);

        Player.get().quests.add(quest1);
    }

    public static void maintainPlayerQuests(){
        for(int i=0; i< Player.get().quests.size(); i++)
        {
            for(int j = 0; j< Player.get().quests.get(i).tasks.size(); j++)
            {
                if(!Player.get().quests.get(i).tasks.get(j).isCompleted &&
                        Engine.getCurrentLevel().equals(Player.get().quests.get(i).tasks.get(j).completeTaskZone.level))
                {
                    if(!Engine.getCurrentLevel().getZones().contains(Player.get().quests.get(i).tasks.get(j).completeTaskZone))

                        Engine.getCurrentLevel().getZones().add(Player.get().quests.get(i).tasks.get(j).completeTaskZone);

                    if(Player.get().getSolidBox().intersects(Player.get().quests.get(i).tasks.get(j).completeTaskZone))
                    {
                        Player.get().quests.get(i).tasks.get(j).isCompleted = true;
                    }
                }


            }
        }
    }


    // КОНЕЦ КВЕСТА 1

}
