package ru.muwa.shq.quests;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.consumables.Carrot;
import ru.muwa.shq.items.consumables.Gurken;
import ru.muwa.shq.items.consumables.Lube;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.quests.conditions.TaskCondition;

import java.util.ArrayList;

public class HackerQuestReboot extends Quest{
    public HackerQuestReboot()
    {
        isTaken = true;
        owner="хакер";
        name="Переустановка ОС";
        description = "Хакер просит достать ему некоторые вещи. Далее по списку :. 10 пакетов \"скорости\", морковь, и смазку из аптеки \"Анальный князь\". Эти вещи нужно принести хакеру.";
        tasks = new ArrayList<>();
        tasks.add(new Task("Достать морковь.", new TaskCondition() {
            @Override
            public boolean checkCondition() {

                for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
                {
                    //TODO: реализовать проверку на наличие в инвентаре
                    Item item = Inventory.getInstance().getItems().get(i);
                    if(item instanceof Carrot) return true;
                }
                return false;
            }
        }));
        tasks.add(new Task("Достать 10 пакетов порошка.", new TaskCondition() {
            @Override
            public boolean checkCondition() {

                for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
                {
                    //TODO: реализовать проверку на наличие в инвентаре
                    Item item = Inventory.getInstance().getItems().get(i);
                    if(item instanceof Flour && item.amount >=10) return true;
                }
                return false;
            }
        }));
        tasks.add(new Task("Достать мазь \"Анальный князь\". ", new TaskCondition() {
            @Override
            public boolean checkCondition() {

                for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
                {
                    //TODO: реализовать проверку на наличие в инвентаре
                    Item item = Inventory.getInstance().getItems().get(i);
                    if(item instanceof Lube) return true;
                }
                return false;
            }
        }));
        tasks.add(new Task("Отнести вещи хакеру.", new TaskCondition() {
            @Override
            public boolean checkCondition() {

                return false;
            }
        }));
    }
}
