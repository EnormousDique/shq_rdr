package ru.muwa.shq.engine.utilities;

public class ActionWindowUtility {
    static private ActionWindowUtility instance;
    private ActionWindowUtility(){instance = this;}
    public static ActionWindowUtility getInstance(){if(instance==null) return new ActionWindowUtility(); else return instance;}

    public void work (){

    }
}
