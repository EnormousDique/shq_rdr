package ru.muwa.shq.dialogues.hach;

import ru.muwa.shq.creatures.npc.questnpc.HackerMan;
import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.dialogues.hacker.HackerDialogueFirstMeeting;
import ru.muwa.shq.economics.money.Money_500;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.zakladki.KladBlue;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance5.HackersPlace;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.HachQuestStaff;
import ru.muwa.shq.quests.HackerQuestNewAcquaintances;
import ru.muwa.shq.quests.actions.QuestAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HachDialog extends Dialogue {

    public HachDialog(){
        Message m = new Message();
        ArrayList<Respond> responds = new ArrayList<>();
        m.setText("Саламу лейкум, братан. Подзаработать шустренько не хочешь");
        responds.add(new Respond());
        responds.get(0).setText("Тебе какое дело?");
        responds.add(new Respond());
        responds.get(1).setText("Привет! ДА хочу мне маме бы помочь");
        m.setResponds(responds);
        initialMessage = m;
        currentMessage = initialMessage;

        //вторая масага
        m = new Message();
        responds.get(1).setMsg(m);
        m.setText("Смотри, тут надо на один адрес сгонять. Забрать кое-что для меня. Короче, топаешь до северо-западного конца города, где желтые пятиэтажки, заходишь в правый дальний дом, на третьем этаже в электрощитке будет моя вещица. Мне брат оставил");
        m.setResponds(List.of(new Respond("ДА КОНЕЧНО", new QuestAction() {
            @Override
            public void performAction() {
                Player.get().hachQuests.add(new HachQuestStaff());
                Message m = new Message();
                m.setText("НУ ЧТО!!!! УЖЕ ПРИНЕС???");
                m.setResponds(List.of(new Respond("куда куда надо сгонять?",new Message("Топаешь до северо-западного конца города, где желтые пятиэтажки, заходишь в правый дальний дом, на третьем этаже в электрощитке будет моя вещица. Мне брат оставил",new Respond("закрыть")))));
                initialMessage = m;
                currentMessage = initialMessage;

            }
        })));
        //первая масага

        m= new Message();
        responds.get(0).setMsg(m);
        m.setText("Братишка ты не кипишуй ДА!.Я ж вижу ты братишка Чёткий , ровный ДА! .Денег заработаешь братишка.Причем хароших ДА!? Братишка.");
        m.setResponds(List.of(new Respond("Сорян)0)..Тупанул чот. что нужно делать", new Message("Смотри, тут надо на один адрес сгонять. Забрать кое-что для меня. Короче, топаешь до северо-западного конца города, где желтые пятиэтажки, заходишь в правый дальний дом, на третьем этаже в электрощитке будет моя вещица. Мне брат оставил",new Respond("ДА КОНЕЧНО", new QuestAction() {
            @Override
            public void performAction() {
                Player.get().hachQuests.add(new HachQuestStaff());
                update1();

            }
        })))));
        //продолжение


    }
    public boolean update1;
    public boolean update2;
    public void update1(){
        Message m = new Message();
        m.setText("НУ ЧТО!!!! УЖЕ ПРИНЕС???");
        m.setResponds(List.of(new Respond("куда куда надо сгонять?",new Message(" топаешь до северо-западного конца города, где желтые пятиэтажки, заходишь в правый дальний дом, на третьем этаже в электрощитке будет моя вещица. Мне брат оставил",new Respond("закрыть")))));
        initialMessage = m;
        currentMessage = initialMessage;
        update1 = true;
    }
public void update2(){
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
    responds.get(1).setAction(new QuestAction() {
        @Override
        public void performAction() {
            Item klad = null;
            for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) {
                if(Inventory.getInstance().getItems().get(i) instanceof KladBlue)
                    klad = Inventory.getInstance().getItems().get(i);
            }
            if(klad!= null) Inventory.getInstance().getItems().remove(klad);
        }
    });
    Message fin = new Message("да братишка это кайфарики крч. Мне один типчик помогает с этой теой постоянно. Он там понял хакер-шмакер вот это вот все я те отвечаю. Но мутный какой-то тип. Братан, ты лучше сам к нему на хату заскочи. Знаешь серую многоэтажку буквой П? В четвертом подъезде живет на 4-м  этаже.",new Respond("Ладно", new QuestAction() {
        @Override
        public void performAction() {
            //квест на знакомство с хакером
            Player.get().hachQuests.get(0).tasks.get(Player.get().hachQuests.get(0).tasks.size()-1).isCompleted=true;
            Player.get().hackerQuests.add(new HackerQuestNewAcquaintances());
            HackersPlace.hackerNPC = new HackerMan(200,200);

            HackersPlace.getInstance().getNPC().add(HackersPlace.hackerNPC);
            HackersPlace.hackerNPC.dialogue = new HackerDialogueFirstMeeting();

            HackersPlace.hackerNPC.dialogue = new HackerDialogueFirstMeeting();
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
    }));
    m.setResponds(List.of(new Respond("вот держи", new Message("АХУЕННО БРАТИШКА ЕЖЖЕ РАХМАТ БРАТ ВОТ ДРЕЖИ БАБКИ",new Respond("ахуенно а что это такое",fin)))));
    //первый ответ
    m = new Message();
    responds.get(0).setMsg(m);
    m.setText(" СТОЙ БРАТ ДА ВИНОВАТ БРАТ НЕ БЕЙ БРАТ я шакал позорный виноват я виноват");

    m.setResponds(List.of(new Respond("НУ смотри у меня давай бабки за беспокойство а вещицу я себе оставлю",new Message("да конечно Брат только не бей брат рахмат брат. ВОТ держи",new Respond("ЭЭЭ а что жто такое вообще",fin)))));
update2 = true;
}
    @Override
    public void init() throws IOException {

    }
}
