package ru.muwa.shq.engine.combat;

import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.items.bluntWeapons.BaseballBat;
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
            if(c.getSolidBox().intersects(Player.get().getSolidBox() ) && c.isEnemy){
                Player.get().setHp(Player.get().getHp()-0.2);
              //  CombatUtility.attack(Player.get(),0.2);
            }
        }
    }

    public static void updateAttackZone(){

        boolean holdingGun = false;
        int yOFF = 50;

        for(int i = 0; i< Inventory.getInstance().getItems().size(); i++)

            if(Inventory.getInstance().getItems().get(i).isEquipped()
            &&
                Inventory.getInstance().getItems().get(i) instanceof Firearm)
                holdingGun = true;

        if(holdingGun)  yOFF = 20;

        Player.get().getAttackZone().setBounds(Player.get().getX(),Player.get().getY() + yOFF,50,50);//todo поиграться с отталкиванием холодным оружием. пока норм
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
        if( (!Player.get().equals(victim)) && victim.getHp() <= 0 && Engine.getCurrentLevel().getNPC().contains(victim)) {
            Engine.getCurrentLevel().getNPC().remove(victim);
            Engine.getCurrentLevel().getContainers().add(new Corpse(victim.getX(),victim.getY(),victim.getcorpseimg(), victim.getRandomLoot()));

           // Spawner.decreaseSpawnCounter();
        }

    }

    }


