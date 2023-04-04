/**
  @author Bernard Aire
  bernard.aire@ucalgary.ca
  @version 1.0
  @since 1.0
 */


package edu.ucalgary.oop;

public class Fox extends Animal{
    private static final int[] FEED_HOUR = {0, 1, 2};
    private static final int FOOD_PREP = 5;
    private static final int FEED_DURATION = 5;
    private static final int CLEAN_TIME = 10;

    /*
     * Getters and setters
     */

    public Fox(int animalID, String animalNickname){
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
