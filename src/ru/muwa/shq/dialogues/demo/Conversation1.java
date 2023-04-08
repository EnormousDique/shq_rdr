package ru.muwa.shq.dialogues.demo;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.engine.ai.AI;
import ru.muwa.shq.objects.containers.Container;

import java.util.ArrayList;
import java.util.Objects;

public class Conversation1 extends Dialogue {
    private static Conversation1 instance;
    public static Conversation1 getInstance()
    {
        if(instance == null) return new Conversation1();
        else return instance;
    }
    private Conversation1(){

        instance = this;
        ArrayList<Respond> responds = new ArrayList<>();
        ArrayList<Respond> responds2ndBraNCH = new ArrayList<>();
        responds2ndBraNCH.add(new Respond("Дай денюжку",new Message("да возьми в сундуке",new Respond("да спасибо"))));
        responds2ndBraNCH.add(new Respond("хорошо. я возьму деньги в сундуке?",new Message("Да возьми , там должны лежать твои старые вещи",new Respond("Отлично"))));



        responds.add
                (new Respond("Привет мама",
                        new Message("Ты голодный наверно? сходи на рынок купи себе покушать",responds2ndBraNCH)));

        responds.add(
                new Respond("Дарова МАТЬ",
                        new Message("Фу как грубо! Разве я тебя так воспитывала",
                                new Respond("ИЗВИНИ МАМА"))));




        initialMessage = new Message("Привет,Сынок как хорошо что ты вернулся со своего моря",responds);
        currentMessage = new Message( initialMessage.getText(),initialMessage.getResponds());

    }



}
