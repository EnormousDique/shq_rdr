package ru.muwa.shq.zones;


import ru.muwa.shq.minigames.MiniGame;

public class InteractiveEnterZone extends GameZone {

    public MiniGame getGame() {
        return game;
    }

    public void setGame(MiniGame game) {
        this.game = game;
    }

    private MiniGame game ;
    public InteractiveEnterZone(int x, int y, int width, int height, MiniGame game) {
        super(x, y, width, height);
        this.game = game;


    }
}
