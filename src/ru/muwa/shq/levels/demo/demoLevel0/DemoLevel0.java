package ru.muwa.shq.levels.demo.demoLevel0;
import ru.muwa.shq.creatures.npc.enemies.AimingGuy;
import ru.muwa.shq.creatures.npc.enemies.BadGuy0;
import ru.muwa.shq.creatures.npc.enemies.VelvetTank;
import ru.muwa.shq.creatures.npc.questnpc.Hachique;
import ru.muwa.shq.dialogues.demo.Conversation0;
import ru.muwa.shq.engine.animations.cutscenes.Cutscene0;
import ru.muwa.shq.engine.spawner.Spawner;
import ru.muwa.shq.items.BluntWeapons.BaseballBat;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.items.guns.Makarov;
import ru.muwa.shq.items.guns.ammo.MakarovAmmo;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;
import ru.muwa.shq.levels.demo.indoors.FatBuildingStraightFloor;
import ru.muwa.shq.levels.demo.indoors.Hub;
import ru.muwa.shq.minigames.padiklock.PadikLock;
import ru.muwa.shq.objects.buildings.TEST.FatBuilding;
import ru.muwa.shq.objects.buildings.TEST.TallFatBuilding;
import ru.muwa.shq.objects.containers.TrashCan;
import ru.muwa.shq.objects.obstacles.crates.Crate0;
import ru.muwa.shq.player.Inventory;
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
        this.isStreet = true;
        startPosX =641;
        startPosY = 1626;
        containers.add(new TrashCan(800,1900));
        containers.get(0).addItem(new Flour());
        containers.get(0).addItem(new Flour());
        containers.get(0).addItem(new Flour());
        containers.get(0).addItem(new Makarov());
        containers.get(0).addItem(new Makarov());
        Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Flour());

        Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Makarov());
        Inventory.getInstance().addItem(new Makarov());
        Inventory.getInstance().addItem(new MakarovAmmo());
        Inventory.getInstance().addItem(new MakarovAmmo());
        Inventory.getInstance().addItem(new MakarovAmmo());
        objects.add(new DemoLevel0_BG(0,0));
        Inventory.getInstance().addItem(new BaseballBat());


        objects.add(new Crate0(100,100));
        objects.add(new FatBuilding(735,825));
        objects.add(new FatBuilding(735,825));

        //objects.add(new TallFatBuilding(1000,2500));
        instance = this;
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(1280,1130,70,70, FatBuildingStraightFloor.getInstance(), 172,644,false)));
        zones.add(new InteractiveEnterZone( new PadikLock("1488К228"),new EnterZone(1280,1600,70,70, FatBuildingFloor1.getInstance(), FatBuildingFloor1.getInstance().getStartPosX(), FatBuildingFloor1.getInstance().getStartPosY(), false)));
        zones.add(new CutsceneZone(-200,-200,50,50, Cutscene0.getInstance()));
        // zones.add(new EnterZone(520,1765,70,70, FatBuildingFloor1.getInstance(), 327,220,false));
      //  zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(520,1665,70,70, Hub.getInstance(), 172,644,false)));
      //  zones.add(new InteractiveEnterZone( new PadikLock("1488К228"),new EnterZone(520,2115,70,70, Hub.getInstance(), 172,644,false)));
        zones.add(new EnterZone(520,1800,70,70,Hub.getInstance(),51,705,false));
        // zones.add(new EnterZone(0,0,70,70, DemoHub.getInstance(), 0,0,false));
        zones.add(new DialogueZone(Conversation0.getInstance(),400,400,100,100,false));



         // маного танков для проверки отдачи от холодног оружия
        /*
        npc.add(new VelvetTank(750,1800));

        npc.add(new VelvetTank(750,1800));
        npc.add(new VelvetTank(750,1800));
        npc.add(new VelvetTank(750,1800));
        npc.add(new VelvetTank(750,1800));
        npc.add(new BadGuy0(800,1800));
        npc.add(new BadGuy0(950,1800));
        npc.add(new BadGuy0(1100,1800));
        npc.add(new BadGuy0(1300,1800));
        npc.add(new AimingGuy(500,500));
        npc.add(new Hachique(-300,-200));
        npc.add(new Hachique(-350,-150,"Vasya"));

        npc.add(new VelvetTank(750,1800));



        npc.get(0).setRayCasterBorders(npc.get(0).getRayCaster().buildLines(objects));
        npc.get(1).setRayCasterBorders(npc.get(1).getRayCaster().buildLines(objects));
        npc.get(2).setRayCasterBorders(npc.get(2).getRayCaster().buildLines(objects));
        npc.get(3).setRayCasterBorders(npc.get(3).getRayCaster().buildLines(objects));
        npc.get(4).setRayCasterBorders(npc.get(4).getRayCaster().buildLines(objects));
        npc.get(5).setRayCasterBorders(npc.get(0).getRayCaster().buildLines(objects));

         */
        Spawner.updateTimers();


    }
}
