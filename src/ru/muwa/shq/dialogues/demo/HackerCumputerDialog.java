package ru.muwa.shq.dialogues.demo;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.GameZone;

import java.util.ArrayList;
import java.util.LinkedList;

public class HackerCumputerDialog extends Dialogue {
    public HackerCumputerDialog(){
        super();
        Message initM = new Message();
        Message m = null;
        this.initialMessage = initM;
        currentMessage = new Message();

        //Начало диалога
        initM.setText("Привет!");
        ArrayList<Respond> respondSS = new ArrayList<>();
        respondSS.add(new Respond());
        respondSS.get(0).setText("Ответ 1.");
        respondSS.add(new Respond());
        respondSS.get(1).setText("Ответ 2.");
        respondSS.add(new Respond());
        respondSS.get(2).setText("Ответ 3.");
        initM.setResponds(respondSS);



        //ответ
                /*    responds = (ArrayList<Respond>) initM.getResponds();
                m = new Message();
                responds.get(0).setMsg(m);
                m.setText("Ветка ответа 3");
                responds = new ArrayList<>();
                responds.add(new Respond("пошел нахуй"));
                m.setResponds(responds);

                 */


        this.currentMessage = this.initialMessage;
    }

            @Override
            public void init() {
            }
}
