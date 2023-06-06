package ru.muwa.shq.dialogues.mom;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.MomQuestFood;
import ru.muwa.shq.quests.Quest;
import ru.muwa.shq.quests.actions.QuestAction;

import java.io.IOException;
import java.util.List;

public class MomDialog extends Dialogue {
    public MomDialog(){
        Message m = new Message();
        m.setText("ай.. ой.. кто это? кто здесь?!?!?.......");
        m.setResponds(List.of(new Respond("мама ты чо !?.Это я Коля")));
        initialMessage = m;
        m = new Message();
        m.setText("Сынок ты такой молодец что пришел. У меня тут сплошное горя прям.С продуктами беда , на лекарства не хватает . Платежки душат , свет грозятся отключить. У врача была . Врач говорит месяц мне остался , без операции и лекарств.");
        initialMessage.getResponds().get(0).setMsg(m);
        m.setResponds(List.of(new Respond("Мама ты чего , Я тебе со всем помогу . И в беде не брошу", new QuestAction() {
            @Override
            public void performAction() {
                Player.get().momQuests.get(0).tasks.get(1).isCompleted = true;
                Player.get().momQuests.add(new MomQuestFood());
                Message m = new Message();
                m.setText("Привет сынок как дела ");
                initialMessage = m;
                m.setResponds(List.of(new Respond("Все хорошо мама тебе что-то нужно?",new Message("Спасибо сынок если что я скажу",new Respond("закрыть")))));
                currentMessage = initialMessage;
            }
        })));
            currentMessage = initialMessage;
    }
    @Override
    public void init() throws IOException {

    }
}
