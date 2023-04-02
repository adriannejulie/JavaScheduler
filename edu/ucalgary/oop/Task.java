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

     /*
      * getters and setters --maybe change docstring?
      */

    public void setTaskID (int id) {
        this.taskID = id;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void setDuration (int duration) {
        this.duration = duration;
    }

    public void setMaxWindow (int maxWindow) {
        this.maxWindow = maxWindow;
    }

    public void setStartHour (int start) {
        this.startHour = start;
    }

    public void setAnimalID (int id) {
        this.animalID = id;
    }

    public int getTaskID() {
        return this.taskID;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getMaxWindow() {
        return this.maxWindow;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getAnimalID() {
        return this.animalID;
    }
}
