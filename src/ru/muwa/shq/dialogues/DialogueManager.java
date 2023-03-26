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
    public static DialogueManager getInstance()
    {
        if(instance == null) return new DialogueManager();
        else return instance;
    }
    private DialogueManager(){
    instance = this;
    }
    public void work()
    {
        for(GameZone z : Engine.getCurrentLevel().getZones())
        {
            if(z instanceof DialogueZone && ((DialogueZone)z).isActive())
            {

                //Инициализация
                Engine.pause=true;
                Dialogue dialogue = ((DialogueZone)z).getDialog();
                JPanel window = HUD.getInstance().getDialogueWindow();
                ArrayList<JButton> responseButtons = new ArrayList<>();

                //Чистим диалоговое окно
                Arrays.stream(window.getComponents()).forEach(window::remove);

                //Выводим сообщение
                JLabel messageLabel = new JLabel(dialogue.getCurrentMessage().getText());
                System.out.println("new msg label text wiill bee : " + messageLabel.getText());
                System.out.println("current message " + dialogue.getCurrentMessage().getText());
                window.add(messageLabel);

                //Выводим ответы
                for(int i = 0; i < dialogue.getCurrentMessage().responds.size(); i++)
                {
                    responseButtons.add(new JButton(dialogue.getCurrentMessage().responds.get(i).text));
                   // System.out.println("otvety : " + dialogue.getCurrentMessage().responds.get(i).text);

                }
                for(JButton b : responseButtons){ window.add(b); b.addActionListener(DialogueWindowButtonListener.getInstance());}

                window.updateUI();

            }
        }
    }
}
