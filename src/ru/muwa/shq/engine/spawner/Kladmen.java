package ru.muwa.shq.engine.spawner;

import java.util.ArrayList;

import ru.muwa.shq.items.zakladki.KladBlue;
import ru.muwa.shq.items.zakladki.KladRed;
import ru.muwa.shq.items.zakladki.KladYellow;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.minigames.PostBoxShq;

public class Kladmen {
    public static void mudak()
    {
        // Проходимся по всем уровням, зарегистрированным в службе
        for(int i = 0; i < list.size(); i++)
        {
            for(int j = 0; j < list.get(i).getContainers().size();j++)
            {
                if(list.get(i).getContainers().get(j).shqurable) {
                    if(Math.random() > 0.999/*99*/) {
                        list.get(i).getContainers().get(j).addItem(Math.random() > 0.5 ? new KladRed() : new KladYellow());
                        ((PostBoxShq)list.get(i).getContainers().get(j).miniGame).init=false;
                    }

                }
            }
        }

    }
    private static ArrayList<Level> list = new ArrayList<Level>();

    public static void register(Level level)
    {
        list.add(level);
    }
}
