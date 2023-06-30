package ru.muwa.shq.zones;


import ru.muwa.shq.objects.GameObject;

public class CarSpawnZone extends GameZone {
    public GameObject.Direction direction;
    public long lastSpawnTime;
    public CarSpawnZone (int x, int y, int width, int height,GameObject.Direction direction ) {
        super(x, y, width, height);
        this.direction=direction;
    }
}
