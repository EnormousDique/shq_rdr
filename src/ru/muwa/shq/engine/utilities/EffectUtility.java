package ru.muwa.shq.engine.utilities;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.player.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static ru.muwa.shq.engine.utilities.EffectUtility.Effects.*;


public class EffectUtility {
    private static HashMap<Effects, Long> currentEffects;

    public static HashMap<Effects, Long> getCurrentEffects() {
        return currentEffects;
    }

    static {
        currentEffects = new HashMap<>();
        currentEffects.put(SPEED, 0L);
        currentEffects.put(StaminaRegen, 0L);
        currentEffects.put(ODYSHKA,0L);
    }

    public enum Effects {
        SPEED,
        StaminaRegen,
        ODYSHKA,
        STONED;

    }

    public static void work() {
        for (Map.Entry<Effects, Long> entry : currentEffects.entrySet()) {
            switch (entry.getKey()) {

                case SPEED:
                    if (entry.getValue() > System.currentTimeMillis()) {
                        Player.get().setStamina(Player.get().getStamina()+2);
                    }
                    break;

                case StaminaRegen:
                    if(entry.getValue() > System.currentTimeMillis()){
                        Player.get().setStamina(Player.get().getStamina()+1);
                        Player.get().setHp(Math.random()>0.99&&Player.get().getHp()<99?Player.get().getHp()+1:Player.get().getHp());

                    }

                case ODYSHKA:
                    if(entry.getValue()>System.currentTimeMillis()) Player.get().setStamina(Player.get().getStamina()-1);
                    break;


                case STONED:
                    if (entry.getValue()>System.currentTimeMillis())
                    {
                        Renderer.getInstance().isDrawingBg = false;
                        if(Math.random()>0.98 && Player.get().getHighMeter()>1)
                        {
                            Player.get().setHighMeterLock(Player.get().getHighMeterLock()-1);
                            if(Math.random()>0.66) Player.get().setHighMeter(Player.get().getHighMeter()+1);
                        }

                        Player.get().isConfused=true;
                    }
                    else {
                        Player.get().isConfused=false;
                        Renderer.getInstance().isDrawingBg = true;
                    }


                    break;
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


