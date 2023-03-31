
/**
@author Braden Vivas
braden.vivas@ucalgary.ca
@version 1.0
@since 1.0
*/

package edu.ucalgary.oop;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import java.time.*;
/*
* Client is a client for the user to interact with that generates a schedule based on the database provided
* After called by the GUI, the client is created
*/
public class Client{


    private Task[] treatments;
    private Fox[] foxes;
    private Porcupine[] porcupines;
    private Raccoon[] raccoons;
    private Coyote[] coyotes;
    private Beaver[] beavers;

    private Schedule schedule;
    private Connection dbConnect;
    private ResultSet results;

    private boolean[] volunteerNeeded;

    /*
    * Constructor that connects to the MySQL database and retrieves data ***CHANGE USER AND PASS LATER
    * PARAMATERS: NONE
    * PROMIMSES: DATA STORED INTO VALUES, NO RETURN VALUE
    */
    public Client(){

        Arrays.fill(this.volunteerNeeded, false);

        //Open a connection to the database
        try{
            this.dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "Marasco", "ensf");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Initalize Animal arrays from the data inside the 

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT animalID, animalNickname FROM ANIMALS WHERE AnimalSpecies='coyote'");
            
            ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();

            while (results.next()){

                Coyote newC = new Coyote(results.getInt("animalID"), results.getString("animalNickname"));
                coyoteList.add(newC);
            }
            
            this.coyotes = coyoteList.toArray(this.coyotes);

            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT animalID, animalNickname FROM ANIMALS WHERE AnimalSpecies='fox'");
            
            ArrayList<Fox> foxList = new ArrayList<Fox>();

            while (results.next()){

                Fox newF = new Fox(results.getInt("animalID"), results.getString("animalNickname"));
                foxList.add(newF);
            }
            
            this.foxes = foxList.toArray(this.foxes);

            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT animalID, animalNickname FROM ANIMALS WHERE AnimalSpecies='porcupine'");
            
            ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();

            while (results.next()){

                Porcupine newP = new Porcupine(results.getInt("animalID"), results.getString("animalNickname"));
                porcupineList.add(newP);
            }
            
            this.porcupines = porcupineList.toArray(this.porcupines);

            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT animalID, animalNickname FROM ANIMALS WHERE AnimalSpecies='beaver'");
            
            ArrayList<Beaver> beaverList = new ArrayList<Beaver>();

            while (results.next()){

                Beaver newB = new Beaver(results.getInt("animalID"), results.getString("animalNickname"));
                beaverList.add(newB);
            }
            
            this.beavers = beaverList.toArray(this.beavers);

            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT animalID, animalNickname FROM ANIMALS WHERE AnimalSpecies='porcupine'");
            
            ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();

            while (results.next()){

                Raccoon newR = new Raccoon(results.getInt("animalID"), results.getString("animalNickname"));
                raccoonList.add(newR);
            }
            
            this.raccoons = raccoonList.toArray(this.raccoons);

            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        //Get tasks from database

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT TASKS.TaskID, TASKS.Description, TASKS.Duration, TASKS.MaxWindow, TREATMENTS.StartHour, TREATMENTS.AnimalID FROM TASKS INNER JOIN TREATMENTS ON TASKS.TaskID = TREATMENTS.TaskID ORDER BY TaskID");

            ArrayList<Task> taskList = new ArrayList<Task>();
            while (results.next()){

                Task newTask = new Task(results.getInt("TaskID"), results.getString("Description"), results.getInt("Duration"), results.getInt("MaxWindow"), results.getInt("StartHour"), results.getInt("AnimalID"));

                taskList.add(newTask);

            }

            this.treatments = taskList.toArray(this.treatments);
            
        } catch (Exception e) {
            e.printStackTrace();
        }





    }

    /*
    * uploadSchedule() will create a new schedule object with the data obtained. It will throw a VolunteerNeededException which will be passed to the GUI. NOTE THAT THE SCHEDULE OBJECT SHOULD HAVE BE A MATRIX OF FORMATTED STRINGS (set the example .txt file for details)
    * REQUIRES: NONE
    * PROMISES: NONE
    */
    public void uploadSchedule() throws VolunteerNeededException{

        try{

            this.schedule = new Schedule(treatments, coyotes, foxes, porcupines, beavers, raccoons);
        }
        catch (VolunteerNeededException v ){

            throw new VolunteerNeededException();
        }


        String[][] draftedSch = this.schedule.getScheduleTime();

        int currentHour = 0;

        //Write to a file
        try {

            FileWriter outFile = new FileWriter("schedule.txt");
            String header = new String("Schedule for " + LocalDate.now());
            outFile.write(header);
            outFile.write("\n");
            for (String[] hour : draftedSch){

                if(hour.length != 0){

                    if(this.volunteerNeeded[currentHour]){

                        outFile.write(String.format("%d:00 [+ backup volunteer]", currentHour + 1));
                    }
                    else{
                        outFile.write(String.format("%d:00", currentHour));
                    }

                    for(String taskString : hour){

                        outFile.write(taskString);
                    }

                }

                outFile.write("\n");
                currentHour++;

            }

            outFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        

    }

    /*
    * Changes the old task with the same id as the new given task
    * REQUIRES: The new task
    * PROMISES: None
    */
    public void changeMedicalTask (Task newTask) {

        ArrayList<Task> taskList = new ArrayList<Task>(Arrays.asList(this.treatments));

        int i = 0;

        for (Task task : taskList){
            if (task.getTaskID() == newTask.getTaskID()){
                
                taskList.set(i, task);
                break;
            }
        }

        this.treatments = taskList.toArray(this.treatments);

    }


    /*
    * getters and setters
    */



    public Task[] getTreatments(){ return this.treatments;}
    public Fox[] getFoxes(){ return this.foxes;}
    public Porcupine[] getPorcupines() {return this.porcupines;}
    public Raccoon[] getRaccoons() {return this.raccoons;}
    public Coyote[] getCoyotes() {return this.coyotes;}
    public Beaver[] getBeavers() {return this.beavers;}

    public void setVolunteerNeeded(int hour) {

        //List<Boolean> boolList = Arrays.asList(this.volunteerNeeded);
        ArrayList<Boolean> boolList = new ArrayList<Boolean>();

        for (Boolean b : this.volunteerNeeded){

            boolList.add(b);
        }
        
        boolList.set(hour, true);


        boolean[] newV = {};
        int i = 0;
        for (Boolean b : boolList){
        
            newV[i] = b;
            i++;
        }

        this.volunteerNeeded = newV;
    }


    /*
     * Returns a task from the given id. If there is more than one, return first one 
     * REQUIRES: The id of the wanted task
     * PROMISES: A task in treatments that matches the given id
     */

    public Task getTask(int id){

        for (Task task : treatments){

            if (task.getTaskID() == id){
                return task;
            }
        }

    }

    


}