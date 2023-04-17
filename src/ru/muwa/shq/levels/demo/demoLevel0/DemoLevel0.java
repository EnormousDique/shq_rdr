package ru.muwa.shq.levels.demo.demoLevel0;
import ru.muwa.shq.dialogues.demo.Conversation0;
import ru.muwa.shq.engine.animations.cutscenes.Q2T1_Cutscene;
import ru.muwa.shq.engine.spawner.Spawner;
import ru.muwa.shq.items.BluntWeapons.BaseballBat;
import ru.muwa.shq.items.guns.Makarov;
import ru.muwa.shq.items.guns.ammo.MakarovAmmo;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;
import ru.muwa.shq.levels.demo.indoors.Hub;
import ru.muwa.shq.minigames.padiklock.PadikLock;
import ru.muwa.shq.objects.buildings.TEST.FatBuilding;
import ru.muwa.shq.objects.buildings.TEST.TallFatBuilding;
import ru.muwa.shq.objects.containers.TrashCan;
import ru.muwa.shq.objects.obstacles.crates.Crate0;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.quests.QuestUtility;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.InteractiveEnterZone;
import ru.muwa.shq.zones.*;

import java.io.IOException;

public class DemoLevel0 extends Level
{
    private static DemoLevel0 instance;
    public static DemoLevel0 getInstance() throws IOException {
        if(instance == null) return new DemoLevel0(); else return instance;
    }

    private DemoLevel0() throws IOException
    {
        super();
        instance = this;
        this.isStreet = true;
        startPosX =641;
        startPosY = 1626;

        containers.add(new TrashCan(800,1900));
        containers.get(0).addItem(new Makarov());

        Inventory.getInstance().addItem(new MakarovAmmo());
        Inventory.getInstance().addItem(new BaseballBat());

        objects.add(new DemoLevel0_BG(0,0));

        objects.add(new Crate0(100,100));
        objects.add(new FatBuilding(1690,280));
        objects.add(new FatBuilding(3630,680));
        objects.add(new FatBuilding(1690,1500));
        objects.add(new FatBuilding(3630,1500));
        objects.add(new TallFatBuilding(1730,2450));
        objects.add(new TallFatBuilding(2640,3045));

        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(1900,1200,70,70, FatBuildingFloor1.getInstance(), 190,220,false)));

        zones.add(new EnterZone(520,1800,70,70,Hub.getInstance(),290,705,false));
        zones.add(new DialogueZone(Conversation0.getInstance(),400,400,100,100,false));


        QuestUtility.startQuest1();
        Spawner.updateTimers();

    }
}
