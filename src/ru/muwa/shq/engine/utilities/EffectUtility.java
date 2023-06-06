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
        meterLock();
        thirstMetr();
        psychOmetr();
        sleepy();
        hungry();
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

    private static void hungry(){
        Player.get().hunger-= 0.005;
    }
    private static void sleepy() {
        Player.get().awake -= 0.002;
    }

    //todo вынести в отдельный класс
public static void meterLock(){
    if(Player.get().getHighMeterLock()>100)  Player.get().setHighMeterLock(100.0);
    if(Player.get().getHighMeter()>100) Player.get().setHighMeter(100.0);
    if (Player.get().getStamina()>100) Player.get().setStamina(100);
    if (Player.get().getThirst()>100) Player.get().setThirst(100);
    if(Player.get().getThirst()<0) Player.get().setThirst(0);
    if(Player.get().getStamina()<0) Player.get().setStamina(0);
    if(Player.get().getHighMeter()<0) Player.get().setHighMeter(0);
    if(Player.get().getHighMeterLock()<0) Player.get().setHighMeterLock(0);
    if(Player.get().getHp()<-1) Player.get().setHp(-1);
    if(Player.get().getHp()>100) Player.get().setHp(100);
    if(Player.get().pee>100)Player.get().pee=100;
    if(Player.get().poo>100)Player.get().poo=100;
    if(Player.get().awake>100)Player.get().awake=100;
    if(Player.get().pee<0)Player.get().pee=0;
    if(Player.get().poo<0)Player.get().poo=0;
    if(Player.get().awake<0)Player.get().awake=0;
}
    public static void psychOmetr() {
       if (Player.get().getHighMeter() > Player.get().getHighMeterLock()) {
            Player.get().setHighMeter(Player.get().getHighMeter() - (Player.get().getHighMeterLock() > 50? 0.005 : 0.02));
        }
    }
    public static void thirstMetr(){
        if(Player.get().getThirst() > 0 ){
         Player.get().setThirst(Player.get().getThirst()-0.01);
        }
    }
}


