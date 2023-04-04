package edu.ucalgary.oop;

public class Porcupine extends Animal{
    private static final int[] FEED_HOUR = {19, 20, 21};
    private static final int FOOD_PREP = 0;
    private static  final int FEED_DURATION = 5;
    private static final  int CLEAN_TIME = 10;


    public Porcupine(int animalID, String animalNickname){
        super(animalID, animalNickname);
    }
    public static int[] getFeedHour(){
        return FEED_HOUR;
    }
    public static int getFoodPrep(){
        return FOOD_PREP;
    }
    public static int getFeedDuration(){
        return FEED_DURATION;
    }
    public static int getCleanTime(){
        return CLEAN_TIME;
    }
}
