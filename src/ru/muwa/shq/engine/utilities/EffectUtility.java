package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.player.Player;

import java.util.HashMap;
import java.util.Map;

import static ru.muwa.shq.engine.utilities.EffectUtility.Effects.SPEED;

public class EffectUtility {
    static private EffectUtility instance;
    private EffectUtility(){
        instance = this;
        currentEffects = new HashMap<>();
        currentEffects.put(SPEED,0L);

    }
    public static EffectUtility getInstance(){
        if(instance==null) return new EffectUtility();
        else return instance;
    }

    public enum Effects {
        SPEED
    }

    private HashMap<Effects,Long> currentEffects;

    public HashMap<Effects, Long> getCurrentEffects() {
        return currentEffects;
    }

    public void work (){
       for(Map.Entry<Effects,Long> entry :currentEffects.entrySet())
       {
           switch (entry.getKey()) {

               case SPEED:
                   if(entry.getValue() > System.currentTimeMillis()){
                       Player.get().setSpeed(15);

                       System.out.println(Player.get().getSpeed());
                   } else Player.get().setSpeed(7);
                   break;

           }
       }
    }

}
