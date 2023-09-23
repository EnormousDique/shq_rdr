package ru.muwa.shq.levels.demo.indoors;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demoLevel0.buildings.building6.L1B6P1F4_KidApartments;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoorNoNumberEnotherDoor;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;
public class WhiteBlueTallBuildingFloor4 extends Level
{
    private static WhiteBlueTallBuildingFloor4 instance;
    public static WhiteBlueTallBuildingFloor4 getInstance() throws IOException {
        if(instance == null) return new WhiteBlueTallBuildingFloor4(); else return instance;
    }
    private WhiteBlueTallBuildingFloor4() throws IOException {
        instance = this;
        startPosX = 527;
        startPosY = 762;

        objects.add(new WhiteBlueTallBuildingFLoorNoNumberEnotherDoor(10,10));
        zones.add(new EnterZone(699,755,80,50,WhiteBlueTallBuildingFloor3_5.getInstance(),699,200,false));
        zones.add(new EnterZone(701,8,80,50,WhiteBlueTallBuildingFloor4_5.getInstance(),699,585,false));

        //Вход в квартиру в рамках приключения "кража пацана"
        zones.add(new EnterZone(200,200,100,150, L1B6P1F4_KidApartments.getInstance(),0,0,false));
    }
}
