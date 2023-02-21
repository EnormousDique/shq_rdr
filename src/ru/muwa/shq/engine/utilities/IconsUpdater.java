package ru.muwa.shq.engine.utilities;

public class IconsUpdater
{
    private static IconsUpdater  instance;
    private IconsUpdater(){instance = this;}
    public static IconsUpdater getInstance(){if(instance!=null) return instance; else return new IconsUpdater();}

}
