package ru.muwa.shq.engine.utilities;
import ru.muwa.shq.player.*;

import java.util.HashMap;
import java.util.Map;

import static ru.muwa.shq.engine.utilities.EffectUtility.Effects.SPEED;

public class EffectUtility {


    private static HashMap<Effects,Long> currentEffects;

    public static HashMap<Effects, Long> getCurrentEffects() {
        return currentEffects;
    }


    static {

        currentEffects = new HashMap<>();
        currentEffects.put(SPEED,0L);

    }
    public enum Effects {
        SPEED
    }


    public static void work (){
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
