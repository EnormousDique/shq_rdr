package ru.muwa.shq.quests;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.consumables.Gurken;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.quests.conditions.TaskCondition;

import java.util.ArrayList;

public class HackerQuestReboot extends Quest{
    public HackerQuestReboot()
    {
        isTaken = true;
        owner="хаке";
        name="Переустановка ОС";
        description = "Хакер просит достать ему некоторые вещи. Далее по списку : 10 пакетов \"скорости\", огурец, пачку презервативов и смазку из аптеки \"Анальный князь\". Эти вещи нужно оставить в ящике у двери его квартиры, и уведомить постучав в дверь.";
        tasks = new ArrayList<>();
        tasks.add(new Task("Достать вещи.", new TaskCondition() {
            @Override
            public boolean checkCondition() {

                for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
                {
                    //TODO: реализовать проверку на наличие в инвентаре
                    Item item = Inventory.getInstance().getItems().get(i);
                    if(item instanceof Gurken)
                    {

                    }
                }
                return false;
            }
        }));
        tasks.add(new Task("Отнести вещи в ящик.", new TaskCondition() {
            @Override
            public boolean checkCondition() {

                for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
                {
                    //TODO: реализовать проверку на наличие в контейнере
                    Item item = Inventory.getInstance().getItems().get(i);
                    if(item instanceof Gurken)
                    {

                    }
                }
                return false;
            }
        }));
        tasks.add(new Task("Постучать в дверь", new TaskCondition() {
            @Override
            public boolean checkCondition() {


                    //TODO: этот пункт будет проставлен true вручную через взаимедойствие игрока с зоной.

                return false;
            }
        }));


    }
}
