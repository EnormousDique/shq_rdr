package ru.muwa.shq.zones;
import ru.muwa.shq.levels.Level;

import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.security.PublicKey;


public class DialogueZone extends GameZone{
    private Level level;
    public DialogueZone dialogueZone;
    private boolean isAuto;




    public DialogueZone(int x, int y, int width, int height,boolean isAuto) {
        super(x,y,width,height);
        this.isAuto =isAuto;

    }



}
