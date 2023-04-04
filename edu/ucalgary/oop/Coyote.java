/**
  @author Bernard Aire
  bernard.aire@ucalgary.ca
  @version 1.0
  @since 1.0
 */

package edu.ucalgary.oop;

public class Coyote extends Animal{
    private static int[] FEED_HOUR = {19, 20, 21};
    private static int FOOD_PREP = 10;
    private static int FEED_DURATION = 5;
    private static int CLEAN_TIME = 5;

    public Coyote(int animalID, String animalNickname){

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
