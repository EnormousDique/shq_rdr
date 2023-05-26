package ru.muwa.shq.economics.trading;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.ItemPanel;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.GameZone;
import ru.muwa.shq.zones.TradeZone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Trade {
    protected ArrayList<Item> goods = new ArrayList<>();
    JButton exitButton = new JButton("Выход");
    TradeButtonListener listener = new TradeButtonListener();
    public abstract void setGoods();
    public void trade(){

        Engine.pause = true;
        HUD.getInstance().getActionWindow().setVisible(true);
        HUD.getInstance().getActionWindow().setLayout(null);
        Arrays.stream(HUD.getInstance().getActionWindow().getComponents()).forEach(HUD.getInstance().getActionWindow()::remove);

        int x = 0;
        int y = 0;

        for(Item i : goods)
        {
            JLabel l = new JLabel(new ImageIcon( i.getTexture()));
            HUD.getInstance().getActionWindow().add(l);
            l.setBounds(0,y*50,50,50);

            String s = "цена : " + i.getPrice();
            JButton b = new JButton(s);
            HUD.getInstance().getActionWindow().add(b);
            b.setBounds(50,y*50,100,50);
            b.addActionListener(listener);

            HUD.getInstance().getActionWindow().add(exitButton);
            exitButton.setBounds(HUD.getInstance().getActionWindow().getWidth()-100,
                   0,100,50);
            exitButton.addActionListener(listener);

            y++;
        }
    }
    private static class TradeButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(((JButton)e.getSource()).getText().equals("Выход")) for(GameZone z : Engine.getCurrentLevel().getZones()) if(z instanceof TradeZone){ ((TradeZone) z).isActive = false; HUD.getInstance().getActionWindow().setVisible(false);Engine.pause = false;}

            for(GameZone z : Engine.getCurrentLevel().getZones())
            {
                if(z instanceof TradeZone && ((TradeZone)z).isActive)
                {
                    TradeZone zz = (TradeZone)z;  //Наша торговая зона
                    Item i = null; //Искомая вещь (мы попытаемся определить на какую вещь нажал игрок)
                    String key = ((JButton)e.getSource()).getText(); // строка - ключ, в которой содержится инфо о цене вещи
                    for(Item ii : zz.trade.goods)
                    {
                        if(key.contains(""+ii.getPrice())) i = ii;
                    }
                    if(i!=null)//Если вещь нашлась
                    {
                        if(Player.get().money >= i.getPrice())//Смотрим хватает ли денег
                        {
                            Inventory.getInstance().addItem(i);
                            Player.get().money = Player.get().money - i.getPrice();
                        }else /* если нехватает */ {
                            Renderer.addMessage("Нехватает бабок! (лох)");
                        }
                    }
                }
            }
        }
    }
}
