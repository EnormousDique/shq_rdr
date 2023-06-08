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
    private static long lastTimeSpeedWithdrawalNotification = 0l;
    public static boolean highOnSpeed,isDrunk;

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
        SPEED_WITHDRAWAL,
        DRUNK;

    }

    public static void work() {
        meterLock();
        thirstMetr();
        psychOmetr();
        sleepy();
        hungry();
        stamina();
        for (Map.Entry<Effects, Long> entry : currentEffects.entrySet()) {
            switch (entry.getKey()) {

                case SPEED:
                    if (entry.getValue() > System.currentTimeMillis()) {
                        Player.get().setStamina(Player.get().getStamina()+2);
                        highOnSpeed=true;
                    }else highOnSpeed = false;
                    break;

                case StaminaRegen:
                    if(entry.getValue() > System.currentTimeMillis()){
                        Player.get().setStamina(Player.get().getStamina()+1);
                        Player.get().setHp(Math.random()>0.99&&Player.get().getHp()<99?Player.get().getHp()+1.0:Player.get().getHp());

                    }

                case ODYSHKA:
                    if(entry.getValue()>System.currentTimeMillis()) Player.get().setStamina(Player.get().getStamina()-1);
                    break;
                case DRUNK:
                    if(entry.getValue()>System.currentTimeMillis()) isDrunk=true;
                    else isDrunk=false;
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
                        if(System.currentTimeMillis()>lastTimeSpeedWithdrawalNotification+15_000) {
                            Renderer.addMessage(Math.random() > 0.5 ? "Трясёт без порошка" : "Нюхнуть бы.. потряхивает");
                            lastTimeSpeedWithdrawalNotification = System.currentTimeMillis();
                        }
                        if(Player.get().getHighMeter()<Player.get().getHighMeterLock()) Player.get().setHighMeter(Player.get().getHighMeter()+0.001);

                    }else
                    {
                        CameraUpdateUtility.isShaking = false;
                    }
                    break;
            }
        }
    }

    private static void stamina() {
        if(Player.get().getStamina() < 100)
            Player.get().setStamina(Player.get().getStamina()+0.3);
    }

    private static long lastTimeFoodNotification;
    private static void hungry(){
        Player.get().hunger-= 0.005;
        if(Player.get().hunger<20){
            Player.get().setSpeed(Player.get().hungrySpeed);
            if(System.currentTimeMillis()>lastTimeFoodNotification+15_000){
                Renderer.addMessage("Жрать охота");
                lastTimeFoodNotification=System.currentTimeMillis();
            }
        } else Player.get().setSpeed(Player.get().getRegSpeed());
        if(Player.get().hunger>70) Player.get().setHp(Player.get().getHp()+0.02);

        if(Player.get().hunger>100) Player.get().hunger=100;
        if(Player.get().hunger<0) Player.get().hunger=0;


    }
    private static long lastTimeSleepNotification;
    private static long asleepTimer;
    private static void sleepy() {
        Player.get().awake -= 0.002;

        if(Player.get().awake<10 && lastTimeSleepNotification < System.currentTimeMillis()+15_000)
        {
            lastTimeSleepNotification = System.currentTimeMillis();
            Renderer.addMessage("Очень хочется спать...");
            Renderer.addMessage("Могу отрубиться...");
        }
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
    private static long lastTimeThirstNotification=0;
    public static void thirstMetr(){
        if(Player.get().getThirst() > 0 ){
         Player.get().setThirst(Player.get().getThirst()-0.01);
        }
        if(Player.get().getThirst()>50){
            Player.get().setStamina(Player.get().getStamina()+0.2);
        }
        if(Player.get().getThirst()<20){
            Player.get().setStamina(Player.get().getStamina()-0.2);
            if(System.currentTimeMillis()>lastTimeThirstNotification+15_000){
                Renderer.addMessage("Очень хочется пить..");
                lastTimeThirstNotification = System.currentTimeMillis();
            }
        }

    }

}


