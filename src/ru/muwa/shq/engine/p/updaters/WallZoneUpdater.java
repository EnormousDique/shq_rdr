package ru.muwa.shq.engine.p.updaters;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.Engine;

public class WallZoneUpdater {
    static private WallZoneUpdater instance;
    private WallZoneUpdater(){}
    public static WallZoneUpdater getInstance()
    {if (instance == null) return new WallZoneUpdater(); else return instance;}
    public void update(){
      for  ( NPC n:  Engine.getCurrentLevel ().getNPC ()){
          n.getLeftWallZone ().setBounds (n.getX ()-10,n.getY (),5,n.getHeight ());
          n.getRightWallZone  ().setBounds (n.getX ()+10+n.getWidth (),n.getY (),5,n.getHeight ());
      }



    }
}
