package ru.muwa.shq.entities.gameObjects;

public interface Usable {
    boolean isUsable = true;// Объект можно использовать (юзабелен).
    public default boolean isUsable(){return isUsable;}
}
