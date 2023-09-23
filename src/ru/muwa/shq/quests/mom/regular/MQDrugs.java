package ru.muwa.shq.quests.mom.regular;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.items.consumables.Lube;
import ru.muwa.shq.items.drugs.Lyrica;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.quests.Quest;
import ru.muwa.shq.quests.conditions.TaskCondition;
import java.util.ArrayList;
public class MQDrugs extends Quest {
    public MQDrugs()
    {
        isTaken = true;
        timeAcquired = TimeMachine.getCurrentTime();
        expirationTime = TimeMachine.getCurrentTime() + TimeMachine.DAY_LENGTH / 2;

        owner="мама";
        name="купить лекарства";
        description="Нужно купить лекарста по списку и оставить их дома в холодильнике.";
        tasks = new ArrayList<>();

        //TODO: Можно сделать таски на покупку разных продкутов и давать игроку набор случайных тасков.
        tasks.add(Math.random() > 0.5? task1 : task2) ;
    }
    private static Task task1 =
            new Task("Таблетки (лирика)", new TaskCondition() {
                @Override
                public boolean checkCondition() {
                    boolean b=false;
                    try {
                        for (int i = 0; i < HubHataIgoryana.getInstance().fridge.getItems().size(); i++)
                            if(HubHataIgoryana.getInstance().fridge.getItems().get(i) instanceof Lyrica)
                                b = true;
                    }catch (Exception e){}//...............
                    return b;
                }
            });
    private static Task task2 =
            new Task("Мазь", new TaskCondition() {
                @Override
                public boolean checkCondition() {
                    boolean b=false;
                    try {
                        for (int i = 0; i < HubHataIgoryana.getInstance().fridge.getItems().size(); i++)
                            if(HubHataIgoryana.getInstance().fridge.getItems().get(i) instanceof Lube)
                                b = true;
                    }catch (Exception e){}//...............
                    return b;
                }
            });
}
