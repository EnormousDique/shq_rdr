package ru.muwa.shq.engine.animations.cutscenes;

import java.util.ArrayList;

public class Cutscene0 extends Cutscene {
    private static Cutscene0 instance;
    private Cutscene0(){

        instance = this;
        movements = new ArrayList<>();
        //TODO:
        // нужно написать утилиту, которая будет подбирать крича по значению поля name
        // чтобы мы могли просто передавать его как аргумент метода,
        // и таким образом выцеплять нужного крича для сцены.

        movements.add(new Movement("Hachique",300,300));
        movements.add(new Movement("Hachique",400,400));
        movements.add(new Movement("Vasya",480,400));
    }
    public static Cutscene0 getInstance(){
        if(instance ==null)return new Cutscene0();
        else return instance;
    }


}