package ru.muwa.shq.quests;
import ru.muwa.shq.quests.actions.Q2T1_Action;
import ru.muwa.shq.quests.actions.QuestAction;
import ru.muwa.shq.quests.conditions.TaskCondition;
import ru.muwa.shq.zones.CompleteTaskZone;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
public class Quest {
    public String name = "";
    public String description = "";
    public boolean isTaken;
    public List<Task> tasks;
    public String owner="";
    public long timeAcquired;
    public long expirationTime;
    public Quest(){
        tasks = new ArrayList<>();
    }
    public void addTask(String name, CompleteTaskZone z){
        this.tasks.add(new Task(name,z));
    }
    public void addTask(String name, TaskCondition с){
        this.tasks.add(new Task(name,с));
    }
    public void addTask(String name, CompleteTaskZone z, Q2T1_Action a) {
        this.tasks.add(new Task(name,z,a));
    }
    public static class Task{
        public boolean isVisible = true;
        public String name;
        public boolean isCompleted;
        public boolean isTriggeringAction;
        public CompleteTaskZone completeTaskZone;
        public QuestAction action;
        public TaskCondition condition;
        boolean hasCondition;
        boolean isExpired = false;
        public Task(String name, CompleteTaskZone z)
        {
            this.name = name;
            isCompleted = false;
            completeTaskZone = z;
        }
        public Task(String name, TaskCondition condition)
        {
            this.name = name;
            this.condition = condition;
            hasCondition = true;
        }

        public Task(String name, CompleteTaskZone z, QuestAction a) {
            this.name=name;
            isTriggeringAction = true;
            action = a;
            this.completeTaskZone =z;
        }
    }
}
