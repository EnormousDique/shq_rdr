package ru.muwa.shq.quests.actions;

import ru.muwa.shq.dialogues.DialogueManager;
import ru.muwa.shq.engine.animations.Animator;
import ru.muwa.shq.engine.animations.cutscenes.Q2T1_Cutscene;
import ru.muwa.shq.levels.demoLevel0.DemoLevel0;

/**
 * Заскриптованное действие, происходящее при завершении первой задачи второго квеста (дойти до рынка)
 * Действие должно запускать сценку хачика, подходящего к игроку и после этого открывать соответствующий диалог.
 */
public class Q2T1_Action extends QuestAction{
    @Override
    public void performAction() {
        Animator.playCutscene(Q2T1_Cutscene.getInstance());
        DialogueManager.playDialogueOnDemand(DemoLevel0.hachNPC.dialogue);
    }

}
