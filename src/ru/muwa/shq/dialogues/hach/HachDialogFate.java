package ru.muwa.shq.dialogues.hach;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.ComputerQuest;
import ru.muwa.shq.quests.HachQuestStaff;
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
        //gthsdq jndtn
        m = new Message();
        responds.get(1).setMsg(m);
        m.setText("да ничего давай то что нашел ");
        m.setResponds(List.of(new Respond("вот держи", new QuestAction() {
            @Override
            public void performAction() {
                Player.get().hachQuests.get(0).tasks.get(0).isCompleted = true;
                Message m = new Message();
                m.setText("Спасибо тебе вот твои бабки");
                currentMessage = initialMessage;
                m.setResponds(List.of(new Respond("а что это было",new Message("да наркота брат хочешь и теюя научу покупать",new Respond("давай", new QuestAction() {
                    @Override
                    public void performAction() {
                        Player.get().hackerQuests.add(new ComputerQuest());
                    }
                })))));
                initialMessage = m;

            }
        })));
    }

    @Override
    public void init() throws IOException {

    }
}
