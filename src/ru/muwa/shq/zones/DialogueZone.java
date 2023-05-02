package ru.muwa.shq.zones;
import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.levels.Level;

import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.security.PublicKey;


public class DialogueZone extends GameZone{
    private Level level;
    public DialogueZone dialogueZone;
    public boolean isAuto() {
        return isAuto;
    }
    public void setAuto(boolean auto) {
        isAuto = auto;
    }
    private boolean isAuto, isActive;
    private Dialogue dialog;
    public Dialogue getDialog() {
        return dialog;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public DialogueZone(Dialogue dialog,int x, int y, int width, int height,boolean isAuto) {
        super(x,y,width,height);
        this.isAuto =isAuto;
        this.dialog = dialog;
    }
}
