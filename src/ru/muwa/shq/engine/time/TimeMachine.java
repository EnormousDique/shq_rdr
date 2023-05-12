package ru.muwa.shq.engine.time;

public class TimeMachine {
    private static final long DAY_LENGTH = 720_000;
   // private static final long GAME_CYCLE_LENGTH = 16;
    private static TimesOfTheDay timeOfTheDay = TimesOfTheDay.SUNRISE;
    public static TimesOfTheDay getTimeOfTheDay() {return timeOfTheDay;}
    public enum TimesOfTheDay{
        SUNRISE, MORNING,NOON,AFTERNOON,DUSK,EVENING,NIGHT;

    }
    private static int dayNumber;
    private static long startTime;
    private static long currentTime;
    public static void setStartTime(){
        startTime = System.currentTimeMillis();
        currentTime = startTime;
    }
    public static void work()
    {
        currentTime = System.currentTimeMillis() - startTime;
        dayNumber = (int) Math.floor(currentTime/DAY_LENGTH) +1;

        long timeFromTheBeginningOfADay = currentTime % DAY_LENGTH;
        if(timeFromTheBeginningOfADay < 100_000) timeOfTheDay = TimesOfTheDay.DUSK;
        if(200_000 < timeFromTheBeginningOfADay && timeFromTheBeginningOfADay < 300_000) timeOfTheDay = TimesOfTheDay.NOON;
        if(400_000 < timeFromTheBeginningOfADay && timeFromTheBeginningOfADay < 500_000) timeOfTheDay = TimesOfTheDay.DUSK;
        if(500_000 < timeFromTheBeginningOfADay && timeFromTheBeginningOfADay <600_000) timeOfTheDay = TimesOfTheDay.EVENING;
        if(600_000 < timeFromTheBeginningOfADay) timeOfTheDay = TimesOfTheDay.NIGHT;

        System.out.println(dayNumber);



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

        output += "" + hours +":"+minutes;

        return output;
    }

}
