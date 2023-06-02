package ru.muwa.shq.dialogues.demo;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.Quest;
import ru.muwa.shq.quests.QuestUtility;
import ru.muwa.shq.quests.actions.QuestAction;

import java.util.ArrayList;

public class Q5T0_Conversation extends Dialogue {
    private static Q5T0_Conversation instance;
    public static Q5T0_Conversation getInstance()
    {
        if(instance == null) return new Q5T0_Conversation();
        else return instance;
    }

    @Override
    public void init() {

    }

    private Q5T0_Conversation(){

        instance = this;

        ArrayList<Respond> responds = new ArrayList<>();
        //ArrayList<Respond> responds2ndBraNCH = new ArrayList<>();



        responds.add(
                new Respond("Мне бы маме помочь.",
                        new Message("А чего у твоей матушки случилось?",
                                new Respond("Ну там, всякое... продуктов бы ей ну и там  вего.",
                                        new Message("Ну с продуктами у меня всё пучком ))). Я на этом рынке всем заправляю",
                                                new Respond("Мне нужны продукты для мамы. Хорошие.",
                                                        new Message("Так, смотри. Благотворительностью  я тут не занимаюсь. Ты или мне платишь  бабки. Или работаешь на меня. Понятно объясняю?",
                                                                new Respond("Что надо делать?", new QuestAction() {
                                                            @Override
                                                            public void performAction() {
                                                                System.out.println("проверочка-с");
                                                                boolean isQuest5AlreadyTaken =  false;
                                                            //    for(Quest q : Player.get().quests)
                                                       //             if(q.tasks.get(0).name.contains("пацанёнку"))
                                                      //                  isQuest5AlreadyTaken  = true;
                                                  //              if(!isQuest5AlreadyTaken) QuestUtility.startQuest5();
                                                            }
                                                        }))))))));

        responds.add(
                new Respond("Мясо хочу купить."));


        initialMessage = new Message("Привет, друг. Тебе чего?",responds);
        currentMessage = new Message( initialMessage.getText(),initialMessage.getResponds());

    }
}
