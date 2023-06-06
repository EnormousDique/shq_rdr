package ru.muwa.shq.dialogues.demo;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.HachQuestStaff;
import ru.muwa.shq.quests.actions.QuestAction;

import java.util.ArrayList;

public class Q2T1_Conversation extends Dialogue {
    private static Q2T1_Conversation instance;
    public static Q2T1_Conversation getInstance()
    {
        if(instance == null) return new Q2T1_Conversation();
        else return instance;
    }

    @Override
    public void init() {

    }

    private Q2T1_Conversation(){

        instance = this;

        ArrayList<Respond> responds = new ArrayList<>();
        ArrayList<Respond> responds2ndBraNCH = new ArrayList<>();

        responds2ndBraNCH.add(
                new Respond("Ну че там, заебал.",
                        new Message("Смотри, тут надо на один адрес сгонять. Забрать кое-что для меня. Короче, топаешь до северо-западного конца города, где желтые пятиэтажки, заходишь в правый дальний дом, на третьем этаже в электрощитке будет моя вещица. Мне брат оставил",
                                new Respond("ок.", new QuestAction() {
                                    @Override
                                    public void performAction() {
                                        Player.get().hachQuests.add(new HachQuestStaff());
                                    }
                                }))));

        responds.add(
                new Respond("Привет! На рынок иду",
                new Message("Бля, братан, вот это ты внатуре вовремя. Смотри, я тут на рынке не последний человек, ты мне помоги, а я за тебя кому надо скажу. Бля буду.",
                new Respond("Что надо сделать?",
                new Message("Смотри, тут надо на один адрес сгонять. Забрать кое-что для меня. Короче, топаешь до северо-западного конца города, где желтые пятиэтажки, заходишь в правый дальний дом, на третьем этаже, в электрощитке будет моя вещица. Мне брат оставил",
                new Respond("ок.", new QuestAction() {
                    @Override
                    public void performAction() {
                        Player.get().hachQuests.add(new HachQuestStaff());
                    }
                }))))));

        responds.add(
                new Respond("Тебе какое дело?",
                new Message("Братан, да ты не газуй. Тема перспективная. Послушай лучше че я те скажу..",responds2ndBraNCH)));



        initialMessage = new Message("Саламу лейкум, братан. А ты куда идешь?",responds);
        currentMessage = new Message( initialMessage.getText(),initialMessage.getResponds());

    }
}
