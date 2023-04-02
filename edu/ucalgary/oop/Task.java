package edu.ucalgary.oop;

import java.sql.ResultSet;

public class Task {
    private int taskID;
    private String description;
    private int duration;
    private int maxWindow;
    private int startHour;
    private int animalID;

    /*
     * Constructor
     */

     public Task(int taskID, String description, int duration, int maxWindow, int startHour, int animalID) {
         this.taskID = taskID;
         this.description = description;
         this.duration = duration;
         this.maxWindow = maxWindow;
         this.startHour = startHour;
         this.animalID = animalID;
     }
}
