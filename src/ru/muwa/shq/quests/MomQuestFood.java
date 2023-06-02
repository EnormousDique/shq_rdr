package ru.muwa.shq.quests;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.consumables.Gurken;
import ru.muwa.shq.items.zakladki.KladBlue;
import ru.muwa.shq.items.zakladki.KladRed;
import ru.muwa.shq.items.zakladki.KladYellow;
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

                for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
                {
                    Item item = Inventory.getInstance().getItems().get(i);
                    if(item instanceof Gurken)
                    {
                        return true;
                    }
                }
                return false;
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
