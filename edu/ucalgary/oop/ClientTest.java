


import org.junit.Test;

import edu.ucalgary.oop.Beaver;
import edu.ucalgary.oop.Client;
import edu.ucalgary.oop.Coyote;
import edu.ucalgary.oop.Fox;
import edu.ucalgary.oop.Porcupine;
import edu.ucalgary.oop.Raccoon;
import edu.ucalgary.oop.Task;
import edu.ucalgary.oop.VolunteerNeededException;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class ClientTest {


    @Test
    /*
     * Testing constructor for Client
     */
    
    public void testClientCtr(){

        boolean passed = true;

        try{
            Client c = new Client();
        }
        catch (Exception e){
            passed = false;
        }

        assertTrue("Client was not made successfully", passed);

    }


    @Test
    /*
     * Testing the close() method
     */

     public void testClose(){

        boolean passed = true;

        Client c = new Client();
        try{
            c.close();
        }
        catch (Exception e){
            passed = false;
        }

        assertTrue("Client was not closed properly", passed);
     }

    @Test

    /*
     * Tests if the exception was thrown
     */

     public void testVolunteerException(){

        boolean passed = false;

        Client c = new Client();
        try{

            c.buildSchedule();
        }
        catch(VolunteerNeededException v){
            passed = true;
        }
        catch(Exception e){}

        assertTrue("Building the schedule did not throw a volunteer exception");
     }
    @Test

    /*
     * Check if a schedule file was made with correct values
     */

    public void testUploadSchedule(){

        boolean passed = true;
        Client c = new Client();

        c.buildSchedule();
        c.uploadSchedule();

        File f = new File("schedule.txt");
        if(!f.exists()){

            passed = false;

        }

        assertTrue("The schedule was not created", passed);
    }

    @Test
    /*
     * Testing if getters and setters working correctly
     */

    public void testGetSomeAnimals(){

        boolean passed =  true;
        Client c = new Client();

        List<Beaver> beavers = Arrays.asList(c.getBeavers());
        List<Porcupine> porcupines = Arrays.asList(c.getPorcupines());
        List<Fox> foxes = Arrays.asList(c.getFoxes());
        List<Raccoon> racoons = Arrays.asList(c.getRaccoons());        
        List<Coyote> coyotes = Arrays.asList(c.getCoyotes());

        //See if each array has the right animals:

    
        Porcupine expectedPorcupine = new Porcupine(8, "Spike");
        Fox expectedFox = new Fox(6, "Annie, Oliver and Mowgli");
        Coyote expectedCoyote = new Coyote(12, "Shadow");
    

        boolean bPass = true;
        boolean pPass = true;
        boolean fPass = true;
        boolean rPass = true;
        boolean cPass = true;

        if (beavers.size() != 0){
            bPass = false;
        }
        if (!porcupines.contains(expectedPorcupine)){

            pPass = false;
        }
        if(!foxes.contains(expectedFox)){

            fPass = false;
        }
        if(racoons.size() != 0){

            rPass = false;
        }
        if(!coyotes.contains(expectedCoyote)){

            cPass = false;
        }


        assertTrue("The expected data value do not match with the fetched ones", (bPass && pPass && fPass && rPass && cPass));


    }

    @Test
    /*
     * Test if tasks were retrieved and have the correct values
     */
    public void testGetTasks(){


        Client c = new Client();

        List<Task> taskList = Arrays.asList(c.getTreatments());


        Task expectedTask1 = new Task(10, "Inspect broken leg", 5, 2, 13, 2);

        Task expectedTask2 = new Task(10, "Inspect broken leg", 5, 2, 13, 14);

        ArrayList<Task> expectedTaskArrL = new ArrayList<Task>();

        expectedTaskArrL.add(expectedTask1);
        expectedTaskArrL.add(expectedTask2);

        boolean passed = true;
    
        //Check if the task list array has this value
        if(!taskList.contains(expectedTask1)){
            passed = false;
        }

        ArrayList<Task> gottenTasks = c.getTasks(10);

        if(!gottenTasks.equals(expectedTaskArrL)){

            passed = false;
        }

        assertTrue("getTreatments() did not retrieve all tasks correctly", passed);
        


    }


}
