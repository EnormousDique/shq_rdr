package ru.muwa.shq.dialogues.hacker;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.levels.demoLevel0.buildings.building5.entrance5.HackersPlace;
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
        responds.get(1).setMsg(m);
        m.setText("да ты!");
        m.setResponds(List.of(new Respond("я коля моряк кликуха шкипер,а ты ?",new Message("я?",new Respond("да ты!",new Message("я Антоха кликуха Хакер",new Respond("о круто мне как раз хач с рынка о тебе говорил",new Message("Ааа марик шоль так он через меня адреса берет, он тупой и компом пользоваться не умеет",new Respond("Я вообще на корабле в морской бой играл на компе",new Message("а ща у тебя есть комп какой-то",new Respond("сейчас у меня нет вообще ничего. Сперва бы маме помочь... ",new Message("Да ну, че ты как дебил блять? Надо тебе компьютер. ",new Respond("ну давай ",new Message("смотри, блок питация я могу тебе отдать свой старый. Остальное надо искать самому. Процессор там, мать, видюху..",new Respond("ну понял. будем собирать." ,new QuestAction() {
            @Override
            public void performAction() {
                Player.get().hackerQuests.add(new ComputerQuest());
                Player.get().hackerQuests.get(0).tasks.get(Player.get().hackerQuests.get(0).tasks.size()-1).isCompleted=true;
                HackersPlace.hackerNPC.dialogue = new HackerMainDialogue();

            }
        })))))))))))))));
    }
    @Override
    public void init() throws IOException {

    }
}
