package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.objects.containers.Corpse;
import ru.muwa.shq.player.Player;

import java.awt.*;
import java.util.Objects;

public class CorpseCleanerUtility {
    public static void work() {
       Rectangle corpseField = new Rectangle(Camera.getInstance().getX(),Camera.getInstance().getY(),GameScreen.SCREEN_WIDTH,GameScreen.SCREEN_HEIGHT);
        for (int i = 0; i < Engine.getCurrentLevel().getContainers().size(); i++) {
            Container body = Engine.getCurrentLevel().getContainers().get(i);
            if (body instanceof Corpse) {
                    Corpse corpse = (Corpse) Engine.getCurrentLevel().getContainers().get(i);

                if ( Engine.getCurrentLevel().getContainers().get(i).getItems().isEmpty() &! corpseField.intersects(Engine.getCurrentLevel().getContainers().get(i).getSolidBox()) ) {
                    Engine.getCurrentLevel().getContainers().remove(body);
                }
            }
        }
    }
}



