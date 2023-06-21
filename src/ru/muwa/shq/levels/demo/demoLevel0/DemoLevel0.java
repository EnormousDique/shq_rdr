package ru.muwa.shq.levels.demo.demoLevel0;
import ru.muwa.shq.creatures.npc.Hach;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.creatures.npc.questnpc.StarleyMahony;
import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.dialogues.DialogueManager;
import ru.muwa.shq.dialogues.demo.Conversation0;
import ru.muwa.shq.dialogues.demo.HackerCumputerDialog;
import ru.muwa.shq.dialogues.demo.Q3_PoliceConversation;
import ru.muwa.shq.dialogues.hach.HachDialogFirst;
import ru.muwa.shq.dialogues.hach.HachDialogFate;
import ru.muwa.shq.dialogues.uchastkovy.UchastkovyDialogFirst;
import ru.muwa.shq.economics.trading.Buyout;
import ru.muwa.shq.economics.trading.Trade;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.animations.Animator;
import ru.muwa.shq.engine.animations.cutscenes.Q2T1_Cutscene;
import ru.muwa.shq.items.guns.Obrez;
import ru.muwa.shq.items.quest.BlackCreditCard;
import ru.muwa.shq.items.quest.GreenCreditCard;
import ru.muwa.shq.items.quest.Package;
import ru.muwa.shq.items.zakladki.KladYellow;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building1.Entrance1.L1B1P1F3_5;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.police.PoliceHQ;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.post.PostOffice;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.drugstore.DrugStoreInteriors;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.grocery.GroceryInteriors;
import ru.muwa.shq.objects.containers.HubChest;
import ru.muwa.shq.engine.animations.cutscenes.Q3_PoliceCutscene;

import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.spawner.Spawner;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.consumables.*;
import ru.muwa.shq.items.bluntWeapons.BaseballBat;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.items.drugs.IceOlator;
import ru.muwa.shq.items.drugs.Lyrica;
import ru.muwa.shq.items.guns.Makarov;
import ru.muwa.shq.items.guns.ammo.MakarovAmmo;
import ru.muwa.shq.items.knifes.Kortique;
import ru.muwa.shq.items.quest.Processor;
import ru.muwa.shq.items.zakladki.KladBlue;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building1.Entrance1.L1B1P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building1.Entrance2.L1B1P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building1.Entrance3.L1B1P3F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building1.Entrance4.L1B1P4F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building10.entrance1.L1B10P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building10.entrance2.L1B10P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building11.entrance1.L1B11P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building11.entrance2.L1B11P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building11.entrance2.L1B11P7F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building11.entrance3.L1B11P3F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building11.entrance4.L1B11P4F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building11.entrance5.L1B11P5F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building11.entrance6.L1B11P6F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building11.entrance8.L1B11P8F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building11.entrance9.L1B11P9F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building12.entrance1.L1B12P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building13.entrance1.L1B13P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building2.entrance1.L1B2P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building2.entrance2.L1B2P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building2.entrance3.L1B2P3F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building2.entrance4.L1B2P4F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building3.entrance1.L1B3P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building3.entrance2.L1B3P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building3.entrance3.L1B3P3F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building3.entrance4.L1B3P4F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building4.entrance1.L1B4P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building4.entrance2.L1B4P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building4.entrance3.L1B4P3F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building4.entrance4.L1B4P4F1;
//import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.Entrance1.L1B5P10F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.Entrance1.L1B5P1F1;

import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance10.L1B5P10F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance11.L1B5P11F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance2.L1B5P22F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance3.L1B5P3F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance4.L1B5P4F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance5.L1B5P5F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance6.L1B5P6F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance7.L1B5P7F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance8.L1B5P8F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance9.L1B5P9F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.L1B6P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance2.L1B6P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building7.entrance1.L1B7P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building7.entrance2.L1B7P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building8.entrance1.L1B8P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building8.entrance2.L1B8P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building8.entrance3.L1B8P3F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building8.entrance4.L1B8P4F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.market.MarketInteriors;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.market.VegetablesVendor;
import ru.muwa.shq.levels.demo.indoors.WhiteBlueTallBuildingFloor1;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.minigames.Domofon;
import ru.muwa.shq.objects.buildings.newBuildings.*;
import ru.muwa.shq.objects.buildings.TEST.FatBuilding;
import ru.muwa.shq.objects.buildings.TEST.TallFatBuilding;
import ru.muwa.shq.objects.obstacles.crates.Crate0;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.*;
import ru.muwa.shq.quests.actions.QuestAction;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.*;

import java.io.IOException;
import java.util.ArrayList;

public class DemoLevel0 extends Level
{
    public static NPC hachNPC;
    private static DemoLevel0 instance;
    public static DemoLevel0 getInstance() throws IOException {
        if(instance == null) return new DemoLevel0(); else return instance;
    }

