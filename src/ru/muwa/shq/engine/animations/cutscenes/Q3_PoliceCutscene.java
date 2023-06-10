package ru.muwa.shq.engine.animations.cutscenes;

import ru.muwa.shq.creatures.npc.enemies.AimingGuy;
import ru.muwa.shq.creatures.npc.questnpc.Hachique;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.market.MarketInteriors;
import ru.muwa.shq.player.Player;

import java.util.ArrayList;

public class Q3_PoliceCutscene extends Cutscene{
    private static Q3_PoliceCutscene instance;
    private Q3_PoliceCutscene(){

        instance = this;
        movements = new ArrayList<>();

        Engine.getCurrentLevel().getNPC().add(new AimingGuy(Player.get().getX() + 250, Player.get().getY()-150,"Ment1"));
        Engine.getCurrentLevel().getNPC().add(new AimingGuy(Player.get().getX() + 350, Player.get().getY()-100,"Ment2"));
        Engine.getCurrentLevel().getNPC().add(new AimingGuy(Player.get().getX() + 100, Player.get().getY()-200,"Ment3"));


        movements.add(new Movement("Ment3", Player.get().getX()+50,Player.get().getY()-50));
        movements.add(new Movement("Ment2", Player.get().getX()+260,Player.get().getY()-50));
        movements.add(new Movement("Ment1", Player.get().getX()-50,Player.get().getY()+150));

    }

    public static Q3_PoliceCutscene getInstance(){
        if(instance ==null)return new Q3_PoliceCutscene();
        else return instance;
    }

}
