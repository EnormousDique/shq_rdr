package ru.muwa.shq.dialogues.hacker;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.ComputerQuest;
import ru.muwa.shq.quests.actions.QuestAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HackerDialogueFirstMeeting extends Dialogue {
    public HackerDialogueFirstMeeting(){
        ArrayList<Respond> responds = new ArrayList<>();
        Message m = new Message();
        m.setText("ТЫ кто ??!! ");
        responds.add(new Respond());
        responds.get(0).setText("Я?");
        responds.add(new Respond());
        responds.get(1).setText("Привет! Я колян кликуха шкипер");
        m.setResponds(responds);
        initialMessage = m;
        currentMessage = initialMessage;

        //первая масага
        m= new Message();
        responds.get(0).setMsg(m);
        m.setText("да ты!");
        m.setResponds(List.of(new Respond("я коля моряк кликуха шкипер,а ты ?",new Message("я?",new Respond("да ты!",new Message("я Антоха кликуха Хакер",new Respond("о круто мне как раз хач с рынка о тебе говорил",new Message("Ааа марик шоль так он у меня Адреса тарит он тупой компом пользоваться не умеет",new Respond("Ну я на корабле в морской бой играл думаю дорулю чо да как",new Message("так а ты чего хотел то",new Respond("научи меня тоже адреса покупать а то чо я как ХАч тупой",new Message("а у тебя компьютер есть?",new Respond("неа нету ",new Message("тогда спрева тебе нужно комп собрать",new Respond("а как эта" ,new QuestAction() {
            @Override
            public void performAction() {
                Player.get().hackerQuests.add(new ComputerQuest());

            }
        })))))))))))))));
    }
    @Override
    public void init() throws IOException {

    }
}
