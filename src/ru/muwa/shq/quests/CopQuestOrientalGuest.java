package ru.muwa.shq.quests;

import ru.muwa.shq.quests.conditions.TaskCondition;

import java.util.ArrayList;

public class CopQuestOrientalGuest extends Quest{
    public CopQuestOrientalGuest()
    {
        //TODO: НУЖО ЗАСПАВНИТЬ НПЦ В УКАЗАННЫХ ЗОНАХ ПРИ ДОБАВЛЕНИИ КВЕСТА ИГРОКУ.
        isTaken=true;
        owner="участковый";
        name="Гостреприимность";
        description="Участковый просит помочь разобаться с одним вопросом. В последнее время участились случаи педофилии. Иммигранты начали собираться  на детсих площадках и дрочить. Надо от этого избавиться";
        tasks = new ArrayList<>();
        addTask("зачистить первую площадку", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                return false;
                //TODO: добавить проверку
            }
        });
        addTask("зачистить вторую площадку", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                return false;
                //TODO: добавить проверку
            }
        });
        addTask("зачистить третью площадку", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                return false;
                //TODO: добавить проверку
            }
        });
    }
}
