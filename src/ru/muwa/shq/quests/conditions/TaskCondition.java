package ru.muwa.shq.quests.conditions;

import ru.muwa.shq.quests.Quest;

public abstract class TaskCondition {

    public Quest.Task myTask;

    public abstract boolean checkCondition();


}
