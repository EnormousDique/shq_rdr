package ru.muwa.shq.zones;

public abstract class InteractionZone extends GameZone{


    protected InteractionZone(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public abstract void use();
}
