package ru.muwa.shq.quests.mom.regular;

import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.consumables.*;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.quests.Quest;
import ru.muwa.shq.quests.conditions.TaskCondition;

import java.util.ArrayList;

public class MQFood extends Quest {
    public MQFood()
    {
        isTaken = true;
        timeAcquired = TimeMachine.getCurrentTime();
        expirationTime = TimeMachine.getCurrentTime() + TimeMachine.DAY_LENGTH / 3;

        owner="мама";
        name="купить продукты";
        description="Нужно купить продукты по списку и оставить их дома в холодильнике.";
        tasks = new ArrayList<>();

        //TODO: Можно сделать таски на покупку разных продкутов и давать игроку набор случайных тасков.
        tasks.add(Math.random()>0.5?task1:task2);
        tasks.add(Math.random()>0.5?task3:task4);

    }
    private Task task1 =
        new Task("Мокровь", new TaskCondition() {
        @Override
        public boolean checkCondition() {
            boolean b=false;
            try {
                for (int i = 0; i < HubHataIgoryana.getInstance().fridge.getItems().size(); i++)
                    if(HubHataIgoryana.getInstance().fridge.getItems().get(i) instanceof Carrot)
                        b = true;
            }catch (Exception e){}//...............
            return b;
        }
    });
    private Task task2 =
            new Task("Капуста", new TaskCondition() {
                @Override
                public boolean checkCondition() {
                    boolean b=false;
                    try {
                        for (int i = 0; i < HubHataIgoryana.getInstance().fridge.getItems().size(); i++)
                            if(HubHataIgoryana.getInstance().fridge.getItems().get(i) instanceof Cabbage)
                                b = true;
                    }catch (Exception e){}//...............
                    return b;
                }
            });
    private Task task3 =
            new Task("Свекла", new TaskCondition() {
                @Override
                public boolean checkCondition() {
                    boolean b=false;
                    try {
                        for (int i = 0; i < HubHataIgoryana.getInstance().fridge.getItems().size(); i++)
                            if(HubHataIgoryana.getInstance().fridge.getItems().get(i) instanceof Beetroot)
                                b = true;
                    }catch (Exception e){}//...............
                    return b;
                }
            });
    private Task task4 =
            new Task("Картошка", new TaskCondition() {
                @Override
                public boolean checkCondition() {
                    boolean b=false;
                    try {
                        for (int i = 0; i < HubHataIgoryana.getInstance().fridge.getItems().size(); i++)
                            if(HubHataIgoryana.getInstance().fridge.getItems().get(i) instanceof Potato)
                                b = true;
                    }catch (Exception e){}//...............
                    return b;
                }
            });
}
