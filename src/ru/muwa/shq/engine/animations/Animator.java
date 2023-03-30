package ru.muwa.shq.engine.animations;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.animations.cutscenes.Cutscene;

public class Animator implements Runnable {
    private static Animator instance ;
    private Thread thread;
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
          //  System.out.println("performing movement " + m.toString());

            cutscene.playMovement(m);
        }
        Engine.cutscene = false;
    }
}
