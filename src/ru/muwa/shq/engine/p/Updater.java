package ru.muwa.shq.engine.p;
import ru.muwa.shq.engine.ai.AI;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.utilities.*;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.controls.PlayerControls;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.p.collisions.CollisionsChecker;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Player;

/**
 * Класс, отвечающий за изменение положения игровых объектов и сущностей в пространстве.
 *  А так же за вызов соотвеетствующих обновляющих служб.
 */
public class Updater implements Runnable
{
    public static Updater instance;
    private Thread thread; // Поток движка игровых состояний.
    private Player player; // Ссылка на игрока.

    private Updater()
    {
        if(instance != null) return;
        instance = this;
        player = Player.get();
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run()
    {
        System.out.println("Physx engin initialized. Updater thread started.");


        double drawInterval = 1_000_000_000/60;
        double delta=0;
        long lastTime = System.nanoTime();
        long currTime;

        while(thread !=  null)
        {
            currTime = System.nanoTime();
            delta += (currTime - lastTime) / drawInterval;
            lastTime = currTime;

            for(GameObject o : Engine.getCurrentLevel().getObjects()) CollisionsChecker.getInstance().checkCollisions(o, Engine.getCurrentLevel().getObjects());
            //Попытка вынести расчёты столновений за ограничение в 60 итераций в секунду для исправления бага с выталкиванием за текстуры
            //TODO: вероятно стоит запустить отдельный поток для провери столновений вне основного потоа updater'a
            //Баг удалось исправить.

            if(delta >= 1)
            {
                update();
                Renderer.getInstance().render();
                delta--;
            }
        }
    }

    /**
     * Основной метод обработчика внутриигровых действий и состояний.
     * Вызывается каждый кадр, проверяет состояние внутриигровых объектов и обновляет их.
     */

    //TODO: Ранее проверка коллизий вызывалась дважды - в начале и в конце итерации.
    // Нужно проверить насколько это была необходимая мера и можно ли обойтись без неё.
    private void update()
    {

        //System.out.println(Math.toDegrees(Math.acos( ((4*4) + (3*3) - (5*5)) / (2 * 4 * 3) )) );


        //Блок обработки игрока.

        // Проверяем были ли команды игроку через игровое управление.
        PlayerControls.getInstance().controlPlayer(); //TODO: Тут должен быть весь код, который зависит от ввода.

        // Проверяем столкновение игрока с объектами.
        CollisionsChecker.getInstance().checkCollisions(player, Engine.getCurrentLevel().getObjects());

        //Обновляем бокс игрока.
        SolidBoxUpdater.getInstance().updateSolidBox(player);

        //Обновляем окно инвентаря

        InventoryManager.getInstance().update();

        // Обновление зоны доступного использования
        UseZoneUpdater.getInstance().update();

        // Через службу проверки игровых зон смотрим взаимодействие игрока с той или иной зоной
        GameZoneUtility.getInstance().work();

        //Блок обработки обычных объектов из списка текущих.
        for(GameObject o : Engine.getCurrentLevel().getObjects())
        {
            // Проверяем столкновения
            CollisionsChecker.getInstance().checkCollisions(o, Engine.getCurrentLevel().getObjects());

            // Обновляем боксы
            SolidBoxUpdater.getInstance().updateSolidBox(o);

        }

        //Блок обработки НПЦ из списка текущих.
        for(NPC c : Engine.getCurrentLevel().getNPC())
        {
            //Передаем нпц ии, чтобы тот решил что ему делать.
            AI.getInstance().move(c);

            // Проверяем столкновения.
            CollisionsChecker.getInstance().checkCollisionsNPC(c, Engine.getCurrentLevel().getObjects());

            // Обновляем бокс.
            SolidBoxUpdater.getInstance().updateSolidBox(c);

            // Обновляем стены рейкастера и сам рейкастер.
            // TODO: Проверить насколько необходимо обновление каждую итерацию и скорость при большом кол-ве нпц.
            c.getRayCaster().setBorders(c.getRayCaster().buildLines(Engine.getCurrentLevel().getObjects()));
            RayCasterUpdater.getInstance().updateRayCaster(c.getRayCaster(),c);

        }

        //Блок обработки объектов контейнеров из списка текущих.
        for(Container con : Engine.getCurrentLevel().getContainers())
        {

            CollisionsChecker.getInstance().checkCollisions(con,Engine.getCurrentLevel().getObjects());

            // Обновляем бокс
            SolidBoxUpdater.getInstance().updateSolidBox(con);
        }
        //Блок ПОСТ обработки

        //Очищаем нажатые клавиши. (Фиксим баг с мульти прожатием клавиш)
       // DropKeyUtility.getInstance().work(); //TODO: Убрать.
    }
    public static Updater getInstance()
    {
        if (instance == null) return new Updater();
        else return instance;
    }

}