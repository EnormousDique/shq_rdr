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

import ru.muwa.shq.engine.g.Renderer;
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
import ru.muwa.shq.items.zakladki.KladBlack;
import ru.muwa.shq.items.zakladki.KladBlue;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building1.Entrance1.L1B1P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building1.Entrance2.L1B1P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building1.Entrance3.L1B1P3F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building1.Entrance4.L1B1P4F1;
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
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.Entrance1.L1B5P10F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.Entrance1.L1B5P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance11.L1B5P11F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance2.L1B5P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance3.L1B5P3F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance4.L1B5P4F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance5.L1B5P5F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance6.L1B5P6F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance7.L1B5P7F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance8.L1B5P8F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance9.L1B5P9F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building8.entrance1.L1B8P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building8.entrance2.L1B8P2F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building8.entrance3.L1B8P3F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building8.entrance4.L1B8P4F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.market.MarketInteriors;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.market.VegetablesVendor;
import ru.muwa.shq.levels.demo.indoors.WhiteBlueTallBuildingFloor1;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;
import ru.muwa.shq.minigames.Domofon;
import ru.muwa.shq.minigames.padiklock.PadikLock;
import ru.muwa.shq.objects.buildings.NewBuildings.LoongGrayBuildingFront;
import ru.muwa.shq.objects.buildings.NewBuildings.LoongGrayBuildingSide;
import ru.muwa.shq.objects.buildings.NewBuildings.ShopBiolog;
import ru.muwa.shq.objects.buildings.TEST.FatBuilding;
import ru.muwa.shq.objects.buildings.TEST.TallFatBuilding;
import ru.muwa.shq.objects.containers.TrashCan;
import ru.muwa.shq.objects.obstacles.crates.Crate0;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.*;
import ru.muwa.shq.quests.actions.QuestAction;
import ru.muwa.shq.quests.conditions.C_HasPlayerFoundKlad;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.InteractiveEnterZone;
import ru.muwa.shq.zones.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        Player.get().momQuests.add(new MomQuestFood());
        Player.get().butcherQuests.add(new ButcherQuestPacan());
        Player.get().hackerQuests.add(new HackerQuestReboot());
        Player.get().hackerQuests.add(new HackerQuestATM());
        Player.get().copQuests.add(new CopQuestOrientalGuest());
        Player.get().copQuests.add(new CopQuestUnhappyFamilies());

        System.out.println("test 2");
        
        /**ТЕСТОВЫЙ ДИАЛОГ  ПО НОВОЙ СИСТЕМЕ **/

        //  initMsg -  > respond 1 -->   msg2 -> respond -> x
           //        - > respond 2 --> x(конец) --> respond --> msg -> respond -> x

        zones.add(new DialogueZone(new Dialogue() {
            @Override
            public void init() {

                Message initM = new Message();
                Message m = null;
                this.initialMessage = initM;

                //Начало диалога
                initM.setText("Привет!");
                ArrayList<Respond> responds = new ArrayList<>();
                responds.add(new Respond());
                responds.get(0).setText("Ответ 1.");
                responds.add(new Respond());
                responds.get(1).setText("Ответ 2.");
                responds.add(new Respond());
                responds.get(2).setText("Ответ 3.");
                initM.setResponds(responds);

                //Ветка первого ответа

                m = new Message();
                responds.get(0).setMsg(m);
                m.setText("Ветка ответа 1");
                responds = new ArrayList<>();
                responds.add(new Respond("заканчиваем диалог (просто)"));
                responds.add(new Respond("заканчиваем действием", new QuestAction() {
                    @Override
                    public void performAction() {
                        Renderer.addMessage("произошло действие");
                    }
                }));
                m.setResponds(responds);

                //Ветка второго ответа
                responds = (ArrayList<Respond>) initM.getResponds();
                m = new Message();
                responds.get(1).setMsg(m);
                m.setText("Ветка ответа 2");
                responds = new ArrayList<>();
                responds.add(new Respond("заканчиваем диалог (просто)"));
                responds.add(new Respond("продолжение диалога",new Message()));
                m.setResponds(responds);
                //Подветка
                m = responds.get(1).getMsg();
                m.setText("подветка ответа 2, ответ с продолжением");
                responds = new ArrayList<>();
                responds.add(new Respond("заканчиваем диалог (просто)"));
                responds.add(new Respond("заканчиваем действием", new QuestAction() {
                    @Override
                    public void performAction() {
                        Renderer.addMessage("произошло действие");
                    }
                }));
                m.setResponds(responds);

                //Ветка третьего ответа
                responds = (ArrayList<Respond>) initM.getResponds();
                m = new Message();
                responds.get(2).setMsg(m);
                m.setText("Ветка ответа 3");
                responds = new ArrayList<>();
                responds.add(new Respond("пошел нахуй"));
                m.setResponds(responds);

                this.currentMessage = this.initialMessage;
            }
        }, 200, 200, 200, 200,false));



        //КОНЕЦ ДИАЛОГА

        /** ЗАДНИК **/
        objects.add(new DemoLevel0_BG(0,0));



        objects.add(new Crate0(100,100));

        /** Дома **/

        /* Дом 1 */

        //Сам дом
        objects.add(new FatBuilding(1690,280));
        //Вход в подъезд : 1
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(1900,800,70,70, L1B1P1F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 2
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(2255,800,70,70, L1B1P2F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 3
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(2615,800,70,70, L1B1P3F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 4
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(2970,800,70,70, L1B1P4F1.getInstance(), 190,220,false)));

        /* Дом 2 */
        objects.add(new FatBuilding(1690,1100));
        //Вход в подъезд : 1
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(1900,1620,70,70, L1B2P1F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 2
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(2255,1620,70,70, L1B2P2F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 3
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(2615,1620,70,70, L1B2P3F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 4
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(2970,1620,70,70, L1B2P4F1.getInstance(), 190,220,false)));

        /* Дом 3 */
        objects.add(new FatBuilding(3630,280));
        //Вход в подъезд : 1
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(3840,800,70,70, L1B3P1F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 2
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4200,800,70,70, L1B3P2F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 3
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4560,800,70,70, L1B3P3F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 4
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4900,800,70,70, L1B3P4F1.getInstance(), 190,220,false)));


        /* Дом 4 */
        objects.add(new FatBuilding(3630,1100));
        //Вход в подъезд : 1
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(3840,1620,70,70, L1B4P1F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 2
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4200,1620,70,70, L1B4P2F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 3
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4560,1620,70,70, L1B4P3F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 4
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4900,1620,70,70, L1B4P4F1.getInstance(), 190,220,false)));


        /* Дом 8 */
        //3700 2100
        objects.add(new FatBuilding(3700,2100));
        //Вход в подъезд : 1
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(3910,2620,70,70, L1B8P1F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 2
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4270,2620,70,70, L1B8P2F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 3
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4630,2620,70,70, L1B8P3F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 4
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4990,2620,70,70, L1B8P4F1.getInstance(), 190,220,false)));


        /* Дом 9 */
        //3700 3100
        objects.add(new FatBuilding(3700,3100));
        //Вход в подъезд : 1
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(3910,3620,70,70, L1B8P1F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 2
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4270,3620,70,70, L1B8P2F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 3
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4630,3620,70,70, L1B8P3F1.getInstance(), 190,220,false)));
        //Вход в подъезд : 4
        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(4990,3620,70,70, L1B8P4F1.getInstance(), 190,220,false)));


        /* Дом  */
        objects.add(new TallFatBuilding(1730,2134));

        /* Дом  */
        objects.add(new TallFatBuilding(2640,3045));

        /* Дом 5 */
        objects.add(new LoongGrayBuildingFront(5490,214));// фронт дома
        objects.add(new LoongGrayBuildingSide(5490,894));// боковина среднего дома4
        objects.add(new LoongGrayBuildingSide(8272,894));// втарая боковина среднего дома
        //Вход в подъезд : 2
        zones.add(new InteractiveEnterZone(new PadikLock("2"),new EnterZone(5990 ,900,70,70, L1B5P2F1.getInstance(),800,800,false)));
        //Вход в подъезд : 3
        zones.add(new InteractiveEnterZone(new PadikLock("298К2939"),new EnterZone(6243 ,900,70,70, L1B5P3F1.getInstance(),800,800,false)));
        //Вход в подъезд : 4
        zones.add(new InteractiveEnterZone(new PadikLock("298К2939"),new EnterZone(6496 ,900,70,70, L1B5P4F1.getInstance(),800,800,false)));
        //Вход в подъезд : 5
        zones.add(new InteractiveEnterZone(new PadikLock("298К2939"),new EnterZone(6715 ,900,70,70, L1B5P5F1.getInstance(),800,800,false)));
        //Вход в подъезд : 6
        zones.add(new InteractiveEnterZone(new PadikLock("298К2939"),new EnterZone(6966 ,900,70,70, L1B5P6F1.getInstance(),800,800,false)));
        //Вход в подъезд : 7
        zones.add(new InteractiveEnterZone(new PadikLock("298К2939"),new EnterZone(7253 ,900,70,70, L1B5P7F1.getInstance(),800,800,false)));
        //Вход в подъезд : 8
        zones.add(new InteractiveEnterZone(new PadikLock("298К2939"),new EnterZone(7533 ,900,70,70, L1B5P8F1.getInstance(),800,800,false)));
        //Вход в подъезд : 9
        zones.add(new InteractiveEnterZone(new PadikLock("298К2939"),new EnterZone(7823 ,900,70,70, L1B5P9F1.getInstance(),800,800,false)));
        //Вход в подъезд : 10
        zones.add(new InteractiveEnterZone(new PadikLock("298К2939"),new EnterZone(8112 ,900,70,70, L1B5P10F1.getInstance(),800,800,false)));
        //Вход в подъезд : 1
        zones.add(new InteractiveEnterZone(new PadikLock("298К2939"),new EnterZone(5995 ,1226,70,70, L1B5P1F1.getInstance(),800,800,false)));
        //Вход в подъезд : 11
      //  zones.add(new InteractiveEnterZone(new PadikLock("298К2939"),new EnterZone(8189  ,1226,70,70, L1B5P11F1.getInstance(),800,800,false)));




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
            zones.add(new MiniGameZone(500,500,100,100,new Domofon("111",HubHataIgoryana.getInstance(),300,300)));
       // QuestUtility.startQuest1();
        Spawner.updateTimers();
        System.out.println("test 4");


    }
}
