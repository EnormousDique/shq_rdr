package ru.muwa.shq.dialogues;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Dialogue {

    protected Message initialMessage;
    protected Message currentMessage;

    public Message getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(Message currentMessage) {
        this.currentMessage = currentMessage;
    }

    public void restore() {
        currentMessage = new Message(initialMessage.text,initialMessage.responds);
        System.out.println("MESSAGE RESTORED");
        System.out.println("curr msg" + currentMessage.text);
    }

    protected static class Message{
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
        public String getText(){return text;}
        public List<Respond> getResponds(){return responds;}
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
        public String getText(){return text;}
        public Message getMsg(){return msg;}
    }

    public void getDialogueFromTxt(){
        //TODO: реализовать метод.
    }
}
