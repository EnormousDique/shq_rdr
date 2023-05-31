package ru.muwa.shq.dialogues.demo;

import ru.muwa.shq.dialogues.Dialogue;

import java.util.ArrayList;

public class Q3_PoliceConversation extends Dialogue {
    private static Q3_PoliceConversation instance;
    public static Q3_PoliceConversation getInstance()
    {
        if(instance == null) return new Q3_PoliceConversation();
        else return instance;
    }

    @Override
    public void init() {

    }

    private Q3_PoliceConversation(){

        instance = this;
        ArrayList<Respond> responds = new ArrayList<>();

        responds.add
                (new Respond("Пошёл нахуй",
                        new Message("Ты че охуел? Не понял что сейчас произошло?",
                                new Respond("Пошёл нахуй, я сказал",
                                        new Message("ууу сука бля..",
                                                new Respond("< * Съебаться * >"))))));

        responds.add(
                new Respond("< * Остановиться * >",
                        new Message("Я знаю что у тебя в кармане. Мы давно пасем этот падик. Тебе пизда.",
                                new Respond("< * Съебаться * >"))));

        initialMessage = new Message("Молодой человек! Стоять! Полиция!",responds);
        currentMessage = new Message( initialMessage.getText(),initialMessage.getResponds());

    }


}
