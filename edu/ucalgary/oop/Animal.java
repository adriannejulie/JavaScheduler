/**
  @author Bernard Aire
  bernard.aire@ucalgary.ca
  @version 1.0
  @since 1.0
 */


package edu.ucalgary.oop;

public class Animal {
    private int animalID;
    private String animalNickname;

    /*
     * constructor
     * 
     */
    public Animal (int animalID, String animalNickname) {
        this.animalID = animalID;
        this.animalNickname = animalNickname;

    }
    public int getAnimalID(){
        return this.animalID;

    }
    public String getAnimalName(){
        return this.animalNickname;

    }
    
    public void setAnimalID(int animalID){
        this.animalID = animalID;
    }
    public void setAnimalNickname(String animalNickname){
        this.animalNickname = animalNickname;
    }
    

}

