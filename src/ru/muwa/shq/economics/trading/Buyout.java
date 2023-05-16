package ru.muwa.shq.economics.trading;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.ItemPanel;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.BuyoutZone;
import ru.muwa.shq.zones.GameZone;
import ru.muwa.shq.zones.TradeZone;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Buyout {

    public ArrayList<Item> goods = new ArrayList<>();

    private BuyoutButtonListener listener = new BuyoutButtonListener();


    public abstract void init();
    public void trade()
    {
        Engine.pause = true;
        HUD.getInstance().getActionWindow().setVisible(true);
        HUD.getInstance().getActionWindow().setLayout(null);
        Inventory.getInstance().setIsOpened(true);
        Arrays.stream(HUD.getInstance().getActionWindow().getComponents()).forEach(HUD.getInstance().getActionWindow()::remove);

        int x =0;
        int y=0;

        JButton exitButton = new JButton("Выход");
        exitButton.addActionListener(listener);
        HUD.getInstance().getActionWindow().add(exitButton);
        exitButton.setBounds(200,0,100,50);

        if(goods.size()>0) {

            for (int i = 0 ; i< goods.size(); i++) {
                ItemPanel p = new ItemPanel(goods.get(i));
                HUD.getInstance().getActionWindow().add(p);
                p.setBounds(x, y * 50, 50, 50);
                p.addMouseListener(MouseButtonListener.getInstance());

                String s = "цена : " + goods.get(i).getPrice();
                JLabel l = new JLabel(s);
                HUD.getInstance().getActionWindow().add(l);
                l.setBounds(50,y*50,100,50);

                y++;
            }

            JButton sell = new JButton("Слить");
            HUD.getInstance().getActionWindow().add(sell);
            sell.setBounds(200,100,100,50);
            sell.addActionListener(listener);
        }

        HUD.getInstance().getActionWindow().updateUI();

    }
    private static class BuyoutButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(((JButton)e.getSource()).getText().equals("Выход"))
            {
                for (GameZone z : Engine.getCurrentLevel().getZones())
                    if (z instanceof BuyoutZone)
                    {
                        for(Item i : ((BuyoutZone)z).buyout.goods) Inventory.getInstance().addItem(i);
                        ((BuyoutZone)z).buyout.goods = new ArrayList<>();
                        ((BuyoutZone) z).isActive = false;
                        HUD.getInstance().getActionWindow().setVisible(false);
                        Engine.pause = false;
                    }
            }

            if(((JButton)e.getSource()).getText().equals("Слить"))
                for(GameZone z : Engine.getCurrentLevel().getZones())
                    if(z instanceof BuyoutZone && ((BuyoutZone)z).isActive)
            {
                 int i = 0;
                 for(Item item : ((BuyoutZone)z).buyout.goods)
                 {
                     i += item.getPrice();
                 }
                ((BuyoutZone)z).buyout.goods = new ArrayList<>();
                Player.get().money = Player.get().money+i;
            }
        }
    }

}
