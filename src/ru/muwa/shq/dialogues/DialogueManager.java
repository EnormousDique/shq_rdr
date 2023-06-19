package ru.muwa.shq.dialogues;

import ru.muwa.shq.dialogues.demo.Conversation0;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.GameZone;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;

public class DialogueManager {
    private static DialogueManager instance;
    private static boolean busy;
    private static boolean playingDialogueOnDemand;
    public static boolean isPlayingDialogueOnDemand(){return playingDialogueOnDemand;}
    private static Dialogue currentDialogue;
    public static Dialogue getCurrentDialogue(){return currentDialogue;}

    private DialogueManager(){
    instance = this;
    }
    public static void work()
    {
        for(GameZone z : Engine.getCurrentLevel().getZones())
        {
            if(z instanceof DialogueZone && ((DialogueZone)z).isActive())
            {
                play(((DialogueZone) z).getDialog());
            }
        }
        if(playingDialogueOnDemand && currentDialogue != null)
        {
            play(currentDialogue);
        }
    }

    public static class ResponseButton extends Rectangle
    {
        public Dialogue.Respond respond;
        public ResponseButton(Dialogue.Respond r)
        {
            respond=r;
        }

    }
    private static int DWx=150, DWy=400, DWWidth=SCREEN_WIDTH-DWx*2,DWHeight=SCREEN_HEIGHT-DWy;
    public static ArrayList<ResponseButton> buttons = new ArrayList<>();

    public static boolean isBusy() {
        return busy;
    }

    /**Метод, проигрывающий диалог **/
    private static void play(Dialogue d)
    {
        currentDialogue = d;
        //Ставим паузу.
        Engine.pause=true;
        //busy=true
        busy=true;

        //Получаем текущее сообщение диалога.
        String currentMessage = d.getCurrentMessage().getText();

        //Получаем возможные ответы игрока на это сообщение
        //И добавляем их в список кнопок для ответа
        buttons=new ArrayList<>();
        for(int i = 0;i< d.getCurrentMessage().responds.size();i++)
            buttons.add(new ResponseButton(d.currentMessage.responds.get(i)));

        //Сохраняем старый цвет графики.
        Color oldColor = Renderer.g.getColor();

        //Даем графике новый цвет (белый)
        Renderer.g.setColor(new Color(250,250,250,150));

        //Заоплняем окно цветом
        Renderer.g.fillRect(DWx,DWy,DWWidth,DWHeight);
        //Меняем цвет на черный для текста
        Renderer.g.setColor(Color.black);

        //Пишем текущее сообщение
        for (int i = 0; i < currentMessage.split("\\.").length; i++) {
            Renderer.g.drawString(currentMessage.split("\\.")[i],DWx+50, DWy+50 + i*15);
        }


        //Добавляем кнопки для ответа
        for(int i = 0; i<buttons.size();i++)
        {
            buttons.get(i).x = DWx + (i+1)*100;
            buttons.get(i).y = DWy + DWHeight/2 + i%2*20;
            buttons.get(i).width = 100;
            buttons.get(i).height=20;
            Renderer.g.setColor(Color.GREEN);
            Renderer.g.fillRect(buttons.get(i).x,buttons.get(i).y,buttons.get(i).width,buttons.get(i).height);
            Renderer.g.setColor(Color.MAGENTA);
            Renderer.g.drawString(buttons.get(i).respond.text,buttons.get(i).x+5,buttons.get(i).y+10);
        }
        Renderer.g.setColor(oldColor);

    }
    public static void dropDialogue()
    {
        if(currentDialogue!=null && busy) {
            currentDialogue.setCurrentMessage(currentDialogue.initialMessage);
            playingDialogueOnDemand = false;
            currentDialogue = null;
            Engine.pause = false;
            for (int i = 0; i < Engine.getCurrentLevel().getZones().size(); i++)
                if (Engine.getCurrentLevel().getZones().get(i) instanceof DialogueZone)
                    if (((DialogueZone) Engine.getCurrentLevel().getZones().get(i)).isActive())
                        ((DialogueZone) Engine.getCurrentLevel().getZones().get(i)).setActive(false);
            busy = false;
        }
    }
    public static void playDialogueOnDemand(Dialogue d)
    {
        if(currentDialogue != d ) {
            playingDialogueOnDemand = true;
            currentDialogue = d;
        }
    }
}
