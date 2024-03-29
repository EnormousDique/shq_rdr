package ru.muwa.shq.quests;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.quest.BlackCreditCard;
import ru.muwa.shq.items.quest.GreenCreditCard;
import ru.muwa.shq.items.quest.Package;
import ru.muwa.shq.levels.demoLevel0.DemoLevel0;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.quests.conditions.TaskCondition;
import ru.muwa.shq.zones.CompleteTaskZone;

import java.io.IOException;
import java.util.ArrayList;

public class HackerQuestATM extends Quest{

    public static boolean doesPlayerKnowWhereIsTheDelivery = false;
    public HackerQuestATM() throws IOException {
        isTaken=true;
        owner="hacker";
        name="Обнальщики";
        description="Хакеру должна была поступить посылка. В посылке находится аппарат, который требуется установить на банкомат. Нужно заполучить эту посылку и провести установку. Хакер просит заранее собрать 10 банковских карт.";
        tasks = new ArrayList<>();
        addTask("Собрать 10 карт", new TaskCondition() {
            @Override
            public boolean checkCondition() {

                //TODO: добавить проверку наличия 10  карт в инвентаре
                int black = 0;
                int green = 0;
                for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) {
                    Item item = Inventory.getInstance().getItems().get(i);

                    if(item instanceof BlackCreditCard){
                        black ++;

                    }else if (item instanceof GreenCreditCard){
                        green ++;
                    }
                        if(green+black >= 10){
                            tasks.get(0).isCompleted = true;
                        }else tasks.get(0).isCompleted = false;


                }return false;
            }
        });
        addTask("Сходить на почту, спросить про посылку",new CompleteTaskZone(0,0,0,0, DemoLevel0.getInstance()));
        addTask("Узнать где может быть посылка", new TaskCondition() {
            @Override
            public boolean checkCondition() {


                return doesPlayerKnowWhereIsTheDelivery;
            }
        });
        addTask("пролезть ночью в участок",new CompleteTaskZone(0,0,0,0,DemoLevel0.getInstance()));
        addTask("Забрать посылку", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) {
                    Item item = Inventory.getInstance().getItems().get(i);
                    if(item instanceof Package){
                        tasks.get(4).isCompleted = true;
                    }
                }

                return false;
                //TODO: добавить логику

            }
        });
        addTask("Вернуться к хакеру",new CompleteTaskZone(0,0,0,0,DemoLevel0.getInstance()));
        addTask("Установить ночью модуль на банкомат", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                return false;
                //TODO: добавить логику проверки установки
            }
        });
        addTask("обналичить карты", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                return false;
                //TODO: добавить логику проверки
            }
        });
        addTask("Вернуться к хакеру",new CompleteTaskZone(0,0,0,0,DemoLevel0.getInstance()));
    }
}
