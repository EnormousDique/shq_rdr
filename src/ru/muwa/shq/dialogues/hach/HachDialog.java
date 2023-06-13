package ru.muwa.shq.dialogues.hach;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.items.zakladki.KladBlue;
import ru.muwa.shq.items.zakladki.KladRed;
import ru.muwa.shq.items.zakladki.KladYellow;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.HachQuestStaff;
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
                currentMessage = initialMessage;
                m.setResponds(List.of(new Respond("куда куда надо сгонять?",new Message("Топаешь до северо-западного конца города, где желтые пятиэтажки, заходишь в правый дальний дом, на третьем этаже в электрощитке будет моя вещица. Мне брат оставил",new Respond("закрыть")))));
                initialMessage = m;

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
                Message m = new Message();
                m.setText("НУ ЧТО!!!! УЖЕ ПРИНЕС???");
                currentMessage = initialMessage;
                m.setResponds(List.of(new Respond("куда куда надо сгонять?",new Message(" топаешь до северо-западного конца города, где желтые пятиэтажки, заходишь в правый дальний дом, на третьем этаже в электрощитке будет моя вещица. Мне брат оставил",new Respond("закрыть")))));
                initialMessage = m;
            }
        })))));
        //продолжение



    }
    @Override
    public void init() throws IOException {

    }
}
