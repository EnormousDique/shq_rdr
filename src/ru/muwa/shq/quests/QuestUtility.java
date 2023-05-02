package ru.muwa.shq.quests;

import jdk.jshell.spi.SPIResolutionException;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.actions.Q2T1_Action;
import ru.muwa.shq.quests.conditions.C_HasPlayerFoundKlad;
import ru.muwa.shq.quests.conditions.TaskCondition;
import ru.muwa.shq.zones.CompleteTaskZone;
import ru.muwa.shq.zones.InteractiveEnterZone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestUtility {
    private QuestUtility(){};



    /**
     * Главный метод службы.
     */
    public static void work()
    {
        maintainPlayerQuests();

        if(quest1.tasks.get(quest1.tasks.size()-1).isCompleted && !Player.get().quests.contains(quest2)) startQuest2();


    }

    /**
     * Метод обработки квестов. Проверяет дошел ли игрок до цели и регулирует статус соотв. задания.
     */
    public static void maintainPlayerQuests(){
        for(int i=0; i< Player.get().quests.size(); i++)
        {
            for(int j = 0; j< Player.get().quests.get(i).tasks.size(); j++)
            {
                //Блок обработки заданий без условий (задания типа  "дойди до маркера" )
                if(!Player.get().quests.get(i).tasks.get(j).hasCondition &&
                        !Player.get().quests.get(i).tasks.get(j).isCompleted &&
                        Engine.getCurrentLevel().equals(Player.get().quests.get(i).tasks.get(j).completeTaskZone.level))
                {
                    if(!Engine.getCurrentLevel().getZones().contains(Player.get().quests.get(i).tasks.get(j).completeTaskZone))
                    {
                        Engine.getCurrentLevel().getZones().add(Player.get().quests.get(i).tasks.get(j).completeTaskZone);
                    }
                    if(Player.get().getSolidBox().intersects(Player.get().quests.get(i).tasks.get(j).completeTaskZone))
                    {
                        Player.get().quests.get(i).tasks.get(j).isCompleted = true;
                        //Если по завершению задачи должно происодить заскриптованное событие, вызываем его.
                        if(Player.get().quests.get(i).tasks.get(j).isTriggeringAction)
                        {Player.get().quests.get(i).tasks.get(j).action.performAction();}
                    }
                }
                if (Player.get().quests.get(i).tasks.get(j).hasCondition) // Блок обработки заданий с условиями
                {
                    if(Player.get().quests.get(i).tasks.get(j).condition.checkCondition())
                    {
                        // Если условие задания выполнено
                        Player.get().quests.get(i).tasks.get(j).isCompleted = true;

                    }
                }
            }
        }
    }

    /**
     * Ниже идут квесты по очереди
     */


    /**
     * КВЕСТ 1 : МАМЕНЬКИН СыНОК ВОЗВРАЩАЕТСЯ ДОМОЙ
     */

    //***************************************************************************************************************
    // КВЕСТ1
    private static Quest quest1 = new Quest();
    private static CompleteTaskZone ctzQ1T1, ctzQ1T2;
    public static void startQuest1(){
        try{
            ctzQ1T1   = new CompleteTaskZone(190,290,100,100, FatBuildingFloor1.getInstance());
        } catch (IOException e) {
            System.out.println("ИНИНЦИАЛИЗАЦИЯ КВЕСТА 1 ПРОИЗОШЛА ОШИБКА");
        }
        try{
            ctzQ1T2 = new CompleteTaskZone(100,100,100,100, HubHataIgoryana.getInstance());
        } catch (IOException e) {
            System.out.println("ИНИНЦИАЛИЗАЦИЯ КВЕСТА 1 ПРОИЗОШЛА ОШИБКА");
        }
        quest1.addTask("Дойти до дома", ctzQ1T1);
        quest1.addTask("Поговорить с мамой", ctzQ1T2);
        Player.get().quests.add(quest1);

    }
    // КОНЕЦ КВЕСТА 1
    //***************************************************************************************************************

    /**
     * КВЕСТ 2 : ЗА ПОКУПКАМИ
     */
    //***************************************************************************************************************
    // КВЕСТ2
    private static Quest quest2 = new Quest();
    private static CompleteTaskZone ctzQ2T1;
    public static void startQuest2(){
        try{
            ctzQ2T1   = new CompleteTaskZone(1500,9000,200,200, DemoLevel0.getInstance());
        } catch (IOException e) {
            System.out.println("ИНИНЦИАЛИЗАЦИЯ КВЕСТА 2 ПРОИЗОШЛА ОШИБКА");
        }

        quest2.addTask("Дойти до рынка", ctzQ2T1, new Q2T1_Action());
        quest2.addTask("Закупиться продуктами", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                //TODO: добавить код проверки купленных продуктов
                return false;
            }
        });

        Player.get().quests.add(quest2);

    }
    // КОНЕЦ КВЕСТА 2
    //***************************************************************************************************************


    /**
     * КВЕСТ 3 : НА АДРЕС
     */
    //***************************************************************************************************************
    // КВЕСТ3
    private static Quest quest3 = new Quest();

    public static void startQuest3(){

        quest3.addTask("Найти стаффчик", new C_HasPlayerFoundKlad());
        quest3.addTask("Поговорить с хачиком", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                //todo добавить код проверки вернулся ли игрок со стаффом к хачику
                return false;
            }
        });

        Player.get().quests.add(quest3);
    }
    // КОНЕЦ КВЕСТА 3
    //***************************************************************************************************************

}
