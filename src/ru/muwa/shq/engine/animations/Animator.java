package ru.muwa.shq.engine.animations;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.animations.cutscenes.Cutscene;
import ru.muwa.shq.player.Player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animator implements Runnable {
    private static Animator instance ;
    private Thread thread;
    private static boolean busy;
    static public boolean isBusy() {return busy;}
    private static ArrayList<Animation> animationQueue = new ArrayList<>();
    private Animator(){
        instance = this;
        thread = new Thread(instance);
        thread.start();
    }
    public static Animator getInstance() {
        if (instance == null) return new Animator();
        else return instance;
    }


    @Override
    public void run() {
        System.out.println("Animations engine initialized. Animator thread started.");

        double drawInterval = 1_000_000_000/60;
        double delta=0;
        long lastTime = System.nanoTime();
        long currTime;


        while(thread !=  null)
        {
            currTime = System.nanoTime();
            delta += (currTime - lastTime) / drawInterval;
            lastTime = currTime;

            if(delta >= 1)
            {
                System.out.printf("");// КОСТЫЛЬ. БЕЗ ЭТОЙ СТРОЧКИ НИЧЕГО НЕ РАБОТАЕТ.
                checkAnimationQueue();
            }
        }
        while (true)
        {
            System.out.println("animations thread slomalsa");
        }
    }

    public static void playCutscene(Cutscene cutscene)
    {
        Engine.cutscene = true;
        for (Cutscene.Movement m : cutscene.getMovements()) {
            cutscene.playMovement(m);
        }
        Engine.cutscene = false;
    }
    public static void playPlayerAnimation(Animation a)
    {
        if(!busy) {
            animationQueue.add(a);
            System.out.println("в очередь анимаций добавлено : " + a);
        }

    }
    public static void checkAnimationQueue()
    {
        for(int i = 0; i< animationQueue.size(); i++ )
        {
            System.out.println("В очереди есть анимация");
            for(int j = 0 ; j < animationQueue.get(i).getSprites().size() ; j++)
            {
                busy = true;
                System.out.println("меняем текстуру игрока");
                Player.get().setTexture(animationQueue.get(i).getSprites().get(j));
                System.out.println("поменяли текстуру игрока");
                try{
                    Thread.sleep(100);
                }catch (Exception e){}
            }

            busy = false;
        }
        animationQueue = new ArrayList<>();
    }

}
