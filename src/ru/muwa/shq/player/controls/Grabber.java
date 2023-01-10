package ru.muwa.shq.player.controls;

public class Grabber
{
    private static Grabber instance;
    private Grabber(){instance = this;}
    public static Grabber getInstance(){if(instance!=null) return instance; else return new Grabber();}

    public void grab()
    {

    }
}
