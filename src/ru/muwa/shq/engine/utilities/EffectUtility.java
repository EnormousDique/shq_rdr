package ru.muwa.shq.engine.utilities;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.camera.CameraUpdateUtility;
import ru.muwa.shq.player.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static ru.muwa.shq.engine.utilities.EffectUtility.Effects.*;


public class EffectUtility {
    private static HashMap<Effects, Long> currentEffects;
    public static boolean isPlayerAddictedToSpeed =false;

    public static HashMap<Effects, Long> getCurrentEffects() {
        return currentEffects;
    }

    static {
        currentEffects = new HashMap<>();
        currentEffects.put(SPEED, 0L);
        currentEffects.put(StaminaRegen, 0L);
        currentEffects.put(ODYSHKA,0L);
        currentEffects.put(STONED,0L);
        currentEffects.put(SPEED_WITHDRAWAL,0L);
    }

    public enum Effects {
        SPEED,
        StaminaRegen,
        ODYSHKA,
        STONED,
        SPEED_WITHDRAWAL;

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
                        Player.get().setHp(Math.random()>0.99&&Player.get().getHp()<99?Player.get().getHp()+1.0:Player.get().getHp());

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

                case SPEED_WITHDRAWAL:
                    if(Player.get().getHighMeter() >= 5 && currentEffects.get(SPEED) + 20_000 < System.currentTimeMillis() && isPlayerAddictedToSpeed )
                    {
                        CameraUpdateUtility.isShaking = true;
                    }
                    else
                    {
                        CameraUpdateUtility.isShaking = false;
                    }
                    break;
            }
        }
    }
    //todo вынести в отдельный класс

    public static void psychOmetr() {
       if (Player.get().getHighMeter() > Player.get().getHighMeterLock()) {
            Player.get().setHighMeter(Player.get().getHighMeter() - (Player.get().getHighMeterLock() > 50? 0.005 : 0.02));
        }
    }
    public static void thirstMetr(){
        if(Player.get().getThirst() > 0 ){
         Player.get().setThirst(Player.get().getThirst()-0.001);
        }
    }
}


