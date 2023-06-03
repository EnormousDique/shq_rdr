package ru.muwa.shq.quests;

import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.player.Player;

import java.awt.*;
import java.util.ArrayList;

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;

public class QuestHUD {
    public static boolean opened;
    static int x = (int)(SCREEN_WIDTH - SCREEN_WIDTH * 0.75);
    static int y = (int)(SCREEN_HEIGHT - SCREEN_HEIGHT * 0.75);
    static int width = (int) (SCREEN_WIDTH / 1.5);
    static int height = (int) (SCREEN_HEIGHT / 1.5);
    public static ArrayList<QuestPic> pics = new ArrayList<>();
    public static Quest renderingQuest;

    public static void drawJournal()
    {

            Color oldColor = Renderer.g.getColor();
            pics=new ArrayList<>();
            //Рисуем задник
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.fillRect(x,y,width,height);

            if(renderingQuest==null) {
                //Отрисовывание главной страницы
                Renderer.g.setColor(Color.BLACK);
                Renderer.g.drawString("Знакомства : ", x + 50, y + 50);

                drawMomsQuests();
                drawButchersQuests();
                drawHackerQuest();
                drawDrugstoreQuest();
                drawCopsQuests();
            }else {
                //Если рендеринг квест  не  нуль, то мы отрисовываем не общее окно, а этот конкретный квест.
                Renderer.g.setColor(Color.BLACK);
                Renderer.g.drawString(
                        renderingQuest.owner + " просит : "+renderingQuest.name,x+50,y+50 );
                for (int i = 0; i < renderingQuest.description.split("\\.").length; i++) {
                    Renderer.g.drawString(renderingQuest.description.split("\\.")[i],x+150, y+100 + i*20);
                }

                Renderer.g.drawString("Пункты:",x+50,y+150);

                for(int i = 0 ; i<renderingQuest.tasks.size();i++)
                {
                    Renderer.g.drawString(
                            renderingQuest.tasks.get(i).name + (renderingQuest.tasks.get(i).isCompleted?"(сделано)":"(не сделано)"),
                            x+50,y+200+i*30);

                }

            }

            Renderer.g.setColor(oldColor);
    }

    private static void drawCopsQuests() {
        Renderer.g.setColor(Color.BLACK);
        if (Player.get().copQuests.size() > 0) {

            Renderer.g.fillRect(x + 450, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("Участковый : ", x + 450, y + 125);

            for (int i = 0; i < Player.get().copQuests.size(); i++) {
                QuestPic pic = new QuestPic(x + 450, y + 125 + (i + 1) * 30, Player.get().copQuests.get(i));
                Color bg = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.GREEN:Color.MAGENTA;
                Renderer.g.setColor(bg);
                Renderer.g.fillRect(pic.x, pic.y, pic.width, pic.height);
                Color font = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.BLACK:Color.GREEN;
                Renderer.g.setColor(font);
                String done = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?"(v)":"";
                Renderer.g.drawString(pic.quest.name+done, pic.x, pic.y + 10);
                pics.add(pic);
            }

        }else
        {
            Renderer.g.fillRect(x + 450, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("????? : ", x + 350, y + 125);
        }
    }

    private static void drawDrugstoreQuest() {
        Renderer.g.setColor(Color.BLACK);
        if (Player.get().drugstoreQuests.size() > 0) {

            Renderer.g.fillRect(x + 350, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("Аптекарша : ", x + 350, y + 125);

            for (int i = 0; i < Player.get().drugstoreQuests.size(); i++) {
                QuestPic pic = new QuestPic(x + 350, y + 125 + (i + 1) * 30, Player.get().drugstoreQuests.get(i));
                Color bg = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.GREEN:Color.MAGENTA;
                Renderer.g.setColor(bg);
                Renderer.g.fillRect(pic.x, pic.y, pic.width, pic.height);
                Color font = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.BLACK:Color.GREEN;
                Renderer.g.setColor(font);
                String done = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?"(v)":"";
                Renderer.g.drawString(pic.quest.name+done, pic.x, pic.y + 10);
                pics.add(pic);
            }

        }else
        {
            Renderer.g.fillRect(x + 350, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("????? : ", x + 350, y + 125);
        }
    }

    private static void drawHackerQuest() {
        Renderer.g.setColor(Color.BLACK);
        if (Player.get().hackerQuests.size() > 0) {

            Renderer.g.fillRect(x + 250, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("ХаКеР : ", x + 250, y + 125);

            for (int i = 0; i < Player.get().hackerQuests.size(); i++) {
                QuestPic pic = new QuestPic(x + 250, y + 125 + (i + 1) * 30, Player.get().hackerQuests.get(i));
                Color bg = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.GREEN:Color.MAGENTA;
                Renderer.g.setColor(bg);
                Renderer.g.fillRect(pic.x, pic.y, pic.width, pic.height);
                Color font = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.BLACK:Color.GREEN;
                Renderer.g.setColor(font);
                String done = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?"(v)":"";
                Renderer.g.drawString(pic.quest.name+done, pic.x, pic.y + 10);
                pics.add(pic);
            }

        }else
        {
            Renderer.g.fillRect(x + 250, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("????? : ", x + 250, y + 125);
        }
    }

    private static void drawButchersQuests() {
        Renderer.g.setColor(Color.BLACK);
        if (Player.get().butcherQuests.size() > 0) {
            Renderer.g.setColor(Color.BLACK);
            Renderer.g.fillRect(x + 150, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("Мясник : ", x + 150, y + 125);

            for (int i = 0; i < Player.get().butcherQuests.size(); i++) {
                QuestPic pic = new QuestPic(x + 150, y + 125 + (i + 1) * 30, Player.get().butcherQuests.get(i));
                Color bg = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.GREEN:Color.MAGENTA;
                Renderer.g.setColor(bg);
                Renderer.g.fillRect(pic.x, pic.y, pic.width, pic.height);
                Color font = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.BLACK:Color.GREEN;
                Renderer.g.setColor(font);
                String done = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?"(v)":"";
                Renderer.g.drawString(pic.quest.name+done, pic.x, pic.y + 10);
                pics.add(pic);
            }

        }else
        {
            Renderer.g.fillRect(x + 150, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("????? : ", x + 150, y + 125);
        }
    }

    private static void drawMomsQuests() {
        if (Player.get().momQuests.size() > 0) {
            Renderer.g.fillRect(x + 50, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("Мама : ", x + 50, y + 125);

            for (int i = 0; i < Player.get().momQuests.size(); i++) {
                QuestPic pic = new QuestPic(x + 50, y + 125 + (i + 1) * 30, Player.get().momQuests.get(i));
                Color bg = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.GREEN:Color.MAGENTA;
                Renderer.g.setColor(bg);
                Renderer.g.fillRect(pic.x, pic.y, pic.width, pic.height);
                Color font = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.BLACK:Color.GREEN;
                Renderer.g.setColor(font);
                String done = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?"(v)":"";
                Renderer.g.drawString(pic.quest.name+done, pic.x, pic.y + 10);
                pics.add(pic);
            }

        }
    }

    public static class QuestPic extends Rectangle
    {
        public Quest quest;
        public QuestPic(int x,int y,Quest quest)
        {
            super(x,y,100,30);
            this.quest= quest;
        }
    }
}
