/**
  @author Bernard Aire
  bernard.aire@ucalgary.ca
  @version 1.0
  @since 1.0
 */


package edu.ucalgary.oop;

public class Beaver extends Animal{
    private static final int[] FEED_HOUR = {8, 9, 10};
    private static final int FOOD_PREP = 0;
    private static final int FEED_DURATION = 5;
    private static final int CLEAN_TIME = 5;


    public Beaver(int animalID, String animalNickname){

        super(animalID, animalNickname);


    }
    public  int[] getFeedHour(){
        return FEED_HOUR;
    }
    public int getFoodPrep(){
        return FOOD_PREP;
    }
    public int getFeedDuration(){
        return FEED_DURATION;
    }
    public int getCleanTime(){
        return CLEAN_TIME;
    }



}
