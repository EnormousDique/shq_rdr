package ru.muwa.shq.dialogues.hacker;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.HackerQuestReboot;
import ru.muwa.shq.quests.actions.QuestAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HackerMainDialogue extends Dialogue {
    public HackerMainDialogue()
    {
        try {
            this.init();
        } catch (IOException e) {
            System.out.println("бля почему-то не получилось создать диалог с хакером (основной)");
        }
    }
    @Override
    public void init() throws IOException {
        Message  m = new Message();
        initialMessage = m;
        m.setText("Привет, Шкипер! Чего хотел?");
        ArrayList<Respond> responds =new ArrayList<>();
        responds.add(new Respond("Мне бы маме помочь..."));
        responds.add(new Respond("Где взять еще детали для компа?"));
        m.setResponds(responds);

        //Ответ на "мне бы маме помочь.."
        m = new Message( "А чего у тебя с мамой такое?",new Respond("Ну там продукты, лекарство. Операция нужна..",new Message("Во дела. Ну смотри, есть у меня тут работенка одна. Как раз для тебя.",new Respond("А чего делат надо?",new Message("Слушай внимательно. Эти идиоты, барыги, которые наркоту толкают. Соображают они плохо, в общем. Делают тайники постоянно в одних и тех же местах:. Почтовые ящики, электрощитки, мусоропроводы, шахты лифта.",new Respond("ну, и..",new Message("Попробуй пошариться в этих местах. Мне нужно, чтобы ты собрал для меня 10 пакетов порошка. Справишься?.",new Respond("и все?",new Message("нет, еще кое-что. нужно, чтобы ты достал мне морковь с рынка. И мазь из аптеки <<Анальный князь>>. теперь все ",new Respond("понял", new QuestAction() {
            @Override
            public void performAction() {
                Player.get().hackerQuests.add(new HackerQuestReboot());
                initialMessage.getResponds().remove(0);
            }
        }))))))))));
        initialMessage.getResponds().get(0).setMsg(m);

        //Ответ на где взять еще  детали длякомпа
        m = new Message();
        m.setText("Вообще я могу попробовать сделать процессор. Нужно только узнать где достать кремень. Я пока подумаю.");
        m.setResponds(new ArrayList<>(List.of(new Respond("ok"))));
        initialMessage.getResponds().get(1).setMsg(m);


        currentMessage=initialMessage;
    }
}