    private DemoLevel0() throws IOException
    {
        super();
        instance = this;
        this.isStreet = true;

        Player.get().momQuests.add(new MomQuestArriving());


        /** ЗАДНИК **/
        objects.add(new DemoLevel0_BG(0,0));



        objects.add(new Crate0(100,100));

        /** Дома **/

        /* Дом 1 */

        //Сам дом
        objects.add(new FatBuilding(1690,200));
        //Вход в подъезд : 1
        zones.add(new MiniGameZone(1900,725,70,70,new Domofon("228k1488",L1B1P1F1.getInstance(), 190,220)));
        //сКРИПТОВАя ЗОНА МЕНТОВ по квесту зхача
        zones.add(new ActionZone(1800, 700, 300, 300, new QuestAction() {
            @Override
            public void performAction() {
                for(int j =0;j< Inventory.getInstance().getItems().size();j++)
                {
                    Item i = Inventory.getInstance().getItems().get(j);
                    if(i instanceof KladBlue)
                    {
                        Animator.playCutscene(Q3_PoliceCutscene.getInstance());
                        DialogueManager.playDialogueOnDemand(Q3_PoliceConversation.getInstance());
                    }
                }
            }
        }));
        //Вход в подъезд : 2
        zones.add(new MiniGameZone(2255,725,70,70,new Domofon("228k1488",L1B1P2F1.getInstance(), 190,220)));
        //Вход в подъезд : 3
        zones.add(new MiniGameZone(2615,725,70,70,new Domofon("228k1488",L1B1P3F1.getInstance(), 190,220)));
        //Вход в подъезд : 4
        zones.add(new MiniGameZone(2970,725,70,70,new Domofon("228k1488",L1B1P4F1.getInstance(), 190,220)));

        /* Дом 2 */
        objects.add(new FatBuilding(1690,1405));
        //Вход в подъезд : 1
        zones.add(new MiniGameZone(1900,1920,70,70,new Domofon("228k1488",L1B2P1F1.getInstance(), 190,220)));
        //Вход в подъезд : 2
        zones.add(new MiniGameZone(2255,1920,70,70,new Domofon("228k1488",L1B2P2F1.getInstance(), 190,220)));
        //Вход в подъезд : 3
        zones.add(new MiniGameZone(2615,1920,70,70,new Domofon("228k1488",L1B2P3F1.getInstance(), 190,220)));
        //Вход в подъезд : 4
        zones.add(new MiniGameZone(2970,1920,70,70,new Domofon("228k1488",L1B2P4F1.getInstance(), 190,220)));

        /* Дом 3 */
        objects.add(new FatBuilding(3630,200));
        //Вход в подъезд : 1
        zones.add(new MiniGameZone(3840,725,70,70,new Domofon("228k1488",L1B3P1F1.getInstance(), 190,220)));
        //Вход в подъезд : 2
        zones.add(new MiniGameZone(4200,725,70,70,new Domofon("228k1488",L1B3P2F1.getInstance(), 190,220)));
        //Вход в подъезд : 3
        zones.add(new MiniGameZone(4560,725,70,70,new Domofon("228k1488",L1B3P3F1.getInstance(), 190,220)));
        //Вход в подъезд : 4
        zones.add(new MiniGameZone(4900,725,70,70,new Domofon("228k1488",L1B3P4F1.getInstance(), 190,220)));


        /* Дом 4 */
        objects.add(new FatBuilding(3630,1405));
        //Вход в подъезд : 1
        zones.add(new MiniGameZone(3840,1920,70,70,new Domofon("228k1488",L1B4P1F1.getInstance(), 190,220)));
         //Вход в подъезд : 2
        zones.add(new MiniGameZone(4200,1920,70,70,new Domofon("228k1488",L1B4P2F1.getInstance(), 190,220)));
         //Вход в подъезд : 3
        zones.add(new MiniGameZone(4560,1920,70,70,new Domofon("228k1488",L1B4P3F1.getInstance(), 190,220)));
        //Вход в подъезд : 4
        zones.add(new MiniGameZone(4900,1920,70,70,new Domofon("228k1488",L1B4P4F1.getInstance(), 190,220)));

        /* Дом 5 */
        objects.add(new LoongGrayBuildingFront(5490,214));// фронт дома
        objects.add(new LoongGrayBuildingSide(5490,894));// боковина среднего дома4
        objects.add(new LoongGrayBuildingSideRight(8272,894));// втарая боковина среднего дома
        //Вход в подъезд : 1
        zones.add(new MiniGameZone(5995 ,1226,70,70,new Domofon("228k1488",L1B5P1F1.getInstance(), 190,220)));
        //Вход в подъезд : 2
        zones.add(new MiniGameZone(5990 ,900,70,70,new Domofon("228k1488",L1B5P22F1.getInstance(), 190,220)));
        //Вход в подъезд : 3
        zones.add(new MiniGameZone(6243 ,900,70,70,new Domofon("228k1488",L1B5P3F1.getInstance(), 190,220)));
        //Вход в подъезд : 4
        zones.add(new MiniGameZone(6496 ,900,70,70,new Domofon("228k1488",L1B5P4F1.getInstance(), 190,220)));
        //Вход в подъезд : 5
        zones.add(new MiniGameZone(6715 ,900,70,70,new Domofon("228k1488",L1B5P5F1.getInstance(), 190,220)));
        //Вход в подъезд : 6
        zones.add(new MiniGameZone(6966 ,900,70,70,new Domofon("228k1488",L1B5P6F1.getInstance(), 190,220)));
        //Вход в подъезд : 7
        zones.add(new MiniGameZone(7253 ,900,70,70,new Domofon("228k1488", L1B5P7F1.getInstance(), 190,220)));
        //Вход в подъезд : 8
        zones.add(new MiniGameZone(7533 ,900,70,70,new Domofon("228k1488",L1B5P8F1.getInstance(), 190,220)));
        //Вход в подъезд : 9
        zones.add(new MiniGameZone(7823 ,900,70,70,new Domofon("228k1488",L1B5P9F1.getInstance(), 190,220)));
        //Вход в подъезд : 10
        zones.add(new MiniGameZone(8112 ,900,70,70,new Domofon("228k1488",L1B5P10F1.getInstance(), 190,220)));
       //Вход в подъезд : 11
        zones.add(new MiniGameZone(8190  ,1226,70,70,new Domofon("228k1488",L1B5P11F1.getInstance(), 190,220)));
        //Районный отдел милиции
        zones.add(new EnterZone(5490,1915,100,100, PoliceHQ.getInstance(),0,0,false));
        //Почта
        zones.add(new EnterZone(8400,1915,100,100, PostOffice.getInstance(),0,0,false));

        /* Дом 6 */
        objects.add(new TallFatBuilding(1730,2134));
        zones.add(new MiniGameZone(1800,2940,70,70,new Domofon("228k1488",L1B6P1F1.getInstance(), 690,445)));
        zones.add(new MiniGameZone(2215,2940,70,70,new Domofon("228k1488",L1B6P2F1.getInstance(), 690,445)));

        /* Дом 7 */
        objects.add(new TallFatBuilding(2640,2916));
        zones.add(new MiniGameZone(2717,3750,70,70,new Domofon("228k1488",L1B7P1F1.getInstance(), 690,445)));
        zones.add(new MiniGameZone(3128,3750,70,70,new Domofon("228k1488",L1B7P2F1.getInstance(), 690,445)));

        /* Дом 9 */
        //3700 3100
        objects.add(new FatBuilding(3700,3100));
        //Вход в подъезд : 1
        zones.add(new MiniGameZone(3910,3620,70,70,new Domofon("228k1488",L1B8P1F1.getInstance(), 190,220)));
        //Вход в подъезд : 2
        zones.add(new MiniGameZone(4270,3620,70,70,new Domofon("228k1488",L1B8P2F1.getInstance(), 190,220)));
        //Вход в подъезд : 3
        zones.add(new MiniGameZone(4630,3620,70,70,new Domofon("228k1488",L1B8P3F1.getInstance(), 190,220)));
        //Вход в подъезд : 4
        zones.add(new MiniGameZone(4990,3620,70,70,new Domofon("228k1488",L1B8P4F1.getInstance(), 190,220)));

        /* Дом 8 */
        //3700 2100
        objects.add(new FatBuilding(3700,2100));
        //Вход в подъезд : 1
        zones.add(new MiniGameZone(3910,2620,70,70,new Domofon("228k1488",L1B8P1F1.getInstance(), 190,220)));
        //Вход в подъезд : 2
        zones.add(new MiniGameZone(4270,2620,70,70,new Domofon("228k1488",L1B8P2F1.getInstance(), 190,220)));
        //Вход в подъезд : 3
        zones.add(new MiniGameZone(4630,2620,70,70,new Domofon("228k1488",L1B8P3F1.getInstance(), 190,220)));
        //Вход в подъезд : 4
        zones.add(new MiniGameZone(4990,2620,70,70,new Domofon("228k1488",L1B8P4F1.getInstance(), 190,220)));

        //ДОМ 10
        objects.add(new RichBuilding(5606,1779));
        zones.add(new MiniGameZone(5714  ,2900,70,70,new Domofon("228k1488",L1B10P1F1.getInstance(), 190,220)));
        zones.add(new MiniGameZone(6320  ,2900,70,70,new Domofon("228k1488",L1B10P2F1.getInstance(), 190,220)));


        //ДОМ 11
        //верх
        objects.add(new LoongRedBrickBuildingTop(7130,1905));
        zones.add(new MiniGameZone(7250  ,2777,70,70,new Domofon("228k1488",L1B11P1F1.getInstance(), 190,220)));
        zones.add(new MiniGameZone(7900  ,2777,70,70,new Domofon("228k1488",L1B11P2F1.getInstance(), 190,220)));

        //длинная частть
        objects.add(new LoongRedBrickBuildingSide(7995,2708));
        zones.add(new MiniGameZone(7920  ,3100,70,70,new Domofon("228k1488",L1B11P3F1.getInstance(), 190,220)));
        zones.add(new MiniGameZone(7920  ,4300,70,70,new Domofon("228k1488",L1B11P4F1.getInstance(), 190,220)));
        zones.add(new MiniGameZone(7920  ,5090,70,70,new Domofon("228k1488",L1B11P5F1.getInstance(), 190,220)));
        zones.add(new MiniGameZone(7920  ,5892,70,70,new Domofon("228k1488",L1B11P6F1.getInstance(), 190,220)));
        zones.add(new MiniGameZone(7920  ,7096,70,70,new Domofon("228k1488",L1B11P7F1.getInstance(), 190,220)));

        //Магазин продуктов
        // TODO: Перенести к нужному дому (магаз в падике жилого дома)
        zones.add(new EnterZone(7920, 3800,100,100, GroceryInteriors.getInstance(),400,400,false));
        //Аптека
        zones.add(new EnterZone(7920, 6400,100,100, DrugStoreInteriors.getInstance(),400,400,false));


        //низ
        objects.add(new LoongRedBrickBuildingDown(7137,7130));
        zones.add(new MiniGameZone(7250  ,8000,70,70,new Domofon("228k1488",L1B11P8F1.getInstance(), 190,220)));
        zones.add(new MiniGameZone(7900  ,8000,70,70,new Domofon("228k1488",L1B11P9F1.getInstance(), 190,220)));


        //ДОМ 12
        objects.add(new VeryTallBulding(5358,4199));
        zones.add(new MiniGameZone(5530  ,6100,70,70,new Domofon("228k1488",L1B12P1F1.getInstance(), 190,220)));

        //ДОМ 13
        objects.add(new VeryTallBulding(6153,5252));
        zones.add(new MiniGameZone(6322  ,7149,70,70,new Domofon("228k1488",L1B13P1F1.getInstance(), 190,220)));


        /** РЫНОК **/
        //Зона входа в  рынок
        zones.add(new EnterZone(2930,6600,200,200, MarketInteriors.getInstance(),100,100,false));
        //скриптовая зона хача
        zones.add(new ActionZone(2700, 6000, 1000, 1000, new QuestAction() {
            @Override
            public void performAction() {
                hachNPC = new Hach(Player.get().getX() + 350, Player.get().getY()-300);
                hachNPC.name = "Vasya";
                hachNPC.setHp(Double.MAX_VALUE );
                Engine.getCurrentLevel().getNPC().add(hachNPC);
                try {
                    L1B1P1F3_5.getInstance().getContainers().get(0).addItem(new KladBlue());
                    System.out.println("POLOZHIL");
                } catch (IOException e) {
                    System.out.println("ne mogu poluchit l1b1p1f3_5");
                }
                Animator.playCutscene(Q2T1_Cutscene.getInstance());
                hachNPC.dialogue = new HachDialogFirst();
                DialogueManager.playDialogueOnDemand(hachNPC.dialogue);

            }
        }));

        /** Палатки на рынке **/

        /* Брат биолог */
        objects.add(new ShopBiolog(3400,6600));
        zones.add(new BuyoutZone(3300, 6660, 300, 300, new Buyout() {
            @Override
            public void init() {
                //Тут скорее всего будет дополнительный код, запускаемый при создании зоны.
                // Предположительно, он будет влиять на коэффициенты стоимости и прочие ограничения продажи
                // например, на список товаров, который может купить скупщик.
                this.jlob = 4;
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


        zones.add(new TradeZone(3500, 6500, 300, 300, new Trade() {
                    @Override
                    public void setGoods() {
                        goods.add(new CellPhone());
                        goods.add(new HomemadeAnuses());
                        goods.add(new LeBottle());
                        goods.add(new EpicRing());
                        goods.add(new ChickFire());
                        goods.add(new CannedSoup());
                    }
                }));

            zones.add(new EnterZone(1800 ,3256, 100,70,WhiteBlueTallBuildingFloor1.getInstance(),522,809,false));
        zones.add(new MiniGameZone(500,500,70,70,new Domofon("111",HubHataIgoryana.getInstance(),300,300)));
            zones.add(new MiniGameZone(500,500,100,100,new Domofon("111",HubHataIgoryana.getInstance(),300,300)));


       // QuestUtility.startQuest1();
        Spawner.updateTimers();
        System.out.println("test 4");


    }
}


