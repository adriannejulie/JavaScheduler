package edu.ucalgary.oop;
/**
@author Braden Vivas
braden.vivas@ucalgary.ca
@version 1.5
@since 1.0
*/


import java.sql.*;
import java.util.*;
import java.io.*;
import java.time.*;
/**
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


    private int volunteerHour;
    /**
    * Constructor that connects to the MySQL database and retrieves data
    */
    public Client(){

        //Open a connection to the database
        try{
            this.dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/EWR", "oop", "password");
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
            
            Coyote[] cArr = new Coyote[coyoteList.size()];
            this.coyotes = coyoteList.toArray(cArr);

            myStmt.close();
            results.close();

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
            
            Fox[] fArr = new Fox[foxList.size()];
            this.foxes = foxList.toArray(fArr);

            myStmt.close();
            results.close();

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

            Porcupine[] pArr = new Porcupine[porcupineList.size()];
            this.porcupines = porcupineList.toArray(pArr);

            myStmt.close();
            results.close();

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
            
            Beaver[] bArr = new Beaver[beaverList.size()];
            this.beavers = beaverList.toArray(bArr);

            myStmt.close();
            results.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {                    
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT animalID, animalNickname FROM ANIMALS WHERE AnimalSpecies='raccoon'");
            
            ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();

            while (results.next()){

                Raccoon newR = new Raccoon(results.getInt("animalID"), results.getString("animalNickname"));
                raccoonList.add(newR);
            }
            
            Raccoon[] rArr = new Raccoon[raccoonList.size()];
            this.raccoons = raccoonList.toArray(rArr);

            myStmt.close();
            results.close();

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

            Task[] tArr = new Task[taskList.size()];
            this.treatments = taskList.toArray(tArr);

            myStmt.close();
            results.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        //Initialize a new schedule
        this.schedule = new Schedule();



    }

    /**
     * Creates a schedule which will throw VolunteerNeededException if there are any volunteer needed.
     * 
     * @throws VolunteerNeededException throws the volunteerNeededException given from Schedule
     * @throws VetNeededException throws the exception given from Schedule
     */

    public void buildSchedule() throws VolunteerNeededException, VetNeededException{


        try{

            this.schedule.buildSchedule(treatments, coyotes, foxes, porcupines, beavers, raccoons);
        }
        catch (VolunteerNeededException v ){

            this.volunteerHour = this.schedule.getVolunteerHour();
            throw new VolunteerNeededException();
        }
        catch (VetNeededException n){

            throw new VetNeededException();
        }

    }
    /**
    * uploadSchedule() will create a new schedule object with the data obtained. It will throw a VolunteerNeededException which will be passed to the GUI. NOTE THAT THE SCHEDULE OBJECT SHOULD HAVE BE A MATRIX OF FORMATTED STRINGS (set the example .txt file for details)
    */
    public void uploadSchedule(){



        boolean[] vNeeded = this.schedule.getTrueVolunteerHours();

        if (this.schedule != null){
        
        ArrayList<ArrayList<String>> draftedSch = this.schedule.getScheduleTime();

        int currentHour = 0;

        //Write to a file
        try {

            FileWriter outFile = new FileWriter("schedule.txt");
            String header = new String("Schedule for " + LocalDate.now());
            outFile.write(header);
            outFile.write("\n\n");
            for (ArrayList<String> hour : draftedSch){

                if(hour.size() != 0){

                    //Print title
                    if(vNeeded[currentHour]){

                        outFile.write(String.format("%d:00 [+ backup volunteer]", currentHour));
                    }
                    else{
                        outFile.write(String.format("%d:00", currentHour));
                    }

                    outFile.write("\n");
                    //Print each task in hour
                    for(String taskString : hour){

                        outFile.write(taskString);
                        outFile.write("\n");
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
        

    }

    /**
    * Changes the old task with the same id as the new given task
    * @param newTask the task to be added into the database
    */
    public void changeMedicalTask (Task newTask) {

        ArrayList<Task> taskList = new ArrayList<Task>(Arrays.asList(this.treatments));

        int i = 0;

        for (Task task : taskList){

            if (task.getTaskID() == newTask.getTaskID() && task.getAnimalID() == newTask.getAnimalID()){

                taskList.set(i, task);

                try {
            
                    String query = "UPDATE TREATMENTS SET StartHour = ? WHERE TaskID = ? AND AnimalID = ?";
                    PreparedStatement myStmt = dbConnect.prepareStatement(query);
                    
                    myStmt.setString(1, Integer.toString(newTask.getStartHour()));
                    myStmt.setString(2, Integer.toString(task.getTaskID()));
                    myStmt.setString(3, Integer.toString(task.getAnimalID()));
                    
                    myStmt.executeUpdate();
                    
                    myStmt.close();
        
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
           
            
            }    
            i++;
        }

        Task[] taskArr = new Task[taskList.size()];
        this.treatments = taskList.toArray(taskArr);
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
    public Schedule getSchedule() {return this.schedule;}
    public void setTreatments(Task[] newTasks){ this.treatments = newTasks;}


    /**
     * Returns all tasks that match the given id. 
     * @param id The id of the wanted task(s)
     * @return an ArrayList of treatments that matches the given id
     */

    public ArrayList<Task> getTasks(int id){


        ArrayList<Task> matchingTasks = new ArrayList<Task>();

        for (Task task : this.treatments){

            if (task.getTaskID() == id){

                matchingTasks.add(task);
                
            }
        }

        return matchingTasks;

    }

    /**
     * Close() closes the connection to the database.
     */

    public void close(){

        try {
            
            this.dbConnect.close();
            this.dbConnect = null;
            
        } catch (SQLException s) {
            System.out.println("Error closing connection with the database");
            s.printStackTrace();
        }


    }

    


}