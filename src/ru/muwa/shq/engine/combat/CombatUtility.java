package ru.muwa.shq.engine.combat;

import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.creatures.npc.enemies.BadGuy0;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.spawner.Spawner;
import ru.muwa.shq.items.guns.Firearm;
import ru.muwa.shq.objects.containers.Corpse;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;


import java.awt.geom.AffineTransform;

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
    public static void updateAttackZone(){

        boolean holdingGun = false;
        int zoneHeight = 50;

        for(int i = 0; i< Inventory.getInstance().getItems().size(); i++)

            if(Inventory.getInstance().getItems().get(i).isEquipped()
            &&
                Inventory.getInstance().getItems().get(i) instanceof Firearm)
                holdingGun = true;

        if(holdingGun)  zoneHeight = 100;

        Player.get().getAttackZone().setBounds(Player.get().getX(),Player.get().getY() + 10,60,zoneHeight);
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
