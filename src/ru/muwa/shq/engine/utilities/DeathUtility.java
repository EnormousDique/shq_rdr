package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class DeathUtility {
    public static void work()
    {
        if(Player.get().getHp() <=0)
        {
            die();
        }
    }
    private static void die()
    {
        Player.get().setIsBusy(true);
        Inventory.getInstance().setIsOpened(false);
        Arrays.stream(HUD.getInstance().getDeathWindow().getComponents()).forEach(HUD.getInstance().getDeathWindow()::remove);

        HUD.getInstance().getDeathWindow().setVisible(true);
        JLabel deathLabel = new JLabel("ПОРТАЧЕНО");
        JButton deathButton = new JButton("OK");
        HUD.getInstance().getDeathWindow().add(deathLabel);
        HUD.getInstance().getDeathWindow().add(deathButton);
        deathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(deathButton))
                {
                    resurrect();
                }
            }
        });

    }
    private static void resurrect()
    {
        try {
            Engine.switchLevel(HubHataIgoryana.getInstance(),110,110);
        } catch (IOException e) {
            System.out.println("Не могу воскресить игрока : не грузит хаб");
        }
        Player.get().setIsBusy(false);
        Player.get().setHp(100);
        HUD.getInstance().getDeathWindow().setVisible(false);
    }
}
