package ru.muwa.shq.dialogues.demo;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.engine.ai.AI;
import ru.muwa.shq.objects.containers.Container;

import java.util.ArrayList;
import java.util.Objects;

public class StartConversation extends Dialogue {
    private static StartConversation instance;
    public static StartConversation getInstance()
    {
        if(instance == null) return new StartConversation();
        else return instance;
    }

    @Override
    public void init() {

    }

    private StartConversation(){

        instance = this;
        ArrayList<Respond> responds = new ArrayList<>();

        responds.add
                (new Respond("Пошёл нахуй",
                        new Message("Ты че охуел, нормально же общались?",
                                new Respond("Пошёл нахуй, я сказал",
                                        new Message("ууу сука бля.. ну и ипиздуй играй",
                                                new Respond("мать чекай разраб пидор"))))));

        responds.add(
                new Respond("да я новичок",
                        new Message("Ну вот и збс",
                                new Respond("Пока."))));

        initialMessage = new Message("ЗДарова заебал,Добро пожаловать в игру , НАучить тебя что куда сувать?",responds);
        currentMessage = new Message( initialMessage.getText(),initialMessage.getResponds());

    }



}
