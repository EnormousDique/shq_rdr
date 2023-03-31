package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.ItemPanel;
import ru.muwa.shq.items.guns.Weapon;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

import java.util.Map;


public class InventoryManager
{



    private static void updateStatusWindow()
    {
        try {

            BufferedImage face = null;
            if (Player.get().getHp() <= 100) {
                face = ImageIO.read(new File(IMG_PATH + "face/FACE.png"));
            }
            if (Player.get().getHp() <= 70) {
                face = ImageIO.read(new File(IMG_PATH + "face/DAMAGEDFACENOBLOOD.png"));
            }
            if (Player.get().getHp() <= 40) {
                face = ImageIO.read(new File(IMG_PATH + "face/DAMAGEDFACE.png"));
            }
            if (Player.get().getHp() <= 10) {
                face = ImageIO.read(new File(IMG_PATH + "face/DAMAGEDFACEBLOOD.png"));
            }
            String s = " ";
            for (Map.Entry<EffectUtility.Effects, Long> entry : EffectUtility.getCurrentEffects().entrySet()) {
                switch (entry.getKey()) {
                    case SPEED:
                        //TODO добавить побольше проверок if на здоровье (типа когда он обутюженный и хп > 50 то на ебале кровь
                        if (entry.getValue() > System.currentTimeMillis()) {
                            s += "я под мефом";
                            face = ImageIO.read(new File(IMG_PATH + "face/FACEMEF.png"));
                        }
                        break;
                }

            }

            JLabel faceIcon = new JLabel(new ImageIcon(face));
            Arrays.stream(HUD.getInstance().getStatusWindow().getComponents()).forEach(HUD.getInstance().getStatusWindow()::remove);
            HUD.getInstance().getStatusWindow().add(faceIcon);
            HUD.getInstance().getStatusWindow().add(new JLabel("ТВОЁ ЗДОРОВЬЕ! Друг!"));
            HUD.getInstance().getStatusWindow().add(new JLabel(String.valueOf(Player.get().getHp())));
            JLabel effectsLabel = new JLabel(s);
            HUD.getInstance().getStatusWindow().add(effectsLabel);
            HUD.getInstance().getStatusWindow().updateUI();

        }catch (Exception e){
            System.out.println("НЕ ГРУЗИТ КАРТИНКУ СУКА");
        }
    }

    public static void drawContainerItems(Graphics g, Container c) {

    }
    public static void update()
    {

        Inventory.getInstance().setX(Player.get().getX() + 100);
        Inventory.getInstance().setY(Player.get().getY() - 50);

        Inventory.getInstance().getBox().setBounds
                (Inventory.getInstance().getX(),
                 Inventory.getInstance().getY(),
                (int)Inventory.getInstance().getImg().getWidth(),
                (int)Inventory.getInstance().getImg().getHeight());

        for(int i = 0; i < Inventory.getInstance().getItemIcons().size();i++)
        {
            if(Inventory.getInstance().getItemIcons().get(i)!=null) {
                Inventory.getInstance().getItemIcons().get(i).setBounds(Inventory.getInstance().getX()+(50*(i)),Inventory.getInstance().getY(),50,50);
            }

        }

        updateItemWindow();
        updateEquipWindow();
        updateStatusWindow();

        //TODO: Нужно перенести логику так, чтобы она вызывалась из PlayerControls.
        //TODO: Хули до сих пор не сделано)))))

    }
    //
    public static void grab(){
            // System.out.println("нажата левая кнопка мыши");
            for(Container c: Engine.getCurrentLevel().getContainers()){
                if(c.isInUse() && c.getItems().size()>= 1){
                    //System.out.println("есть открытый контейнер");
                    for(int i = 0; i < c.getIcons().size(); i++){

                        if (c.getIcons().get(i).contains(new Point(MouseListener.getInstance().getX()+ Camera.getInstance().getX(), MouseListener.getInstance().getY()+Camera.getInstance().getY()))){

                            c.grabItem(i);
                        }
                    }
                }
            }
    }

        private static void updateItemWindow(){

            //Получаем окно
            JPanel window = HUD.getInstance().getItemWindow();

            //Инициализация лок. переменных, которые будут нужны в цикле
            int itemsSize = Inventory.getInstance().getItems().size(); //Кол-во вещей
            ArrayList<ItemPanel> itemTiles = new ArrayList<>(); // Список панелек для вещей
            int yOffset = 50; // Смещение по оси у (для переноса строки каждые 4 панельки)
            int skip = 0;


            //Цикл
            //  Проходимся по вещам из инвентаря, и добавляем панель под каждую на окно ItemWindow
            for(int i = 0; i<Inventory.getInstance().getItems().size(); i++)
            {
                if(Inventory.getInstance().getItems().get(i).isEquipped()) skip++;
                if(!Inventory.getInstance().getItems().get(i).isEquipped()) {

                    if (Inventory.getInstance().getItems().get(i) != null) {
                        itemTiles.add(new ItemPanel(Inventory.getInstance().getItems().get(i)));
                        itemTiles.get(i-skip).addMouseListener(MouseButtonListener.getInstance());
                    }
                    if (Inventory.getInstance().getItems().get(i).getTexture() != null)
                        itemTiles.get(i-skip).setIcon(new ImageIcon(Inventory.getInstance().getItems().get(i).getTexture()));
                }
            }

            Arrays.stream(window.getComponents()).forEach(window::remove);

            //Добавляем заголовок
            JLabel titleLabel = new JLabel("BEW,N");
            window.add(titleLabel);
            titleLabel.setBounds(10,10,100,20);

            for(int i = 0; i<itemTiles.size(); i++)
            {
                if(i != 0 && i % 4 == 0){ yOffset += 50; }//Перенос строки после 4-х панелек
                window.add(itemTiles.get(i));
                itemTiles.get(i).setBounds(i%4*50,yOffset,40,40);

            }
        }
        private static void updateEquipWindow()
        {
            Arrays.stream(HUD.getInstance().getEquipWindow().getComponents()).forEach(HUD.getInstance().getEquipWindow()::remove);

            JLabel title = new JLabel("EQUIP");
            HUD.getInstance().getEquipWindow().add(title);
            title.setBounds(10,10,50,30);

            for(int i = 0; i<Inventory.getInstance().getItems().size(); i++)
            {
                Item item = Inventory.getInstance().getItems().get(i);

                if(item.isEquipped()){

                    ItemPanel panel = new ItemPanel(item);
                    HUD.getInstance().getEquipWindow().add(panel);
                    panel.setBounds(20,50,50,50);
                    panel.setIcon(new ImageIcon(panel.getItem().getTexture()));
                    panel.addMouseListener(MouseButtonListener.getInstance());

                    if(item instanceof Weapon){
                    String ammoString = ((Weapon) item).getCurrAmmo() + "/" + ((Weapon) item).getMaxAmmo();
                    JLabel ammoLabel = new JLabel(ammoString);
                    HUD.getInstance().getEquipWindow().add(ammoLabel);
                    ammoLabel.setBounds(30,120,100,30);
                    }
                }
            }
            HUD.getInstance().getEquipWindow().updateUI();
        }
}

