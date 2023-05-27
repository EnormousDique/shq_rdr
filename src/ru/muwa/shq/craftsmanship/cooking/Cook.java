package ru.muwa.shq.craftsmanship.cooking;

import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.items.consumables.*;
import ru.muwa.shq.objects.containers.Container;

import java.util.ArrayList;

public class Cook {
    public static boolean cook(Container c)
    {
        if(cookSoup(c)) return true;
        if(cookPasta(c)) return true;
        return false;
    }

    private static boolean cookSoup(Container c)
    {

        boolean isPotatoIn = c.getItems().stream().anyMatch(i -> i instanceof Potato);
        boolean isCabbageIn = c.getItems().stream().anyMatch(i -> i instanceof Cabbage);
        boolean isCarrotIn = c.getItems().stream().anyMatch(i -> i instanceof Carrot);

        if(isCabbageIn && isCarrotIn && isPotatoIn)
        {
            Renderer.addMessage("Получился суп");
            c.setItems(new ArrayList<>());
            //TODO: Добавить появление супа в кастрюле
            return true;
        }
        return false;
    }
    private static boolean cookPasta(Container c)
    {
        boolean isTomatoIn = c.getItems().stream().anyMatch(i -> i instanceof Tomato);
        boolean isOnionIn = c.getItems().stream().anyMatch(i -> i instanceof Onion);
        //TODO: Добавить проверку на макароны, чеснок, зелень и сыр.
        if(isOnionIn && isTomatoIn /*&& прочее */) {
            c.setItems(new ArrayList<>());
            //c.getItems().add();Добавляем готовую пасту
            Renderer.addMessage("Получилась паста");
            return true;
        }
        return false;
    }
}
