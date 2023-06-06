package ru.muwa.shq.quests;

import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.quests.Quest;
import ru.muwa.shq.quests.conditions.TaskCondition;
import ru.muwa.shq.zones.CompleteTaskZone;

import java.io.IOException;
import java.util.ArrayList;

public class MomQuestArriving extends Quest {
    public MomQuestArriving(){
        isTaken = true;
        owner="mom";
        name="дорога до дому";
        description = "меня начали беспокоить мысли о маме.Пора вернуться домой и навестить матушку. Первая пятиэтажка на северо-западе города. Первый Подъезд пятый этаж.Код 228k1488.Я знаю мама меня ждет";
        tasks = new ArrayList<>();
        try {
            addTask("дойди до дома",new CompleteTaskZone(-1000,-1000,3000,3000, HubHataIgoryana.getInstance()));
        } catch (IOException e) {
            System.out.println("неполучилось получить уровень хаба");
        }
            addTask("Поговорить с мамой", new TaskCondition() {
    @Override
    public boolean checkCondition() {
        return false;
    }
});
    }
}
