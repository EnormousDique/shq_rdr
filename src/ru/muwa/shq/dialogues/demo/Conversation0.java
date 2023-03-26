package ru.muwa.shq.dialogues.demo;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.engine.ai.AI;
import ru.muwa.shq.objects.containers.Container;

import java.util.ArrayList;
import java.util.Objects;

public class Conversation0 extends Dialogue {
    private static Conversation0 instance;
    public static Conversation0 getInstance()
    {
        if(instance == null) return new Conversation0();
        else return instance;
    }
    private Conversation0(){

        instance = this;
        ArrayList<Respond> responds = new ArrayList<>();

        responds.add
                (new Respond("Пошёл нахуй",
                        new Message("Ты че охуел, нормально же общались?",
                                new Respond("Пошёл нахуй, я сказал",
                                        new Message("ууу сука бля..",
                                                new Respond("..."))))));

        responds.add(
                new Respond("Нормально",
                        new Message("Ну вот и збс",
                                new Respond("Пока."))));

        initialMessage = new Message("Привет, как дела?",responds);
        currentMessage = new Message( initialMessage.getText(),initialMessage.getResponds());

    }


}
