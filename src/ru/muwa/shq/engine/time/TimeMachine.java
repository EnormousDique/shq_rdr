package ru.muwa.shq.engine.time;

import ru.muwa.shq.economics.bills.Bill;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;

import java.io.IOException;
import java.util.ArrayList;

public class TimeMachine {
    private static final long DAY_LENGTH = 720_000;
   // private static final long GAME_CYCLE_LENGTH = 16;
    public static ArrayList<Bill> bills = new ArrayList<>();
    private static TimesOfTheDay timeOfTheDay = TimesOfTheDay.SUNRISE;
    public static TimesOfTheDay getTimeOfTheDay() {return timeOfTheDay;}
    public enum TimesOfTheDay{
        SUNRISE, MORNING,AFTERNOON,DUSK,EVENING,NIGHT;

    }
    private static int dayNumber;
    private static long startTime;
    private static long currentTime;
    public static void setStartTime(){
        startTime = /* System.currentTimeMillis(); */ 0 ;
        currentTime = startTime;
    }
    public static void work()
    {
        currentTime = currentTime + (1_000 /60);
        dayNumber = (int) Math.floor(currentTime/DAY_LENGTH) +1;

        long timeFromTheBeginningOfADay = currentTime % DAY_LENGTH;
        if(timeFromTheBeginningOfADay < 100_000) timeOfTheDay = TimesOfTheDay.SUNRISE;
        if(200_000 < timeFromTheBeginningOfADay && timeFromTheBeginningOfADay < 300_000) timeOfTheDay = TimesOfTheDay.MORNING;
        if(300_000 < timeFromTheBeginningOfADay && timeFromTheBeginningOfADay < 500_000) timeOfTheDay = TimesOfTheDay.AFTERNOON;
        if(500_000 < timeFromTheBeginningOfADay && timeFromTheBeginningOfADay <600_000) timeOfTheDay = TimesOfTheDay.EVENING;
        if(600_000 < timeFromTheBeginningOfADay) timeOfTheDay = TimesOfTheDay.NIGHT;

        try {
            sendBills();
        }catch (Exception e)
        {

        }


    }

    private static void sendBills() throws IOException {

        int week = (int) Math.ceil(dayNumber / 7.0);
      //  System.out.println("week : " + week);

        if(week > 1)
        {
            if(bills.isEmpty()) {bills.add(new Bill(1000,1));HubHataIgoryana.getInstance().getContainers().get(0).addItem(bills.get(0));}
        }
        if (week>2)
        {
            System.out.println("наступила третья или больше неделя");
            int billTotal = 1000;

            if(!bills.get(week-3).isPayed())
            {
                System.out.println("счет за предыдущую неделю не оплачен. Удваиваем счет");
                billTotal *= 2;
            }


            if(bills.size() < week-1)
            {
                System.out.println("счет за прошедную неделю не был отправлен");
                bills.add(new Bill(billTotal,week-1));
                HubHataIgoryana.getInstance().getContainers().get(0).addItem(bills.get(week-2));
            }
        }

    }

    public static String getStringTime()
    {
        String output = "";

        long time = currentTime % DAY_LENGTH;

        int hours = (int)Math.floor(time / 30_000);
        String minutes = "" + (int)( time % 30_000 ) *2;

        if(minutes.length() < 4) minutes = "00";
        else {
            if (minutes.length() < 5) minutes = "0"+minutes.charAt(0);
            else minutes = minutes.charAt(0) + "" + minutes.charAt(1);
        }

        output += "" + hours +":"+minutes + " ; day " + dayNumber;

        return output;
    }

    public static void rewind(int n){
        currentTime += n;
        dayNumber = (int) Math.floor(currentTime/DAY_LENGTH) +1;
    }


}
