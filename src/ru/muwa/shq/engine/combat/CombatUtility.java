package ru.muwa.shq.engine.combat;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;

import java.awt.geom.AffineTransform;

public class CombatUtility {
    private static CombatUtility instance;
    public static CombatUtility getInstance() {
        if(instance == null) return new CombatUtility();else return instance;
    }
    private CombatUtility (){
        instance = this;
    }
    public void work(){
        // System.out.println(Player.get().getHp());
        updateAttackZone();
        for(NPC c: Engine.getCurrentLevel().getNPC()){
            if(c.getSolidBox().intersects(Player.get().getSolidBox())){
                Player.get().setHp(Player.get().getHp()-1);

            }

        }
    }
    public void updateAttackZone(){

        AffineTransform at = AffineTransform.getTranslateInstance(Player.get().getX()-Camera.getInstance().getX(),Player.get().getY()-Camera.getInstance().getY());
        at.rotate(-Math.toRadians(Aim.getInstance().calculateAngle()),15,15);
        System.out.println(Player.get().getAttackZone().getBounds().x +" "+ Player.get().getAttackZone().getBounds().y +" "+ Player.get().getAttackZone().getBounds().width +" "+ Player.get().getAttackZone().getBounds().height);
        Player.get().getAttackZone().setBounds(Player.get().getX(),Player.get().getY()-30,30,30);

        System.out.println(Player.get().getAttackZone().getBounds().x +" "+ Player.get().getAttackZone().getBounds().y +" "+ Player.get().getAttackZone().getBounds().width +" "+ Player.get().getAttackZone().getBounds().height);

        Player.get().setAttackZone(at.createTransformedShape(Player.get().getAttackZone()).getBounds2D().getBounds());


    }

}
