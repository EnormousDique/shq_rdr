package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;

public class DeathUtility {
    public static void work()
    {
        if(Player.get().getHp() <=0)
        {
            die();
        }
        if(Player.get().momHearts <=0 ) return;// System.exit(0);

    }
    public static boolean isDead = false;
    private static void die()
    {
        Player.get().playerHearts -=1;
        if(Player.get().playerHearts <=0 ) System.exit(0);
        resurrect();
        TimeMachine.rewind(720_000);
        Renderer.addMessage("Игрок потерял все здоровье");
        Renderer.addMessage("Он оклемался за сутки");
    }
    public static void resurrect()
    {
        Engine.pause=false;
        try {
            Engine.switchLevel(HubHataIgoryana.getInstance(),110,110);
        } catch (IOException e) {
            System.out.println("Не могу воскресить игрока : не грузит хаб");
        }
        isDead=false;
        Player.get().setIsBusy(false);
        Player.get().setHp(100);
        Player.get().hunger=40;
        Player.get().setThirst(50);
        Player.get().awake=90;
    }
}
