package ru.muwa.shq.engine.animations.cutscenes;

import ru.muwa.shq.creatures.npc.questnpc.Hachique;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.player.Player;

import java.util.ArrayList;

/**
 * Катсцена для первой задачи второго квеста. К игроку подходит хачик на рынке.
 */
public class Q2T1_Cutscene extends Cutscene {
    private static Q2T1_Cutscene instance;
    private Q2T1_Cutscene(){

        instance = this;
        movements = new ArrayList<>();

        Engine.getCurrentLevel().getNPC().add(new Hachique(Player.get().getX() + 250, Player.get().getY(),"Vasya"));
        movements.add(new Movement("Vasya", Player.get().getX()+100,Player.get().getY()));
        /*
        movements.add(new Movement("Hachique",300,300));
        movements.add(new Movement("Hachique",400,400));
        movements.add(new Movement("Vasya",480,400));
        */

    }

    public static Q2T1_Cutscene getInstance(){
        if(instance ==null)return new Q2T1_Cutscene();
        else return instance;
    }


}
