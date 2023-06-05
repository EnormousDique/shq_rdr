package ru.muwa.shq.engine.spawner;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.creatures.npc.enemies.AimingGuy;
import ru.muwa.shq.creatures.npc.enemies.BadGuy0;
import ru.muwa.shq.creatures.npc.enemies.VelvetTank;
import ru.muwa.shq.creatures.npc.questnpc.Hachique;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.car.Car;
import ru.muwa.shq.objects.car.HondaCivic;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.*;

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;

public class Spawner {

   private InDoorsSpawnZone spawnZone;

    public InDoorsSpawnZone getInDoorsSpawnZone(){

        return spawnZone;
    }

    private static final int MAX_NPC = 90;

    public static long getSpawnInterval() {
        return spawnInterval;
    }

    private static final int TIME_INTERVAL = 10_000;
    private static final int DISTANCE_INTERVAL = SCREEN_WIDTH / 2;

    private static long lastSpawnTime;  //Таймер
    private static long spawnInterval;
    private static int spawnedNPCCounter;
    public static void decreaseSpawnCounter(){
        spawnedNPCCounter--;
    }
    public static void updateTimers()
    {
        lastSpawnTime = System.currentTimeMillis();
        spawnInterval = TIME_INTERVAL;
        spawnedNPCCounter = 0;
    }
    public static int getSpawnedNPCCounter() {
        return spawnedNPCCounter;
    }
    public static void regularSpawn(){
        // Инициализация
        // Получаем уровень
        Level level = Engine.getCurrentLevel();
        if(level.isStreet()) {
            // если время последнего спавна + время спавн интервала меньше чем текущее время системы в милисекундах и счетчий спавна меньше или равен макс нпс
            if(lastSpawnTime + spawnInterval < System.currentTimeMillis() && spawnedNPCCounter <= MAX_NPC) {
                //спавним бэд гая
                int xOff = (int) (Math.random() + 1) * DISTANCE_INTERVAL *( Math.random() > 0.5? 1 : -1);
                int yOff = (int) (Math.random() + 1) * DISTANCE_INTERVAL *( Math.random() > 0.5? 1 : -1);
                NPC c = new BadGuy0(Player.get().getX() + xOff, Player.get().getY() + yOff); //
                spawnedNPCCounter++;
                level.getNPC().add(c);
                c.setRayCasterBorders(c.getRayCaster().buildLines(level.getObjects()));
                // спавним стреляющего гая
                xOff = (int) (Math.random() + 1) * DISTANCE_INTERVAL * (Math.random() > 0.5? 1 : -1);
                yOff = (int) (Math.random() + 1) * DISTANCE_INTERVAL * (Math.random() > 0.5? 1 : -1);
                c = new AimingGuy(Player.get().getX() + xOff, Player.get().getY() + yOff);
                if(Math.random() > 0.75) level.getNPC().add(c);
                spawnedNPCCounter++;
                c.setRayCasterBorders(c.getRayCaster().buildLines(level.getObjects()));
                // спавним танка
                xOff = (int) (Math.random() + 1) * DISTANCE_INTERVAL * (Math.random() > 0.5? 1 : -1);
                yOff = (int) (Math.random() + 1) * DISTANCE_INTERVAL * (Math.random() > 0.5? 1 : -1);
                c= new VelvetTank(Player.get().getX() + xOff, Player.get().getY() + yOff);
                level.getNPC().add(c);
                spawnedNPCCounter++;

                c.setRayCasterBorders(c.getRayCaster().buildLines(level.getObjects()));
                // спавним хачиков
                xOff = (int) (Math.random() + 1) * DISTANCE_INTERVAL * (Math.random() > 0.5? 1 : -1);
                yOff = (int) (Math.random() + 1) * DISTANCE_INTERVAL * (Math.random() > 0.5? 1 : -1);
                c= new Hachique(Player.get().getX() + xOff, Player.get().getY() + yOff);
                level.getNPC().add(c);

                spawnInterval = (int) ( (Math.random() + 1d) * (TIME_INTERVAL * 1.0));
                lastSpawnTime = System.currentTimeMillis();
            }
        }
    }
    // кусок кода отвечающий за появление бэдгая внутри квадрата InDoorsSpawnZone.
    public static void inDoorsSpawn() {
        Level level = Engine.getCurrentLevel();
        if (level.isInDoors()) {
            if(lastSpawnTime + spawnInterval < System.currentTimeMillis() && spawnedNPCCounter <= MAX_NPC) {
                for (GameZone z : Engine.getCurrentLevel().getZones()) {
                    if(z instanceof InDoorsSpawnZone) {
                        NPC c = new BadGuy0(z.x,z.y);//блять я ебал в рот это наъуй икс и игрик индорспавнзоны.
                        level.getNPC().add(c);
                        spawnedNPCCounter++;
                        c.setRayCasterBorders(c.getRayCaster().buildLines(level.getObjects()));
                        spawnInterval = (int) ((Math.random() + 1d) * (TIME_INTERVAL * 1.0));
                        lastSpawnTime = System.currentTimeMillis();
                    }
                }
            }
        }
    }
    static long carLastSpawnTime = System.currentTimeMillis();
   static long carSpawnInterval = 1000;

    public static void carSpawn(){
        for (GameZone zone : Engine.getCurrentLevel().getZones()) {
            if (zone instanceof CarSpawnZone) {

                if(System.currentTimeMillis() >carLastSpawnTime + carSpawnInterval ){
                        Car car = new HondaCivic(zone.x,zone.y);
                        car.setDirection(GameObject.Direction.RIGHT);
                        Engine.getCurrentLevel().getObjects().add(car);
                        carLastSpawnTime= System.currentTimeMillis();
                     }
                    }
                }
    }
}
