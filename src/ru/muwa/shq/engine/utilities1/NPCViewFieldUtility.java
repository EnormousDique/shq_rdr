package ru.muwa.shq.engine.utilities1;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.Engine;

public class NPCViewFieldUtility {
    public static void work(){
        for(int i = 0; i< Engine.getCurrentLevel().getNPC().size(); i++)
        {
            NPC c = Engine.getCurrentLevel().getNPC().get(i);
            c.getViewField().setBounds(c.getX()-700, c.getY()-700,1_400,1_400);
        }
    }
}
