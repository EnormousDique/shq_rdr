package ru.muwa.shq.quests.actions;

import ru.muwa.shq.dialogues.DialogueManager;
import ru.muwa.shq.dialogues.demo.Q2T1_Conversation;
import ru.muwa.shq.engine.animations.Animator;
import ru.muwa.shq.engine.animations.cutscenes.Q2T1_Cutscene;

/**
 * Заскриптованное действие, происходящее при завершении первой задачи второго квеста (дойти до рынка)
 * Действие должно запускать сценку хачика, подходящего к игроку и после этого открывать соответствующий диалог.
 */
public class Q2T1_Action extends QuestAction{
    @Override
    public void performAction() {
        Animator.playCutscene(Q2T1_Cutscene.getInstance());
        DialogueManager.playDialogueOnDemand(Q2T1_Conversation.getInstance());
    }

}
