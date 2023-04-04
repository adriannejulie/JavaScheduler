package edu.ucalgary.oop;

import org.junit.Test;
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
     * Check if a schedule file was made with correct values
     */

    public void testUploadSchedule(){

        boolean passed = true;
        Client c = new Client();

        c.uploadSchedule();

        File f = new File("schedule.txt");
        if(f.exists()){

            passed = false;

        }

        assertTrue("The schedule was not created", passed);
    }

    @Test
    /*
     * Testing if a new task can be changed
     */

    public void testVolunteerException(){

        boolean passed = false;

        //Make 2 tasks that happen at the same time on two different animals

        Task task1 = new Task(1, "Task 1", 1, 1, 0, 1);
        Task task2 = new Task(2, "Task 2", 1, 1, 0, 2);

        Task[] newTaskArr = {task1, task2};

        Client c = new Client();

        c.setTreatments(newTaskArr);

        try{
            c.buildSchedule();
        }
        catch(VolunteerNeededException v){

            passed = true;

        }
        catch(Exception e){}

        assertTrue("VolunteerNeededException was not thrown", passed);
    }


}
