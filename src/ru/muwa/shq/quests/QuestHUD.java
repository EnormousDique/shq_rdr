package ru.muwa.shq.quests;


import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.player.Player;

import java.awt.*;
import java.util.ArrayList;

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;

public class QuestHUD {
    public static boolean opened;
    static int x = (int)(SCREEN_WIDTH - SCREEN_WIDTH * 0.9);
    static int y = (int)(SCREEN_HEIGHT - SCREEN_HEIGHT * 0.75);
    static int width = (int) (SCREEN_WIDTH / 1.3);
    static int height = (int) (SCREEN_HEIGHT / 1.5);
    public static ArrayList<QuestPic> pics = new ArrayList<>();
    public static ArrayList<BossPic> bosses = new ArrayList<>();
    public static Quest renderingQuest;
    public static ArrayList<Quest> renderingQuestsList;

    public static void drawJournal()
    {
            Color oldColor = Renderer.g.getColor();
            pics=new ArrayList<>();
            bosses=new ArrayList<>();
            //Рисуем задник
            Renderer.g.setColor(new Color(250,250,250,150));
            Renderer.g.fillRect(x,y,width,height);

            if(renderingQuest==null && renderingQuestsList == null) {
                //Отрисовывание главной страницы
                Renderer.g.setColor(Color.BLACK);
                Renderer.g.drawString("Знакомства : ", x + 50, y + 50);

                if(Player.get().momQuests.size()>0) bosses.add(new BossPic(x+50,y+100,Player.get().momQuests,"Мать"));
                if(Player.get().hachQuests.size()>0) bosses.add(new BossPic(x+150,y+100,Player.get().hachQuests,"Хачик"));
                if(Player.get().butcherQuests.size()>0) bosses.add(new BossPic(x+250,y+100,Player.get().butcherQuests,"Мясник"));
                if(Player.get().hackerQuests.size()>0) bosses.add(new BossPic(x+50,y+200,Player.get().hackerQuests,"Хакер"));
                if(Player.get().copQuests.size()>0) bosses.add(new BossPic(x+150,y+200,Player.get().copQuests,"Участковый"));
                if(Player.get().drugstoreQuests.size()>0) bosses.add(new BossPic(x+250,y+200,Player.get().drugstoreQuests,"Аптекарша"));

                for (int i = 0; i < bosses.size(); i++) {
                    BossPic p = bosses.get(i);
                    Renderer.g.setColor(Color.DARK_GRAY);Renderer.g.fillRect(p.x,p.y,p.width,p.height);
                    Renderer.g.setColor(Color.YELLOW);Renderer.g.drawString(p.name,p.x,p.y+30);

                }




            }else {
                if(renderingQuest !=null) {
                    //Если рендеринг квест  не  нуль, то мы отрисовываем не общее окно, а этот конкретный квест.
                    Renderer.g.setColor(Color.BLACK);
                    Renderer.g.drawString(
                            renderingQuest.owner + " просит : " + renderingQuest.name, x + 50, y + 50);
                    for (int i = 0; i < renderingQuest.description.split("\\.").length; i++) {
                        Renderer.g.drawString(renderingQuest.description.split("\\.")[i], x + 300, y + 50 + i * 20);
                    }

                    Renderer.g.drawString("Пункты:", x + 50, y + 150);

                    for (int i = 0; i < renderingQuest.tasks.size(); i++) {
                        Renderer.g.drawString(
                                renderingQuest.tasks.get(i).name + (renderingQuest.tasks.get(i).isCompleted ? "(сделано)" : "(не сделано)"),
                                x + 50, y + 200 + i * 30);

                    }
                }
                if(renderingQuestsList != null)
                {
                    for (int i = 0; i < renderingQuestsList.size(); i++) {
                        Quest q = renderingQuestsList.get(i);
                        QuestPic pic = new QuestPic(x + 490, y + 125 + (i + 1) * 30+5, q);
                        Color bg = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.GREEN:Color.MAGENTA;
                        Renderer.g.setColor(bg);
                        Renderer.g.fillRect(pic.x, pic.y, pic.width, pic.height);
                        Color font = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?Color.BLACK:Color.GREEN;
                        Renderer.g.setColor(font);
                        String done = pic.quest.tasks.get(pic.quest.tasks.size()-1).isCompleted?"(v)":"";
                        Renderer.g.drawString(pic.quest.name+done, pic.x, pic.y + 10);
                        pics.add(pic);
                        if(q.expirationTime  != 0)
                        {
                            Renderer.g.drawString("Осталось времени : " + TimeMachine.convertLongToString(q.expirationTime - TimeMachine.getCurrentTime()),pic.x,pic.y + 23);
                        }
                    }

                }
            }

            Renderer.g.setColor(oldColor);
    }
    private static void drawHachQuest() {
        Renderer.g.setColor(Color.BLACK);
        if (Player.get().hachQuests.size() > 0) {

            Renderer.g.fillRect(x + 610, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("ХАч : ", x + 610, y + 125);

            for (int i = 0; i < Player.get().hachQuests.size(); i++) {
                QuestPic pic = new QuestPic(x + 610, y + 125 + (i + 1) * 30, Player.get().hachQuests.get(i));
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
            Renderer.g.fillRect(x + 610, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("????? : ", x + 610, y + 125);
        }
    }
    private static void drawCopsQuests() {
        Renderer.g.setColor(Color.BLACK);
        if (Player.get().copQuests.size() > 0) {

            Renderer.g.fillRect(x + 490, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("Участковый : ", x + 490, y + 125);

            for (int i = 0; i < Player.get().copQuests.size(); i++) {
                QuestPic pic = new QuestPic(x + 490, y + 125 + (i + 1) * 30, Player.get().copQuests.get(i));
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
            Renderer.g.fillRect(x + 490, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("????? : ", x + 490, y + 125);
        }
    }


    private static void drawDrugstoreQuest() {
        Renderer.g.setColor(Color.BLACK);
        if (Player.get().drugstoreQuests.size() > 0) {

            Renderer.g.fillRect(x + 410, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("Аптекарша : ", x + 410, y + 125);

            for (int i = 0; i < Player.get().drugstoreQuests.size(); i++) {
                QuestPic pic = new QuestPic(x + 410, y + 125 + (i + 1) * 30, Player.get().drugstoreQuests.get(i));
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
            Renderer.g.fillRect(x + 410, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("????? : ", x + 410, y + 125);
        }
    }

    private static void drawHackerQuest() {
        Renderer.g.setColor(Color.BLACK);
        if (Player.get().hackerQuests.size() > 0) {

            Renderer.g.fillRect(x + 290, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("ХаКеР : ", x + 290, y + 125);

            for (int i = 0; i < Player.get().hackerQuests.size(); i++) {
                QuestPic pic = new QuestPic(x + 290, y + 125 + (i + 1) * 30, Player.get().hackerQuests.get(i));
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
            Renderer.g.fillRect(x + 290, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("????? : ", x + 290, y + 125);
        }
    }

    private static void drawButchersQuests() {
        Renderer.g.setColor(Color.BLACK);
        if (Player.get().butcherQuests.size() > 0) {
            Renderer.g.setColor(Color.BLACK);
            Renderer.g.fillRect(x + 170, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("Мясник : ", x + 170, y + 125);

            for (int i = 0; i < Player.get().butcherQuests.size(); i++) {
                QuestPic pic = new QuestPic(x + 170, y + 125 + (i + 1) * 30, Player.get().butcherQuests.get(i));
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
            Renderer.g.fillRect(x + 170, y + 100, 50, 50);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString("????? : ", x + 170, y + 125);
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
    public static class  BossPic extends Rectangle
    {
        public ArrayList<Quest> questList;
        String name;
        public BossPic(int x,  int y, ArrayList<Quest> questList, String name)
        {
            super(x,y,80,80);
            this.name=name;
            this.questList=questList;
        }
    }
}
