package ru.muwa.shq.zones;


import ru.muwa.shq.minigames.MiniGameOld;

public class InteractiveEnterZone extends GameZone {

    public MiniGameOld getGame() {
        return game;
    }
    public EnterZone enterZone;

    public void setGame(MiniGameOld game) {
        this.game = game;
    }

    private MiniGameOld game ;
    public InteractiveEnterZone(MiniGameOld game, EnterZone z) {
        super(z.x, z.y, z.width, z.height);
        this.game = game;
        enterZone = z;



    }
}
