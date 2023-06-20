package ru.muwa.shq.dialogues.uchastkovy;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.player.Player;

import java.io.IOException;
import java.util.ArrayList;

public class UchastkovyDialogFirst extends Dialogue{
    @Override
    public void init() throws IOException {

    }

    public UchastkovyDialogFirst(){
        ArrayList<Dialogue.Respond> responds = new ArrayList<>();
        Message m = new Message();
        m.setText("ПОа опа ча молодые Люди что тут происходит???");
            responds.add(new Dialogue.Respond());
            responds.get(0).setText("ЫМ ГЫМ ну тут Хрк это вообщем хрюк пук");
            responds.add(new Dialogue.Respond());
            responds.get(1).setText("Ничего(-1000)");
        m.setResponds(responds);
        currentMessage = initialMessage;
        initialMessage = m;

//взятка
            m= new Message();
            responds.get(1).setMsg(m);
            m.setText("опача понял вы это заходите познакомимся");
//невзятка
        m= new Message();
        responds.get(0).setMsg(m);
        m.setText("чо хреньк пеньк зайдешь ком не вечером и 1000 принесешь");
















    }
}
