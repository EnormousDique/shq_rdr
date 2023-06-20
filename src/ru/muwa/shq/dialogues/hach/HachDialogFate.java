package ru.muwa.shq.dialogues.hach;

import ru.muwa.shq.creatures.npc.Hach;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.creatures.npc.questnpc.HackerMan;
import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.dialogues.hacker.HackerDialogueFirstMeeting;
import ru.muwa.shq.economics.money.Money_500;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance5.L1B5P5F1;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.ComputerQuest;
import ru.muwa.shq.quests.HachQuestStaff;
import ru.muwa.shq.quests.HackerQuestNewAcquaintances;
import ru.muwa.shq.quests.actions.QuestAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HachDialogFate extends Dialogue {
    public HachDialogFate(){
        Message m = new Message();
        ArrayList<Respond> responds = new ArrayList<>();
        m.setText("ООО это ты . я уже тут заждался.Что так долго брат");
        responds.add(new Respond());
        responds.get(0).setText("УУУ ЧУРКА ЕБУЧАЯ Я ТЕБЯ СЕЙЧАС УБЬЮ НАХУЙ");
        responds.add(new Respond());
        responds.get(1).setText("Бля брат прости брат там мусора были брат но я убежал..");
        m.setResponds(responds);
        initialMessage = m;
        currentMessage = initialMessage;

        // второй ответ
        m = new Message();
        responds.get(1).setMsg(m);
        m.setText("да ничего давай то что нашел ");
        m.setResponds(List.of(new Respond("вот держи", new Message("АХУЕННО БРАТИШКА ЕЖЖЕ РАХМАТ БРАТ ВОТ ДРЕЖИ БАБКИ",new Respond("ахуенно а что это такое",new Message("да братишка это кайфарики крч хочешь и тебя научу",new Respond("да давай", new QuestAction() {
            @Override
            public void performAction() {
                //квест на знакомство с хакером
                Player.get().hackerQuests.add(new HackerQuestNewAcquaintances());
                NPC npc = new HackerMan(-200,-200);
                try {
                    L1B5P5F1.getInstance().getNPC().add(npc);
                    npc.dialogue = new HackerDialogueFirstMeeting();
                } catch (IOException e) {
                    System.out.println("cannot load hacker in 5th entrance on l1b5 b");
                }
                npc.dialogue = new HackerDialogueFirstMeeting();
                //бабки за квест
                for (int i = 0; i < 2; i++) {
                    Inventory.getInstance().addItem(new Money_500());
                }
                //загулшка на дальнейшее говрение
                Message m = new Message();
                m.setText("ЗдравствуйТЭ Брат,как ваши дела брат");
                initialMessage = m;
                m.setResponds(List.of(new Respond("Все хорошо чем занят",new Message("Да так Кешельме помаленьку ",new Respond("закрыть")))));
                currentMessage = initialMessage;


            }
        })))))));
        //первый ответ
        m = new Message();
        responds.get(0).setMsg(m);
        m.setText(" СТОЙ БРАТ ДА ВИНОВАТ БРАТ НЕ БЕЙ БРАТ я шакал позорный виноват я виноват");
        m.setResponds(List.of(new Respond("НУ смотри у меня давай бабки за беспокойство а вещицу я себе оставлю",new Message("да конечно Брат только не бей брат рахмат брат. ВОТ держи",new Respond("ЭЭЭ а что жто такое вообще",new Message("да братишка это кайфарики крч сейчас я тебя научу",new Respond("давай Кабанчиком", new QuestAction() {
            @Override
            public void performAction() {
                //квест на знакомство с хакером
                Player.get().hackerQuests.add(new HackerQuestNewAcquaintances());
                NPC npc = new HackerMan(-200,-200);
                npc.setHp(Double.MAX_VALUE);
                try {
                    L1B5P5F1.getInstance().getNPC().add(npc);
                    npc.dialogue = new HackerDialogueFirstMeeting();
                } catch (IOException e) {
                    System.out.println("cannot load hacker in 5th entrance on l1b5 b");
                }
                npc.dialogue = new HackerDialogueFirstMeeting();
                //payment
                for (int i = 0; i < 2; i++) {
                    Inventory.getInstance().addItem(new Money_500());
                }
                //temporary stub
                Message m = new Message();
                m.setText("ЗдравствуйТЭ Брат,как ваши дела брат");
                initialMessage = m;
                m.setResponds(List.of(new Respond("Все хорошо чем занят",new Message("Да так Кешельме помаленьку ",new Respond("закрыть")))));
                currentMessage = initialMessage;




            }
        })))))));

    }

    @Override
    public void init() throws IOException {

    }
}
