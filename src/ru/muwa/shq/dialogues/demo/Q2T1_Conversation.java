package ru.muwa.shq.dialogues.demo;

import ru.muwa.shq.dialogues.Dialogue;

import java.util.ArrayList;

public class Q2T1_Conversation extends Dialogue {
    private static Q2T1_Conversation instance;
    public static Q2T1_Conversation getInstance()
    {
        if(instance == null) return new Q2T1_Conversation();
        else return instance;
    }
    private Q2T1_Conversation(){

        instance = this;

        ArrayList<Respond> responds = new ArrayList<>();
        ArrayList<Respond> responds2ndBraNCH = new ArrayList<>();

        responds2ndBraNCH.add(
                new Respond("Ну че там, заебал.",
                        new Message("Смотри, тут надо на один адрес сгонять. Забрать кое-что для меня. Короче, топаешь до северо-западного конца города, где желтые пятиэтажки, заходишь в правый дальний дом, на третьем этаже в электрощитке будет моя вещица. Мне брат оставил",
                                new Respond("ок."))));

        responds2ndBraNCH.add(
                new Respond("Слыш, чурка, отъебись.",
                        new Message("Эээ бляя, зачем так разговаривешь??",
                                new Respond("* уйти *"))));

        responds.add(
                new Respond("Привет! На рынок иду",
                new Message("Бля, братан, вот это ты внатуре вовремя. Смотри, я тут на рынке не последний человек, ты мне помоги, а я за тебя кому надо скажу. Бля буду.",
                new Respond("Что надо сделать?",
                new Message("Смотри, тут надо на один адрес сгонять. Забрать кое-что для меня. Короче, топаешь до северо-западного конца города, где желтые пятиэтажки, заходишь в правый дальний дом, на третьем этаже, в электрощитке будет моя вещица. Мне брат оставил",
                new Respond("ок."))))));

        responds.add(
                new Respond("Тебе какое дело?",
                new Message("Братан, да ты не газуй. Тема перспективная. Послушай лучше че я те скажу..",responds2ndBraNCH)));



        initialMessage = new Message("Саламу лейкум, братан. А ты куда идешь?",responds);
        currentMessage = new Message( initialMessage.getText(),initialMessage.getResponds());

    }
}
