package ru.muwa.shq.quests;

import ru.muwa.shq.creatures.npc.Hach;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.creatures.npc.questnpc.Hachique;
import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.dialogues.hach.HachDialog;
import ru.muwa.shq.dialogues.hach.HachDialogFate;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.zakladki.KladBlack;
import ru.muwa.shq.items.zakladki.KladBlue;
import ru.muwa.shq.items.zakladki.KladRed;
import ru.muwa.shq.items.zakladki.KladYellow;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.market.MarketInteriors;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.actions.QuestAction;
import ru.muwa.shq.quests.conditions.C_HasPlayerFoundKlad;
import ru.muwa.shq.quests.conditions.TaskCondition;
import ru.muwa.shq.zones.CompleteTaskZone;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.GameZone;

import java.io.IOException;
import java.util.ArrayList;

public class HachQuestStaff extends Quest {
    public HachQuestStaff(){
        tasks = new ArrayList<>();
       tasks.add(new Task("Забрать то что попросил хач", new TaskCondition() {
           @Override
           public boolean checkCondition() {
               for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) {
                   Item item = Inventory.getInstance().getItems().get(i);
                   if(item instanceof KladBlue || item instanceof KladBlack || item instanceof KladRed || item instanceof KladYellow){

                       return true;
                   }
               }
               return false;
           }
       })); tasks.get(0).action = new QuestAction() {
            @Override
            public void performAction() {
                try {
                    tasks.add(new Task("вернуться к хачу и поговорить сним", new CompleteTaskZone(-200, -300, 150, 150, DemoLevel0.getInstance())));
                    NPC npc = new Hach(-200,-200);
                    MarketInteriors.getInstance().getNPC().add(npc);
                   // DemoLevel0.getInstance().getNPC().add(npc);
                    npc.setHp(5000);
                    npc.dialogue = new HachDialogFate();
                } catch (Exception e) {
                    System.out.println("неудалось загрузить зону для квеста хача чтобюы с ним поговорить");
                }
            }
        };
       tasks.get(0).isTriggeringAction = true;




    }
}
