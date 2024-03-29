package ru.muwa.shq.quests;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.MiniGameHUD;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.items.consumables.Water;
import ru.muwa.shq.levels.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.levels.demo.indoors.WhiteBlueTallBuildingFloor4;
import ru.muwa.shq.minigames.MQMinigame;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.actions.Q2T1_Action;
import ru.muwa.shq.quests.conditions.TaskCondition;
import ru.muwa.shq.quests.mom.regular.MQFood;
import ru.muwa.shq.zones.CompleteTaskZone;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestUtility {
    private QuestUtility(){};
    /**
     * Главный метод службы.
     */
    public static void work()
    {
        maintainPlayerQuests(Player.get().momQuests);
        maintainTimedQuests(Player.get().momQuests);
        maintainPlayerQuests(Player.get().hackerQuests);
        maintainPlayerQuests(Player.get().copQuests);
        maintainPlayerQuests(Player.get().butcherQuests);
        maintainPlayerQuests(Player.get().hachQuests);
    }

    /**
     * Метод обработки квестов. Проверяет дошел ли игрок до цели и регулирует статус соотв. задания.
     */
    public static void maintainPlayerQuests(ArrayList<Quest>quests){
        for(int i=0; i< quests.size(); i++)
        {
            for(int j = 0; j< quests.get(i).tasks.size(); j++)
            {
                //Блок обработки заданий без условий (задания типа  "дойди до маркера" )
                if(!quests.get(i).tasks.get(j).hasCondition &&
                        !quests.get(i).tasks.get(j).isCompleted &&
                        Engine.getCurrentLevel().equals(quests.get(i).tasks.get(j).completeTaskZone.level))
                {
                    if(!Engine.getCurrentLevel().getZones().contains(quests.get(i).tasks.get(j).completeTaskZone))
                    {
                        Engine.getCurrentLevel().getZones().add(quests.get(i).tasks.get(j).completeTaskZone);
                    }
                    if(Player.get().getSolidBox().intersects(quests.get(i).tasks.get(j).completeTaskZone))
                    {
                        quests.get(i).tasks.get(j).isCompleted = true;
                        //Если по завершению задачи должно происодить заскриптованное событие, вызываем его.
                        if(quests.get(i).tasks.get(j).isTriggeringAction && !quests.get(i).tasks.get(j).isExpired)
                        {
                            quests.get(i).tasks.get(j).action.performAction();
                            quests.get(i).tasks.get(j).isExpired = true;
                        }
                    }
                }
                if (quests.get(i).tasks.get(j).hasCondition) // Блок обработки заданий с условиями
                {
                    if(quests.get(i).tasks.get(j).condition.checkCondition())
                    {
                        // Если условие задания выполнено
                        quests.get(i).tasks.get(j).isCompleted = true;
                        if(quests.get(i).tasks.get(j).isTriggeringAction && !quests.get(i).tasks.get(j).isExpired)
                        {
                            quests.get(i).tasks.get(j).action.performAction();
                            quests.get(i).tasks.get(j).isExpired = true;
                        }

                    }
                }
            }
        }
    }

    private static void maintainTimedQuests(List<Quest> quests)
    {
        for(int i = 0; i < quests.size(); i++)
        {
            Quest quest = quests.get(i);
            if(TimeMachine.getCurrentTime() > quest.expirationTime && quest.expirationTime != 0)
            {
                MiniGameHUD.currentMiniGame = new MQMinigame(quest);
                if(!quest.tasks.get(quest.tasks.size()-1).isCompleted) Player.get().momHearts -= 1;
                quests.remove(quest);
                quests.add(new MQFood());

            }
        }
    }

    /**
     * Ниже идут квесты по очереди
     */


    /**
     * КВЕСТ 1 : МАМЕНЬКИН СыНОК ВОЗВРАЩАЕТСЯ ДОМОЙ /5
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
      //  Player.get().quests.add(quest1);

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

      //  Player.get().quests.add(quest2);

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



      //  Player.get().quests.add(quest3);
    }
    // КОНЕЦ КВЕСТА 3
    //***************************************************************************************************************

    /**
     * КВЕСТ 4  : TEST
     */
    //***************************************************************************************************************


    private static Quest quest4 = new Quest();

    public static void setQuest4(){

        quest4.addTask("тест", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                for(int i = 0; i < Inventory.getInstance().getItems().size();i++){
                    if(Inventory.getInstance().getItems().get(i) instanceof Water){
                        return true;
                    }
                }
                return false;
            }
        });
        CompleteTaskZone ctzQ4T2 = null;
        try {
             ctzQ4T2 = new CompleteTaskZone(3000,3000,100,100,DemoLevel0.getInstance());
        } catch (IOException e) {
            System.out.println("неудалось получить доступ к демоуровню");
        }
        quest4.addTask("иди нахуй",ctzQ4T2);

     //  Player.get().quests.add(quest4);
    }

    // КОНЕЦ КВЕСТА 4
    //***************************************************************************************************************

    /**
     * КВЕСТ 5  : КРАЖА ПАЦАНА
     */
    //***************************************************************************************************************

    public static void startQuest5(){


        CompleteTaskZone ctzQ5T1 = null;
        try {
            ctzQ5T1 = new CompleteTaskZone(100,100,100,100, WhiteBlueTallBuildingFloor4.getInstance());
        } catch (IOException e) {
            System.out.println("неудалось получить доступ к 4-му  этажу  синего дома");
        }
        quest4.addTask("Прокрасться в хату к пацанёнку.",ctzQ5T1);

        quest4.addTask("Выкрасть пацана", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                //TODO: Реализовать проверку на украденного пацана.
                return false;
            }
        });

       // Player.get().quests.add(quest4);
    }
}
