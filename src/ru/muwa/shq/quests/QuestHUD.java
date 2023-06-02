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
    static int width = SCREEN_WIDTH / 2;
    static int height = SCREEN_HEIGHT / 2;
    public static ArrayList<QuestPic> pics;
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

                //Рисуем Шапку
                Renderer.g.setColor(Color.BLACK);
                Renderer.g.drawString("Знакомства : ", x + 50, y + 50);

                if (Player.get().momQuests.size() > 0) {
                    Renderer.g.fillRect(x + 50, y + 100, 50, 50);
                    Renderer.g.setColor(Color.WHITE);
                    Renderer.g.drawString("Мама : ", x + 50, y + 125);

                    for (int i = 0; i < Player.get().momQuests.size(); i++) {
                        QuestPic pic = new QuestPic(x + 50, y + 125 + (i + 1) * 30, Player.get().momQuests.get(i));
                        Renderer.g.setColor(Color.MAGENTA);
                        Renderer.g.fillRect(pic.x, pic.y, pic.width, pic.height);
                        Renderer.g.setColor(Color.GREEN);
                        Renderer.g.drawString(pic.quest.name, pic.x, pic.y + 10);
                        pics.add(pic);
                    }

                }
            }else {
                //Если рендеринг квест  не  нуль, то мы отрисовываем не общее окно, а этот конкретный квест.
                Renderer.g.setColor(Color.BLACK);
                Renderer.g.drawString(
                        renderingQuest.owner + " просит : "+renderingQuest.name,x+50,y+100 );
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
