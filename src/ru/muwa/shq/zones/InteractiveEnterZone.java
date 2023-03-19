package ru.muwa.shq.zones;


import ru.muwa.shq.minigames.MiniGame;

public class InteractiveEnterZone extends GameZone {

    public MiniGame getGame() {
        return game;
    }
    public EnterZone enterZone;

    public void setGame(MiniGame game) {
        this.game = game;
    }

    private MiniGame game ;
    public InteractiveEnterZone(MiniGame game, EnterZone z) {
        super(z.x, z.y, z.width, z.height);
        this.game = game;
        enterZone = z;



    }
}
