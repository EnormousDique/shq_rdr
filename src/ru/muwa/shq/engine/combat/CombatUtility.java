package ru.muwa.shq.engine.combat;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.player.Player;

public class CombatUtility {
    private static CombatUtility instance;
    public static CombatUtility getInstance() {
        if(instance == null) return new CombatUtility();else return instance;
    }
    private CombatUtility (){
        instance = this;
    }
    public void work(){
        System.out.println(Player.get().getHp());
        for(NPC c: Engine.getCurrentLevel().getNPC()){
            if(c.getSolidBox().intersects(Player.get().getSolidBox())){
                Player.get().setHp(Player.get().getHp()-1);

            }

        }
    }

}
