package ru.muwa.shq.zones;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.minigames.MiniGame;

public class MiniGameZone extends GameZone{
    public MiniGame miniGame;

    public MiniGameZone(int x, int y, int width, int height, MiniGame miniGame) {
        super(x, y, width, height);
        this.miniGame=miniGame;
    }


}
