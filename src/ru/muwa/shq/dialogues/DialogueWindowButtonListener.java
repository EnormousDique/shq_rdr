package ru.muwa.shq.dialogues;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.GameZone;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

public class DialogueWindowButtonListener implements ActionListener {
    private static DialogueWindowButtonListener instance;
    public static DialogueWindowButtonListener getInstance()
    {
        if(instance == null) return new DialogueWindowButtonListener();
        else return instance;
    }
    private DialogueWindowButtonListener(){
    instance = this;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (GameZone z : Engine.getCurrentLevel().getZones())
        {
            if(z instanceof DialogueZone  && ((DialogueZone)z).isActive())
            {

                Dialogue dialogue = ((DialogueZone)z ).getDialog();
                Dialogue.Respond respond =null;
                for(Dialogue.Respond r : dialogue.getCurrentMessage().responds) {

                    if (r.text.equals(((JButton) e.getSource()).getText()))
                        respond = r;
                }
                if(respond.msg==null){ HUD.getInstance().getDialogueWindow().setVisible(false); ((DialogueZone)z).setActive(false); Engine.pause = false; dialogue.restore();}

                else ((DialogueZone)z).getDialog().setCurrentMessage(((respond.msg)));
            }
        }
    }
}
