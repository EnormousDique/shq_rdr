package ru.muwa.shq.quests.conditions;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.zakladki.KladBlue;
import ru.muwa.shq.items.zakladki.KladRed;
import ru.muwa.shq.items.zakladki.KladYellow;
import ru.muwa.shq.player.Inventory;

public class C_HasPlayerFoundKlad extends TaskCondition{
    @Override
    public boolean checkCondition() {
        for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
        {
            Item item = Inventory.getInstance().getItems().get(i);
            if(item instanceof KladBlue || item instanceof KladRed || item instanceof KladYellow)
            {
                return true;
            }
        }
        return false;
    }
}
