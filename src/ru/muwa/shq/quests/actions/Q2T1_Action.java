package ru.muwa.shq.quests.actions;

import ru.muwa.shq.dialogues.DialogueManager;
import ru.muwa.shq.dialogues.demo.Q2T1_Conversation;
import ru.muwa.shq.engine.animations.Animator;
import ru.muwa.shq.engine.animations.cutscenes.Q2T1_Cutscene;
import ru.muwa.shq.items.zakladki.KladBlue;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor3_5;
import ru.muwa.shq.quests.QuestUtility;

import java.io.IOException;

/**
 * Заскриптованное действие, происходящее при завершении первой задачи второго квеста (дойти до рынка)
 * Действие должно запускать сценку хачика, подходящего к игроку и после этого открывать соответствующий диалог.
 */
public class Q2T1_Action extends QuestAction{
    @Override
    public void performAction() {
        Animator.playCutscene(Q2T1_Cutscene.getInstance());
        DialogueManager.playDialogueOnDemand(Q2T1_Conversation.getInstance());
        try {
            FatBuildingFloor3_5.getInstance().getContainers().get(0).addItem(new KladBlue());
        } catch (IOException e) {
            System.out.println("Не удалось получить уровень 3_5 этаж дома 1.");
        }
        QuestUtility.startQuest3();
    }

}