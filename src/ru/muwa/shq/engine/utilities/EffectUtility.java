package ru.muwa.shq.engine.utilities;
import ru.muwa.shq.player.*;

import java.util.HashMap;
import java.util.Map;

import static ru.muwa.shq.engine.utilities.EffectUtility.Effects.SPEED;
import static ru.muwa.shq.engine.utilities.EffectUtility.Effects.SPEED;
import static ru.muwa.shq.engine.utilities.EffectUtility.Effects.StaminaRegen;


public class EffectUtility {
    private static HashMap<Effects, Long> currentEffects;

    public static HashMap<Effects, Long> getCurrentEffects() {
        return currentEffects;
    }

    static {
        currentEffects = new HashMap<>();
        currentEffects.put(SPEED, 0L);
        currentEffects.put(StaminaRegen, 0L);
    }

    public enum Effects {
        SPEED,
        StaminaRegen
    }

    public static void work() {
        for (Map.Entry<Effects, Long> entry : currentEffects.entrySet()) {
            switch (entry.getKey()) {
                case SPEED:
                    if (entry.getValue() > System.currentTimeMillis()) {
                        Player.get().setSpeed(19);
                    } else Player.get().setSpeed(Player.get().getSpeed());
                    break;
                case StaminaRegen:
                {
                    if(entry.getValue() > System.currentTimeMillis()){
                        Player.get().setStamina(100);
                    }//else Player.get().setStamina(99);
                }

            }
        }
    }
    //todo вынести в отдельный класс

    public static void psychOmetr() {

        if (Player.get().getHighMeter() > 0 && Player.get().getHighMeterLock() < 50) {
            Player.get().setHighMeter((Player.get().getHighMeter() - 0.02));
        }else if (Player.get().getHighMeter() > 50 && Player.get().getHighMeterLock() >50) {
            Player.get().setHighMeter((Player.get().getHighMeter() - 0.01));
        }
    }
    public static void thirstMetr(){
        if(Player.get().getThirst() > 0 ){
         Player.get().setThirst(Player.get().getThirst()-0.001);
        }
    }
}


