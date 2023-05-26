package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.objects.containers.Corpse;
import ru.muwa.shq.player.Player;

import java.util.Objects;

public class CorpseCleanerUtility {
    public static void work() {

        for (int i = 0; i < Engine.getCurrentLevel().getContainers().size(); i++) {
            Container body = Engine.getCurrentLevel().getContainers().get(i);
            if (body instanceof Corpse) {
                    Corpse corpse = (Corpse) Engine.getCurrentLevel().getContainers().get(i);

                if (corpse.deathTime + 10000 < System.currentTimeMillis() & Engine.getCurrentLevel().getContainers().get(i).getItems().isEmpty()) {
                    Engine.getCurrentLevel().getContainers().remove(body);
                }
            }
        }
    }
}



