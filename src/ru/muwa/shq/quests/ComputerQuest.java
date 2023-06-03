package ru.muwa.shq.quests;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.quest.*;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.quests.conditions.TaskCondition;

import java.util.ArrayList;

public class ComputerQuest extends Quest {
    public ComputerQuest() {
        name = "''Соборка ПК''";
        owner = "Хакер";
        tasks = new ArrayList<>();
        tasks.add(new Task("найти Процессор", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) {

                    if (Inventory.getInstance().getItems().get(i) instanceof Processor) {
                        return true;
                    }
                }
                return false;
            }
        }));
        tasks.add(new Task("найти видеокарту", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) {
                    Item card = Inventory.getInstance().getItems().get(i);
                    if (card instanceof GraphicsCard) {
                        return true;
                    }
                }
                return false;
            }
        }));
        tasks.add(new Task("найти материнскую плату", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) {
                    Item Board = Inventory.getInstance().getItems().get(i);
                    if (Board instanceof MotherBoard) {
                        return true;
                    }
                }
                return false;
            }
        }));
        tasks.add(new Task("найти блок питания", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) {
                    Item Block = Inventory.getInstance().getItems().get(i);
                    if (Block instanceof PowerUnit) {
                        return true;
                    }
                }
                return false;
            }
        }));
        tasks.add(new Task("найти оперативку", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) {
                    Item pamyat = Inventory.getInstance().getItems().get(i);
                    if (pamyat instanceof Operativca) {
                        return true;
                    }
                }
                return false;
            }
        }));
        tasks.add(new Task("найти Жесткий ДИск", new TaskCondition() {
            @Override
            public boolean checkCondition() {
                for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) {
                    Item Hard = Inventory.getInstance().getItems().get(i);
                    if (Hard instanceof HardDrive) {
                        return true;
                    }
                }
                return false;
            }
        }));

    }
}
