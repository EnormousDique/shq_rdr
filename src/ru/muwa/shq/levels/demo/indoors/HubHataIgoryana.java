package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.craftsmanship.cooking.Cook;
import ru.muwa.shq.creatures.npc.enemies.BadGuy0;
import ru.muwa.shq.dialogues.demo.Conversation1;
import ru.muwa.shq.dialogues.mom.MomDialog;
import ru.muwa.shq.economics.money.Money_500;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.items.consumables.CellPhone;
import ru.muwa.shq.items.consumables.Potato;
import ru.muwa.shq.items.zakladki.KladYellow;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.minigames.PostBoxShq;
import ru.muwa.shq.objects.bounds.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.objects.containers.HubChest;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.*;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class HubHataIgoryana extends Level
{


    private static HubHataIgoryana instance;
    public static HubHataIgoryana getInstance() throws IOException {
        if(instance == null) return new HubHataIgoryana(); else return instance;

    }
    private HubHataIgoryana() throws IOException {
        this.isInDoors = true;
        instance = this;
        startPosX = 300;
        startPosY = 300;
        // текстура хаба
        objects.add(new HubBuild(0,0));
        // хабовый сундук
        containers.add(new HubChest(700,5));
        for (int i = 0; i<6; i++)containers.get(0).addItem(new Money_500());
        containers.get(0).addItem(new KladYellow());

        //выход в падик
        zones.add(new EnterZone(30, 740,100,100,FatBuildingFloor5.getInstance(), 30,30,false));
        // диалог с мамой
        zones.add(new DialogueZone(new MomDialog(),100,100,100,100,false));

        zones.add(new DialogueZone(new MomDialog(),0,0,250,250,false));
       // zones.add(new GifSceneZone(200,200,70,70,true));

        /** Используемые предметы **/
        //Сон
        zones.add(new SleepZone(740,5,50,100));
        //Туалет
        zones.add(new InteractionZone(130,715,80,80) {
            @Override
            public void use() {
                Player.get().pee=0;
                Player.get().poo=0;
                Renderer.addMessage("Поссал и посрал");
                Renderer.addMessage("(Облегчился)");
            }
        });
        //Кухня
        //Плита
        containers.add(new HubChest(640,740));
        zones.add(new InteractionZone(630,660,100,100) {
            @Override
            public void use() {
                Container pot = containers.get(1); //СЕЙЧАС "КАСТРЮЛЯ" ЭТО ВТОРОЙ ПО СЧЕТУ КОНТЕЙНЕР В УРОВНЕ.
                if(!Cook.cook(pot)) Renderer.addMessage("Из такой хуйни супа не свришь.");

            }
        });
        //Раковина
        zones.add(new InteractionZone(360,660,100,100) {
            @Override
            public void use() {
                if (Player.get().pee < 90) {
                    Player.get().setThirst(100);
                    Renderer.addMessage("Попил из крана");
                    Player.get().pee += 20;
                }else{
                    Renderer.addMessage("не могу попить из крана");
                    Renderer.addMessage("надо поссать");

                }
            }
        });
        // стены хаюа
        objects.add(new Wall350(351,-50));
        objects.add(new Wall350(701,-50));
        objects.add(new Wall300(-50,470));
        objects.add(new Wall300(795,470)); //правая
        objects.add(new Wall300(-50,95));
        objects.add(new Wall300(795,95)); //правая
        objects.add(new Wall300(795,160)); //правая
        objects.add(new Wall300(-50,160));
        objects.add(new Wall300(-50,-160));
        objects.add(new Wall300(795,-160)); //правая
        objects.add(new Wall350(0,-50));
        objects.add(new Wall350(-48,800));//нижнаяя
        objects.add(new Wall350(308,800));//нижнаяя
        objects.add(new Wall350(608,800));//нижнаяя

        //zones.add(new MiniGameZone(300,100,50,50,new PostBoxShq()));

        // невидимые стены дома

        //test
        /*
        zones.add(new InteractionZone(400,400,400,400) {
            @Override
            public void use() {
                try {
                    Robot r = new Robot();
                    int xoff = GameScreen.getInstance().getX();
                    int yoff = GameScreen.getInstance().getY();
                    r.mouseMove(Player.get().getX()+xoff- Camera.getInstance().getX(),Player.get().getY()+yoff-Camera.getInstance().getY());
                }catch (Exception e){Renderer.addMessage("ты не должен это видеть");}
            }
        });

         */
    }
}
