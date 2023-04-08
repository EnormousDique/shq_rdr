package ru.muwa.shq.engine.combat;

import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.creatures.npc.enemies.BadGuy0;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.spawner.Spawner;
import ru.muwa.shq.items.BluntWeapons.BaseballBat;
import ru.muwa.shq.items.guns.Firearm;
import ru.muwa.shq.items.guns.Makarov;
import ru.muwa.shq.items.guns.Weapon;
import ru.muwa.shq.objects.containers.Corpse;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;


import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.io.File;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class CombatUtility {
    public static void work()
    {
        updateAttackZone();
        for(NPC c: Engine.getCurrentLevel().getNPC()){
            if(c.getSolidBox().intersects(Player.get().getSolidBox())){
                Player.get().setHp(Player.get().getHp()-1);
            }
        }
    }
    public static void updatePlayerTexture(){ // метод который отвечает за изменение текстуры игрока взависимости от его оружия
        try {

            boolean isbaseballBatEquipped = false;
            boolean isMakarovEquipped = false;
            Weapon bat = null;
            Weapon gun = null;
            for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) { // проходимся циклом фор по инвентарю
                if (Inventory.getInstance().getItems().get(i).isEquipped() &&
                        Inventory.getInstance().getItems().get(i) instanceof BaseballBat) { // если предмет экипирован и бейспольная
                    isbaseballBatEquipped = true;
                    bat = (Weapon) Inventory.getInstance().getItems().get(i);
                }
                else if((Inventory.getInstance().getItems().get(i).isEquipped() &&
                        Inventory.getInstance().getItems().get(i) instanceof Makarov)) { // если предмет экипирован и он макаров
                    isMakarovEquipped = true   ;
                    gun = (Weapon) Inventory.getInstance().getItems().get(i);
                }
            }
            if (isMakarovEquipped && gun instanceof Makarov) {
                Player.get().setTexture(ImageIO.read(new File(IMG_PATH + "player\\kulaginPistol3.png")));
            }
            if (isbaseballBatEquipped && bat instanceof BaseballBat) {   // проверка на то что оружие экипированно и Бита.
                Player.get().setTexture(ImageIO.read(new File(IMG_PATH + "player\\kulaginBat2.png")));// устанавливаем текстуру игрока на текстуру с битой
            }
            else if(!isbaseballBatEquipped && !isMakarovEquipped) {
                Player.get().setTexture(ImageIO.read(new File(IMG_PATH + "player\\kulaginFist.png"))); // если бита не жкипирована и оружие в руках не ьита то стандартная текстура
            }
        } catch (Exception e){
            System.out.println("Failed to load player rexture");
        }
    }
    public static void updateAttackZone(){

        boolean holdingGun = false;
        int zoneHeight = 50;

        for(int i = 0; i< Inventory.getInstance().getItems().size(); i++)

            if(Inventory.getInstance().getItems().get(i).isEquipped()
            &&
                Inventory.getInstance().getItems().get(i) instanceof Firearm)
                holdingGun = true;

        if(holdingGun)  zoneHeight = 100;

        Player.get().getAttackZone().setBounds(Player.get().getX(),Player.get().getY() + 70,70,70);//todo поиграться с отталкиванием холодным оружием. пока норм
        AffineTransform rotate = AffineTransform.getRotateInstance(-Math.toRadians(Aim.getInstance().calculateAngle()),Player.get().getSolidBox().getCenterX(),Player.get().getSolidBox().getCenterY());
        Player.get().setAttackZone(rotate.createTransformedShape(Player.get().getAttackZone()).getBounds());
    }

    /**
     * Функция обратного вызова для службы обработки боя. Позволяет нанести указанный урон целевому существу
     * @param victim - жертва
     * @param damage - урон
     */
    public static void attack(Creature victim, int damage){

        victim.setHp(victim.getHp()-damage);

        //TODO: Сделать нормальный механизм смерти. Пока так.
        if( (!Player.get().equals(victim)) && victim.getHp() <= 0 ) {
            Engine.getCurrentLevel().getNPC().remove(victim);
            Engine.getCurrentLevel().getContainers().add(new Corpse(victim.getX(),victim.getY(),victim.getcorpseimg(), victim.getRandomLoot()));
           // Spawner.decreaseSpawnCounter();


        }

    }

}
