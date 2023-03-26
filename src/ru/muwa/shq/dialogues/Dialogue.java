package ru.muwa.shq.dialogues;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Dialogue {

    protected Message initialMessage;

    protected class Message{
        String text;
        List<Respond> responds = new ArrayList<>();
        public Message(String text, Respond respond)
        {
            this.text = text;
            this.responds.add(respond);
        }
        public Message(String text, List<Respond> respond)
        {
            this.text = text;
            this.responds = List.copyOf(respond);
        }
    }
    protected class Respond{
        String text;
        Message msg;

        public Respond(String text, Message msg)
        {
            this.text = text;
            this.msg = msg;
        }
        public Respond(String text)
        {   //Конечная ветка диалога.
            this.text = text;
        }
    }

    public void getDialogueFromTxt(){
        //TODO: реализовать метод.
    }
}
