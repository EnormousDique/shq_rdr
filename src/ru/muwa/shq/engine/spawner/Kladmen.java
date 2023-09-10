package ru.muwa.shq.engine.spawner;

import java.util.ArrayList;

import ru.muwa.shq.items.zakladki.KladRed;
import ru.muwa.shq.items.zakladki.KladYellow;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.minigames.shquring.PostBoxShq;
import ru.muwa.shq.objects.containers.Container;

public class Kladmen {
    public static void mudak()
    {
        // Проходимся по всем уровням, зарегистрированным в службе
        for(int i = 0; i < list.size(); i++)
        {
            for(int j = 0; j < list.get(i).getContainers().size();j++)
            {
                Container c = list.get(i).getContainers().get(j);

                if(c.shqurable && !stopList.contains(c)) {
                    if(Math.random() > 0.99) {
                        c.addItem(Math.random() > 0.5 ? new KladRed() : new KladYellow());
                        c.miniGame.init=false;
                        stopList.add(c);
                    }

                }
            }
        }

    }
    private static ArrayList<Level> list = new ArrayList<Level>();
    private static ArrayList<Container> stopList = new ArrayList<>();

    public static void register(Level level)
    {
        list.add(level);
    }
}
