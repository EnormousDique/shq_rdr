package ru.muwa.shq.quests;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.dialogues.DialogueManager;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.consumables.Carrot;
import ru.muwa.shq.items.consumables.Gurken;
import ru.muwa.shq.items.consumables.Lube;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance5.HackersPlace;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.L1B6P1F1;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.actions.QuestAction;
import ru.muwa.shq.quests.conditions.TaskCondition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HackerQuestReboot extends Quest{
    public HackerQuestReboot()
    {
        isTaken = true;
        owner="хакер";
        name="Переустановка ОС";
        description = "Хакер просит достать ему некоторые вещи. Далее по списку :. 10 пакетов \"скорости\", морковь, и смазку из аптеки \"Анальный князь\". Эти вещи нужно принести хакеру.";
        tasks = new ArrayList<>();
        tasks.add(new Task("Достать морковь.", new TaskCondition() {
            @Override
            public boolean checkCondition() {

                for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
                {
                    Item item = Inventory.getInstance().getItems().get(i);
                    if(item instanceof Carrot) return true;
                }
                return false;
            }
        }));
        tasks.add(new Task("Достать 10 пакетов порошка.", new TaskCondition() {
            @Override
            public boolean checkCondition() {

                for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
                {
                    Item item = Inventory.getInstance().getItems().get(i);
                    if(item instanceof Flour && item.amount >=10) return true;
                }
                return false;
            }
        }));
        tasks.add(new Task("Достать мазь \"Анальный князь\". ", new TaskCondition() {
            @Override
            public boolean checkCondition() {

                for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
                {
                    Item item = Inventory.getInstance().getItems().get(i);
                    if(item instanceof Lube) return true;
                }
                return false;
            }
        }));
        tasks.add(new Task("Отнести вещи хакеру.", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                if(tasks.get(0).isCompleted && tasks.get(1).isCompleted && tasks.get(2).isCompleted){
                    Dialogue.Respond newBranch = new Dialogue.Respond("Я достал что ты просил");
                    HackersPlace.hackerNPC.dialogue.getCurrentMessage().getResponds().add(newBranch);
                    newBranch.setAction(new QuestAction() {
                        @Override
                        public void performAction() {
                            boolean carrot = false;
                            boolean stuff = false;
                            boolean lube = false;

                            for(int i = 0; i < Inventory.getInstance().getItems().size(); i++) {
                                Item item = Inventory.getInstance().getItems().get(i);
                                if (item instanceof Carrot) carrot = true;


                                if (item instanceof Flour && item.amount>9) stuff = true;
                                if(item instanceof Lube) lube = true;
                            }

                            if(carrot&&lube&&stuff){
                                DialogueManager.dropDialogue();
                                tasks.get(tasks.size()-1).isCompleted=true;
                                try {
                                    Engine.switchLevel(L1B6P1F1.getInstance(),100,100);
                                } catch (IOException e) {
                                    System.out.println("ne mogu poluchit l1b6p1f1");
                                }
                                Renderer.addMessage("Хакер взял вещи");
                                Renderer.addMessage("и попросил удалиться.");

                            }else{
                                Renderer.addMessage("Нехватает вещей.");
                                Renderer.addMessage("Я что-то потерял?");

                                DialogueManager.playDialogueOnDemand(new Dialogue() {
                                    @Override
                                    public void init(){
                                        initialMessage = new Message();
                                        initialMessage.setText("Нихуя ты не достал, пиздабол. Принеси все сюда мне.");
                                        initialMessage.setResponds(List.of(new Respond("бля ок")));
                                        currentMessage=initialMessage;
                                    }
                                });
                            }

                        }
                    });
                }
                return false;
            }
        }));
    }
}
