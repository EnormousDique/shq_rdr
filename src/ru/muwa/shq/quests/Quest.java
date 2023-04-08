package ru.muwa.shq.quests;

import ru.muwa.shq.zones.CompleteTaskZone;

import java.util.ArrayList;
import java.util.List;

public class Quest {
    boolean isTaken;
    public List<Task> tasks;
    int currentTaskIndex = 0;

    public Quest(){
        tasks = new ArrayList<>();

    }
    public void addTask(String name, CompleteTaskZone z){
        this.tasks.add(new Task(name,z));
    };
    public List<Task> getTasks(){return tasks;}
    public static class Task{
        public String name;
        public boolean isCompleted;
        public CompleteTaskZone completeTaskZone;
        public void setCompleted(boolean flag){isCompleted = flag;}

        public Task(String name, CompleteTaskZone z)
        {
            this.name = name;
            isCompleted = false;
            completeTaskZone = z;
        }
    }

}
