package ru.muwa.shq.quests;

import ru.muwa.shq.quests.conditions.TaskCondition;
import ru.muwa.shq.zones.CompleteTaskZone;

import java.util.ArrayList;

public class CopQuestUnhappyFamilies extends Quest{
    public CopQuestUnhappyFamilies()
    {
        isTaken=true;
        owner="участковый";
        name="Гостреприимность";
        description="Участковый дал важное задание. По его информации 3 семьи из  списка неблагополучных получили выплаты по мат капиталу. Все эти семьи из нашего района. Нужно обойти квартиры, изять деньги и документы. Документы нужно отправить почтой \"своему человеку\". Деньги занести в участок ";
        tasks = new ArrayList<>();
        addTask("заглушка", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                return false;
            }
        });
    }
}
