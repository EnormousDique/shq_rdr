package ru.muwa.shq.levels.demo.demoLevel0.buildings.building6;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.quest.Pacanynok;
import ru.muwa.shq.levels.Level;

import ru.muwa.shq.levels.demo.indoors.WhiteBlueTallBuildingFloor4;

import ru.muwa.shq.objects.apartments.L1B6P1F4_KidApartmentsInteriors;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.InteractionZone;

import java.io.IOException;
import java.util.stream.Collectors;

public class L1B6P1F4_KidApartments extends Level {

    private static L1B6P1F4_KidApartments instance;
    public static L1B6P1F4_KidApartments getInstance() throws IOException {
        if(instance == null) return new L1B6P1F4_KidApartments(); else return instance;
    }
    private L1B6P1F4_KidApartments() throws IOException {

        instance = this;

        zones.add(new EnterZone(701,8,80,50, WhiteBlueTallBuildingFloor4.getInstance(),500,700,false));

        objects.add(new L1B6P1F4_KidApartmentsInteriors(0,0));


        zones.add(new InteractionZone(700,200,100,100) {
            @Override
            public void use() {
                //TODO: Дать в руки вместо текущего айтема пацанёнка
                //Сперва проверяем еквипнуто ли что-то
                Item equippedItem  = null;
                try {
                    equippedItem = Inventory.getInstance().getItems().stream().filter(Item::isEquipped).collect(Collectors.toList()).get(0);
                }catch (Exception e)
                {
                    //Игнорируем NUllPointer на случай если ничего не еквипнуто.
                }
                //Если да, снимаем эквип
                if(equippedItem!=null) equippedItem.setEquipped(false);
                //После, добавляем вешь в интвентарь
                Item pacan = new Pacanynok();
                Inventory.getInstance().addItem(pacan);
                //И ставим  в еквип
                pacan.setEquipped(true);
                //TODO: Важно! объект пацана не должен давать возможность снять себя с еквипа.
                //TODO: Добавить чек-поинт / респавн зону.
                // В случае, если игрок держит в руках пацана и приключение в активной фазе, игрок ресается тут, а не дома.

            }
        });
    }

}
