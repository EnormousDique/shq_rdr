package ru.muwa.shq.engine.animations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Animation {
    protected  ArrayList<BufferedImage> sprites;
    public ArrayList<BufferedImage> getSprites()
    {
        return sprites;
    }
}
