package ru.muwa.shq.quests;

import ru.muwa.shq.quests.conditions.C_HasPlayerFoundKlad;
import ru.muwa.shq.quests.conditions.TaskCondition;

public class HachQuestStaff extends Quest {
    public HachQuestStaff(){
        addTask("Найти стаффчик", new C_HasPlayerFoundKlad());
        addTask("Поговорить с хачиком", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                //todo добавить код проверки вернулся ли игрок со стаффом к хачику
                return false;
            }
        });
    }
}
