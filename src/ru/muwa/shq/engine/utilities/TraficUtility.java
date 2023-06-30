package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.guns.Bullet;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.car.Car;

public class TraficUtility {
    public static void work(){
        for(int i = 0; i< Engine.getCurrentLevel().getObjects().size(); i++) {
            GameObject o = Engine.getCurrentLevel().getObjects().get(i);
            if (o instanceof Car) moveCar((Car) o);
        }

    }
 private static void moveCar(Car coc){
        if(coc.getDirection().equals(GameObject.Direction.RIGHT)) {
            coc.setX(coc.getX() + coc.getSpeed());
        }
     if(coc.getDirection().equals(GameObject.Direction.LEFT)) {
         coc.setX(coc.getX() - coc.getSpeed());
     }
     if(coc.getDirection().equals(GameObject.Direction.UP)) {
         coc.setY(coc.getY() - coc.getSpeed());
     }
     if(coc.getDirection().equals(GameObject.Direction.DOWN)) {
         coc.setY(coc.getY() + coc.getSpeed());
     }
        if(coc.getX() > 10000 || coc.getY() > 10000 || coc.getX() < 0 || coc.getY() < 0){
            Engine.getCurrentLevel().getObjects().remove(coc);
        }
 }
}
