package ru.muwa.shq.dialogues;

import ru.muwa.shq.dialogues.demo.Conversation0;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.GameZone;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DialogueManager {
    private static DialogueManager instance;
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
                playDialogueFromZone((DialogueZone) z);
            }
        }
        if(playingDialogueOnDemand && currentDialogue != null)
        {
            playDialogue(currentDialogue);
        }
    }
    private static void playDialogueFromZone(DialogueZone z)
    {
        //Инициализация
        Engine.pause=true;
        Dialogue dialogue = ((DialogueZone)z).getDialog();
        JPanel window = HUD.getInstance().getDialogueWindow();
        ArrayList<JButton> responseButtons = new ArrayList<>();

        //Чистим диалоговое окно
        //window.setLayout(null);
        Arrays.stream(window.getComponents()).forEach(window::remove);

        //Выводим сообщение
        JLabel messageLabel = new JLabel(dialogue.getCurrentMessage().getText());
        System.out.println("new msg label text wiill bee : " + messageLabel.getText());
        System.out.println("current message " + dialogue.getCurrentMessage().getText());
        window.add(messageLabel);
        messageLabel.setBounds(50,50,300,200);

        //Выводим ответы
        int x = 50, y = 300;
        for(int i = 0; i < dialogue.getCurrentMessage().responds.size(); i++)
        {
            JButton b = new JButton(dialogue.getCurrentMessage().responds.get(i).text);
            responseButtons.add(b);
            window.add(b);
            b.addActionListener(DialogueWindowButtonListener.getInstance());
            b.setBounds(x,y,60,30);
            x += 65;
            if(i % 2 == 0)
            {
                x = 50;
                y+= 40;
            }
        }

        window.updateUI();
    }
    private static void playDialogue(Dialogue dialogue)
    {
        //Инициализация
        Engine.pause=true;
        JPanel window = HUD.getInstance().getDialogueWindow();
        ArrayList<JButton> responseButtons = new ArrayList<>();
        window.setVisible(true);

        //Чистим диалоговое окно
        Arrays.stream(window.getComponents()).forEach(window::remove);

        //Выводим сообщение
        JLabel messageLabel = new JLabel(dialogue.getCurrentMessage().getText());
        System.out.println("new msg label text wiill bee : " + messageLabel.getText());
        System.out.println("current message " + dialogue.getCurrentMessage().getText());
        window.add(messageLabel);
        messageLabel.setBounds(50,50,300,200);

        //Выводим ответы
        int x = 50, y = 300;
        for(int i = 0; i < dialogue.getCurrentMessage().responds.size(); i++)
        {
            JButton b = new JButton(dialogue.getCurrentMessage().responds.get(i).text);
            responseButtons.add(b);
            window.add(b);
            b.addActionListener(DialogueWindowButtonListener.getInstance());
            b.setBounds(x,y,60,30);
            x += 65;
            if(i % 2 == 0)
            {
                x = 50;
                y+= 40;
            }
        }

        window.updateUI();
    }
    public static void dropDialogue()
    {
        playingDialogueOnDemand = false;
        currentDialogue = null;
        HUD.getInstance().getDialogueWindow().setVisible(false);
    }
    public static void playDialogueOnDemand(Dialogue d)
    {
        if(currentDialogue != d ) {
            playingDialogueOnDemand = true;
            currentDialogue = d;
        }
    }
}
