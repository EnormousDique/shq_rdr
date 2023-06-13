package ru.muwa.shq.engine.s;

import ru.muwa.shq.engine.g.Renderer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Sounder implements Runnable{
    private static Sounder instance;
    private static Clip clip;

    private Sounder()
    {
        thread.start();
    }
    public static Sounder getInstance() {
        if (instance == null) return new Sounder();
        return instance;
    }
    Thread thread = new Thread(this);
    @Override
    public void run() {
        System.out.println("sound drive start");
       // playSong("src\\ru\\muwa\\shq\\sounds\\songs\\song.wav");
        //clip.loop(Integer.MAX_VALUE);
         playSong("src\\ru\\muwa\\shq\\sounds\\songs\\ost(1).wav");
        //        clip.loop(Integer.MAX_VALUE);


    }
    public static void changeSong(String path){
        clip.stop();
        try {


            File f = new File(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            clip = AudioSystem.getClip();

            clip.open(audioIn);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-25.f);
            clip.start();



            System.out.println("song " + path + " played");
        }catch (Exception e)
        {
            System.out.println("Ошибка при проигрывании песни");
            System.out.println("text : "+e.getMessage());
        }

    }
    public static void playSong(String path){
        try {


            File f = new File(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            clip = AudioSystem.getClip();

            clip.open(audioIn);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-25.f);
            clip.start();



            System.out.println("song " + path + " changed");
        }catch (Exception e)
        {
            System.out.println("Ошибка при проигрывании песни");
            System.out.println("text : "+e.getMessage());
        }

    }
    public static void playSFX(String path){
        try {


            File f = new File(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();

            clip.open(audioIn);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-25.f);
            clip.start();



            System.out.println("sfx " + path + " played");
        }catch (Exception e)
        {
            System.out.println("Ошибка при проигрывании песни");
            System.out.println("text : "+e.getMessage());
        }

    }


}
