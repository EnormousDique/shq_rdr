package ru.muwa.shq.quests;

import ru.muwa.shq.items.consumables.Gurken;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.quests.conditions.TaskCondition;

import java.util.ArrayList;

public class MomQuestFood extends Quest{
    public MomQuestFood()
    {
        isTaken = true;
        owner="mom";
        name="купить продукты";
        tasks = new ArrayList<>();
        tasks.add(new Task("Купить продукты", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                boolean  b =false;
                if(Inventory.getInstance().inventoryContains(new Gurken()))
                {
                    b = true;
                }
                return b;
            }
        }));
        tasks.add(new Task("Принести продукты домой", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                boolean b=false;
                try {
                    for (int i = 0; i < HubHataIgoryana.getInstance().getContainers().get(0).getItems().size(); i++)
                        if(HubHataIgoryana.getInstance().getContainers().get(0).getItems().get(i) instanceof Gurken)
                            b = true;
                }catch (Exception e){}//...............
                return b;
            }
        }));
    }
}
