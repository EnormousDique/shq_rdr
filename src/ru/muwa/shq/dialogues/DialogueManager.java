package ru.muwa.shq.dialogues;

import ru.muwa.shq.dialogues.demo.Conversation0;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.GameZone;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static ru.muwa.shq.engine.g.GameScreen.*;
import static ru.muwa.shq.objects.GameObject.IMG_PATH;

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
    private static int DWx= (int) (SCREEN_WIDTH/3.8), DWy= (int) (SCREEN_HEIGHT/2.25), DWWidth=SCREEN_WIDTH/2,DWHeight=SCREEN_HEIGHT/2;
    public static ArrayList<ResponseButton> buttons = new ArrayList<>();

    public static boolean isBusy() {
        return busy;
    }

    /**Метод, проигрывающий диалог **/
    private static void play(Dialogue d) {
        currentDialogue = d;
        //Ставим паузу.
        Engine.pause = true;
        //busy=true
        busy = true;

        //Получаем текущее сообщение диалога.
        String currentMessage = d.getCurrentMessage().getText();

        //Получаем возможные ответы игрока на это сообщение
        //И добавляем их в список кнопок для ответа
        buttons = new ArrayList<>();
        for (int i = 0; i < d.getCurrentMessage().responds.size(); i++)
            buttons.add(new ResponseButton(d.currentMessage.responds.get(i)));

        //Сохраняем старый цвет графики.
     //   Color oldColor = Renderer.g.getColor();

        //Даем графике новый цвет (белый)
      //  Renderer.g.setColor(new Color(250, 250, 250, 150));

        Image dialogUI;
        try {
            dialogUI = ImageIO.read((new File(IMG_PATH + "dialogUI\\dialogui.png")));
            Renderer.g.drawImage(dialogUI,DWx, DWy, DWWidth, DWHeight,null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Заоплняем окно цветом
        //Renderer.g.fillRect(DWx, DWy, DWWidth, DWHeight);
        //Меняем цвет на черный для текста
      //  Renderer.g.setColor(Color.black);

        //Пишем текущее сообщение
        for (int i = 0; i < currentMessage.split("\\.").length; i++) {

            Renderer.g.drawString(currentMessage.split("\\.")[i], (int) (SCREEN_WIDTH / 2.42), (int) (SCREEN_HEIGHT / 1.85) + (i *15));

        }



        //Добавляем кнопки для ответа
        for (int i = 0; i < buttons.size(); i++) {

            buttons.get(i).x = (int) (SCREEN_WIDTH / 2.32);
            buttons.get(i).y = (int) (SCREEN_HEIGHT / 1.28) + (i *20);
            buttons.get(i).respond.text = buttons.get(i).respond.text.toLowerCase();
            buttons.get(i).width = buttons.get(i).respond.text.length() * 6;
            buttons.get(i).height = 20;
            Renderer.g.setColor(Color.GREEN);
            Renderer.g.fillRect(buttons.get(i).x, buttons.get(i).y, buttons.get(i).width, buttons.get(i).height);
            Renderer.g.setColor(Color.MAGENTA);
            Renderer.g.drawString(buttons.get(i).respond.text, buttons.get(i).x + 5, buttons.get(i).y + 10);
        }
       // Renderer.g.setColor(oldColor);

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
