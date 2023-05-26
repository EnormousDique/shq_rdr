package ru.muwa.shq.levels.demo.demoLevel0;
import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.dialogues.DialogueManager;
import ru.muwa.shq.dialogues.demo.Conversation0;
import ru.muwa.shq.dialogues.demo.Q2T1_Conversation;
import ru.muwa.shq.dialogues.demo.Q3_PoliceConversation;
import ru.muwa.shq.economics.trading.Buyout;
import ru.muwa.shq.economics.trading.Trade;
import ru.muwa.shq.engine.animations.Animator;
import ru.muwa.shq.engine.animations.cutscenes.Q2T1_Cutscene;
import ru.muwa.shq.engine.animations.cutscenes.Q3_PoliceCutscene;

import ru.muwa.shq.engine.spawner.Spawner;

import ru.muwa.shq.engine.utilities.EffectUtility;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.consumables.*;
import ru.muwa.shq.items.bluntWeapons.BaseballBat;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.items.drugs.IceOlator;
import ru.muwa.shq.items.drugs.Lyrica;
import ru.muwa.shq.items.guns.Makarov;
import ru.muwa.shq.items.guns.Obrez;
import ru.muwa.shq.items.guns.ammo.MakarovAmmo;
import ru.muwa.shq.items.knifes.Kortique;
import ru.muwa.shq.items.zakladki.KladBlue;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.market.MarketInteriors;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.market.VegetablesVendor;
import ru.muwa.shq.levels.demo.indoors.WhiteBlueTallBuildingFloor1;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;
import ru.muwa.shq.minigames.padiklock.PadikLock;
import ru.muwa.shq.objects.buildings.NewBuildings.LoongGrayBuildingFront;
import ru.muwa.shq.objects.buildings.NewBuildings.LoongGrayBuildingSide;
import ru.muwa.shq.objects.buildings.NewBuildings.ShopBiolog;
import ru.muwa.shq.objects.buildings.TEST.FatBuilding;
import ru.muwa.shq.objects.buildings.TEST.TallFatBuilding;
import ru.muwa.shq.objects.containers.TrashCan;
import ru.muwa.shq.objects.obstacles.crates.Crate0;
import ru.muwa.shq.objects.street.Car;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.QuestUtility;
import ru.muwa.shq.quests.actions.QuestAction;
import ru.muwa.shq.quests.conditions.C_HasPlayerFoundKlad;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.InteractiveEnterZone;
import ru.muwa.shq.zones.*;

import java.io.IOException;

public class DemoLevel0 extends Level
{
    private static DemoLevel0 instance;
    public static DemoLevel0 getInstance() throws IOException {
        if(instance == null) return new DemoLevel0(); else return instance;
    }

    private DemoLevel0() throws IOException
    {
        super();
        instance = this;
        this.isStreet = true;

        System.out.println("test 1");

        Inventory.getInstance().addItem(new MakarovAmmo());
        Inventory.getInstance().addItem(new BaseballBat());
        Inventory.getInstance().addItem(new Makarov());
        Inventory.getInstance().addItem(new KladBlue());
        Inventory.getInstance().addItem(new IceOlator());
        Inventory.getInstance().addItem(new Kortique());
        for(int i = 0 ; i<4;i++) Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Obrez());


        System.out.println("test 2");

        /** ЗАДНИК **/
        objects.add(new DemoLevel0_BG(0,0));


        objects.add(new Car(1350,1060));
        objects.add(new Crate0(100,100));

        /** Дома **/

        /* Дом 1 */

        //Сам дом
        objects.add(new FatBuilding(1690,280));
        //Вход в подъезд : 1
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(1900,800,70,70, FatBuildingFloor1.getInstance(), 190,220,false)));

        /* Дом 2 */
        objects.add(new FatBuilding(3630,280));

        /* Дом 3 */
        objects.add(new FatBuilding(1690,1100));

        /* Дом 4 */
        objects.add(new FatBuilding(3630,1100));

        /* Дом 5 */
        objects.add(new TallFatBuilding(1730,2134));

        /* Дом 6 */
        objects.add(new TallFatBuilding(2640,3045));

        /* Дом 7 */
        objects.add(new LoongGrayBuildingFront(5490,214));

        /* Дом 8 */
        objects.add(new LoongGrayBuildingSide(5490,894));// боковина среднего дома


        //2930 6600
        /** РЫНОК **/
        //Зона входа в  рынок
        zones.add(new EnterZone(2930,6600,200,200, MarketInteriors.getInstance(),100,100,false));

        /** Палатки на рынке **/

        /* Брат биолог */
        objects.add(new ShopBiolog(3400,6600));
        zones.add(new BuyoutZone(3300, 6660, 300, 300, new Buyout() {
            @Override
            public void init() {
                //Тут скорее всего будет дополнительный код, запускаемый при создании зоны.
                // Предположительно, он будет влиять на коэффициенты стоимости и прочие ограничения продажи
                // например, на список товаров, который может купить скупщик.
            }
        }));
        /* Овощная и фруктовая палатка */
        objects.add(new VegetablesVendor(2900, 6800));
        zones.add(new TradeZone(2900, 6800, 100, 200, new Trade() {
            @Override
            public void setGoods() {
                goods.add(new Potato());
                goods.add(new Carrot());
                goods.add(new Gurken());
            }
        }));
        zones.add(new TradeZone(3000, 6800, 100, 200, new Trade() {
            @Override
            public void setGoods() {
                goods.add(new Onion()  );
                goods.add(new Beetroot());
                goods.add(new Cabbage());
                goods.add(new Tomato());
            }
        }));



        zones.add(new ActionZone(1800, 1200, 200, 200, new QuestAction() {
            @Override
            public void performAction() {
                for(Item i : Inventory.getInstance().getItems())
                {
                    if(i instanceof KladBlue)
                    {
                        Animator.playCutscene(Q3_PoliceCutscene.getInstance());
                        DialogueManager.playDialogueOnDemand(Q3_PoliceConversation.getInstance());
                    }
                }
            }
        }));
        // как использовать актион зону для володички
            zones.add(new ActionZone(3000, 2800, 200, 200, new QuestAction() {
                @Override
                public void performAction() {
                    EffectUtility.getCurrentEffects().put(EffectUtility.Effects.STONED,100000l+System.currentTimeMillis());
                    System.out.println("я в говно");
                }
            }));



        zones.add(new EnterZone(520,1800,70,70,HubHataIgoryana.getInstance(),290,705,false));
        zones.add(new DialogueZone(Conversation0.getInstance(),400,400,100,100,false));
        zones.add(new TradeZone(3000, 1000, 300, 300, new Trade() {
            @Override
            public void setGoods() {
                goods.add(new MakarovAmmo());
                goods.add(new Water());
                goods.add(new EnergyDrink());
                goods.add(new Cigarettes());
                goods.add(new Lyrica());
            }
        }));


            zones.add(new EnterZone(1800 ,3256, 100,70,WhiteBlueTallBuildingFloor1.getInstance(),522,809,false));

        QuestUtility.startQuest1();
        Spawner.updateTimers();
        System.out.println("test 4");


    }
}
