package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;

import edu.ucalgary.oop.Beaver;
import edu.ucalgary.oop.Client;
import edu.ucalgary.oop.Coyote;
import edu.ucalgary.oop.Fox;
import edu.ucalgary.oop.Porcupine;
import edu.ucalgary.oop.Raccoon;
import edu.ucalgary.oop.Task;
import edu.ucalgary.oop.VolunteerNeededException;
import java.awt.event.ActionEvent;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestingScheduleBuild extends JFrame {
    private ScheduleGUI scheduleGUI;

    /**
     * Sets up ScheduleGUI class
     */
    @Before
    public void setUp() {
        JFrame frame = new JFrame();
        scheduleGUI = new ScheduleGUI(new JFrame());
    }

    /**
     * Tests the constructor for variables being initialized properly
     */
    @Test
    public void testConstructor() {
        assertNotNull(scheduleGUI.instructions);
        assertNotNull(scheduleGUI.closingInstructions);

    }

    /**
     * Checks if client is Null as client should not be initilized until 'Generate
     * Schedule' button is selected
     */
    @Test
    public void testClientCreation() {
        assertNull(scheduleGUI.client);
    }

    @Test
    public void testScheduleConstructor() {
        Schedule s = new Schedule();
        assertNotNull(s);
    }

    @Test
    public void testScheduleBuildValid() {
        boolean failed = false;

        ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
        for (int i = 0; i < 2; i++) {
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        }
        Coyote[] coyotes = new Coyote[2];
        coyoteList.toArray(coyotes);

        ArrayList<Fox> foxList = new ArrayList<Fox>();
        for (int i = 2; i < 4; i++) {
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        }
        Fox[] foxes = new Fox[2];
        foxList.toArray(foxes);

        ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
        for (int i = 4; i < 6; i++) {
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        }
        Porcupine[] porcupines = new Porcupine[2];
        porcupineList.toArray(porcupines);

        ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
        for (int i = 6; i < 8; i++) {
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        }
        Beaver[] beavers = new Beaver[2];
        beaverList.toArray(beavers);

        ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();
        for (int i = 8; i < 10; i++) {
            Raccoon newR = new Raccoon(i, Integer.toString(i));
            raccoonList.add(newR);
        }
        Raccoon[] raccoons = new Raccoon[2];
        raccoonList.toArray(raccoons);

        ArrayList<Task> taskList = new ArrayList<Task>();
        for (int i = 0; i < 8; i++) {
            Task newT = new Task(2, "Testing", 1, 1, i, i);
            taskList.add(newT);
        }
        Task[] tasks = new Task[8];
        taskList.toArray(tasks);

        Schedule s = new Schedule();
        try {
            s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, raccoons);
        } catch (VolunteerNeededException e) {
            failed = true;
        } catch (VetNeededException e) {
        }
        assertFalse(failed);
    }

    @Test
    public void testGetScheduleTime() {
        ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
        for (int i = 0; i < 2; i++) {
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        }
        Coyote[] coyotes = new Coyote[2];
        coyoteList.toArray(coyotes);

        ArrayList<Fox> foxList = new ArrayList<Fox>();
        for (int i = 2; i < 4; i++) {
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        }
        Fox[] foxes = new Fox[2];
        foxList.toArray(foxes);

        ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
        for (int i = 4; i < 6; i++) {
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        }
        Porcupine[] porcupines = new Porcupine[2];
        porcupineList.toArray(porcupines);

        ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
        for (int i = 6; i < 8; i++) {
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        }
        Beaver[] beavers = new Beaver[2];
        beaverList.toArray(beavers);

        ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();
        for (int i = 8; i < 10; i++) {
            Raccoon newR = new Raccoon(i, Integer.toString(i));
            raccoonList.add(newR);
        }
        Raccoon[] raccoons = new Raccoon[2];
        raccoonList.toArray(raccoons);

        ArrayList<Task> taskList = new ArrayList<Task>();
        for (int i = 0; i < 2; i++) {
            Task newT = new Task(2, "Testing", 1, 1, 10, i);
            taskList.add(newT);
        }
        Task[] tasks = new Task[2];
        taskList.toArray(tasks);

        Schedule s = new Schedule();
        try {
            s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, raccoons);
        } catch (VolunteerNeededException e) {
        } catch (VetNeededException e) {
        }
        ArrayList<ArrayList<String>> scheduleTime = s.getScheduleTime();
        ArrayList<ArrayList<String>> expScheduleTime = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 24; i++) {
            expScheduleTime.add(new ArrayList<String>());
        }
        expScheduleTime.get(10).add("* Testing (0)");
        expScheduleTime.get(10).add("* Testing (1)");
        expScheduleTime.get(19).add("* Feeding - coyote (2: 0, 1)");
        expScheduleTime.get(19).add("* Feeding - porcupine (2: 4, 5)");
        expScheduleTime.get(8).add("* Feeding - beaver (2: 6, 7)");
        expScheduleTime.get(0).add("* Feeding - fox (2: 2, 3)");
        expScheduleTime.get(0).add("* Feeding - raccoon (2: 8, 9)");
        expScheduleTime.get(0).add("* Cage Cleaning - coyote (2: 0, 1)");
        expScheduleTime.get(0).add("* Cage Cleaning - fox (2: 2, 3)");
        expScheduleTime.get(1).add("* Cage Cleaning - porcupine (2: 4, 5)");
        expScheduleTime.get(1).add("* Cage Cleaning - beaver (2: 6, 7)");
        expScheduleTime.get(1).add("* Cage Cleaning - raccoon (2: 8, 9)");

        assertEquals(expScheduleTime, scheduleTime);
    }

    @Test(expected = VolunteerNeededException.class)
    public void testVolunteerException() throws VolunteerNeededException {
        ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
        for (int i = 0; i < 2; i++) {
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        }
        Coyote[] coyotes = new Coyote[2];
        coyoteList.toArray(coyotes);

        ArrayList<Fox> foxList = new ArrayList<Fox>();
        for (int i = 2; i < 4; i++) {
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        }
        Fox[] foxes = new Fox[2];
        foxList.toArray(foxes);

        ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
        for (int i = 4; i < 6; i++) {
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        }
        Porcupine[] porcupines = new Porcupine[2];
        porcupineList.toArray(porcupines);

        ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
        for (int i = 6; i < 8; i++) {
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        }
        Beaver[] beavers = new Beaver[2];
        beaverList.toArray(beavers);

        ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();
        for (int i = 8; i < 10; i++) {
            Raccoon newR = new Raccoon(i, Integer.toString(i));
            raccoonList.add(newR);
        }
        Raccoon[] raccoons = new Raccoon[2];
        raccoonList.toArray(raccoons);

        ArrayList<Task> taskList = new ArrayList<Task>();
        for (int i = 0; i < 8; i++) {
            Task newT = new Task(2, "Testing", 10, 1, 10, i);
            taskList.add(newT);
        }
        Task[] tasks = new Task[8];
        taskList.toArray(tasks);

        Schedule s = new Schedule();
        try {
            s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, raccoons);
        } catch (VetNeededException e) {
        }
        assertNotNull(s);
    }

    @Test
    public void testFindName() {
        ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
        for (int i = 0; i < 2; i++) {
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        }
        Coyote[] coyotes = new Coyote[2];
        coyoteList.toArray(coyotes);

        ArrayList<Fox> foxList = new ArrayList<Fox>();
        for (int i = 2; i < 4; i++) {
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        }
        Fox[] foxes = new Fox[2];
        foxList.toArray(foxes);

        ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
        for (int i = 4; i < 6; i++) {
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        }
        Porcupine[] porcupines = new Porcupine[2];
        porcupineList.toArray(porcupines);

        ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
        for (int i = 6; i < 8; i++) {
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        }
        Beaver[] beavers = new Beaver[2];
        beaverList.toArray(beavers);

        ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();
        for (int i = 8; i < 10; i++) {
            Raccoon newR = new Raccoon(i, Integer.toString(i));
            raccoonList.add(newR);
        }
        Raccoon[] raccoons = new Raccoon[2];
        raccoonList.toArray(raccoons);

        Task task = new Task(2, "Testing", 1, 1, 10, 5);

        Schedule s = new Schedule();

        String expName = "5";
        String name = s.findAnimalName(task, coyotes, foxes, porcupines, beavers, raccoons);
        assertEquals(expName, name);
    }

    @Test
    public void testGetVolunteerHours() {
        ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
        for (int i = 0; i < 2; i++) {
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        }
        Coyote[] coyotes = new Coyote[2];
        coyoteList.toArray(coyotes);

        ArrayList<Fox> foxList = new ArrayList<Fox>();
        for (int i = 2; i < 4; i++) {
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        }
        Fox[] foxes = new Fox[2];
        foxList.toArray(foxes);

        ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
        for (int i = 4; i < 6; i++) {
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        }
        Porcupine[] porcupines = new Porcupine[2];
        porcupineList.toArray(porcupines);

        ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
        for (int i = 6; i < 8; i++) {
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        }
        Beaver[] beavers = new Beaver[2];
        beaverList.toArray(beavers);

        ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();
        for (int i = 8; i < 10; i++) {
            Raccoon newR = new Raccoon(i, Integer.toString(i));
            raccoonList.add(newR);
        }
        Raccoon[] raccoons = new Raccoon[2];
        raccoonList.toArray(raccoons);

        ArrayList<Task> taskList = new ArrayList<Task>();
        for (int i = 0; i < 8; i++) {
            Task newT = new Task(2, "Testing", 10, 1, 10, i);
            taskList.add(newT);
        }
        Task[] tasks = new Task[8];
        taskList.toArray(tasks);

        Schedule s = new Schedule();
        int expHour = 10;
        int hour = 0;
        try {
            s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, raccoons);
        } catch (VolunteerNeededException e) {
            hour = s.getVolunteerHour();

        } catch (VetNeededException e) {
        }
        assertEquals(expHour, hour);
    }

    @Test
    public void testSetTrueVolunteerHours() {
        Schedule s = new Schedule();
        s.setTrueVolunteerHoursByIndex(1);
        boolean passed = s.getTrueVolunteerHours()[1];
        assertTrue(passed);
    }

    @Test
    public void testGetTrueVolunteerHours() {
        Schedule s = new Schedule();
        boolean[] volHours = s.getTrueVolunteerHours();
        boolean[] volunteer = new boolean[24];
        for (int i = 0; i < 24; i++) {
            volunteer[i] = false;
        }
        assertArrayEquals(volunteer, volHours);
    }

    @Test

    /*
     * Tests if the exception was thrown
     */

    public void testVolunteerExceptionFromClient() throws VolunteerNeededException{

        boolean passed = false;

        Client c = new Client();
        try {

            c.buildSchedule();
        } catch (VolunteerNeededException v) {
            passed = true;
        } catch (Exception e) {
        }

        c.close();

        assertTrue("Building the schedule did not throw a volunteer exception", passed);
    }

    @Test

    /*
     * Check if a schedule file was made with correct values
     */

    public void testUploadSchedule() throws VolunteerNeededException, VetNeededException{

        boolean passed = true;
        Client c = new Client();

        try {

            c.buildSchedule();
        } catch (VolunteerNeededException v) {
        } catch (Exception e) {
        }

        c.uploadSchedule();

        File f = new File("..\\380PROJ\\schedule.txt");
        if (!f.exists()) {

            passed = false;

        }

        c.close();
        assertTrue("The schedule was not created", passed);

    }

    @Test
    /*
     * Testing if getters and setters working correctly
     */

    public void testGetSomeAnimals() {

        boolean passed = true;
        Client c = new Client();

        List<Beaver> beavers = Arrays.asList(c.getBeavers());
        List<Porcupine> porcupines = Arrays.asList(c.getPorcupines());
        List<Fox> foxes = Arrays.asList(c.getFoxes());
        List<Raccoon> racoons = Arrays.asList(c.getRaccoons());
        List<Coyote> coyotes = Arrays.asList(c.getCoyotes());

        // See if each array has the right animals:

        Porcupine expectedPorcupine = new Porcupine(8, "Spike");
        Fox expectedFox = new Fox(6, "Annie, Oliver and Mowgli");
        Coyote expectedCoyote = new Coyote(1, "Loner");

        boolean bPass = true;
        boolean pPass = false;
        boolean fPass = false;
        boolean rPass = true;
        boolean cPass = false;

        if (beavers.size() != 0) {
            bPass = false;
        }

        for( Porcupine p : porcupines){
            if (p.getAnimalName().equals(expectedPorcupine.getAnimalName())) {

                pPass = true;
            }

        }

        for( Fox f : foxes){

            if (f.getAnimalName().equals(expectedFox.getAnimalName())) {

                fPass = true;
            }

        }

        
        if (racoons.size() != 0) {

            rPass = false;
        }

        for( Coyote co : coyotes){


            if (co.getAnimalName().equals(expectedCoyote.getAnimalName())) {

                cPass = true;
            }

        }

        c.close();



        assertTrue("The expected data value do not match with the fetched ones", (bPass && pPass && fPass && rPass && cPass));


    }

    @Test
    /*
     * Test if tasks were retrieved and have the correct values
     */
    public void testGetTasks() {

        Client cli = new Client();

        List<Task> taskList = Arrays.asList(cli.getTreatments());

        Task expectedTask1 = new Task(10, "Inspect broken leg", 5, 2, 13, 2);

        Task expectedTask2 = new Task(10, "Inspect broken leg", 5, 2, 13, 14);

        ArrayList<Task> expectedTaskArrL = new ArrayList<Task>();

        expectedTaskArrL.add(expectedTask1);
        expectedTaskArrL.add(expectedTask2);


        ArrayList<Task> gottenTasks = cli.getTasks(10);


        cli.close();

        assertEquals(expectedTaskArrL.get(0).getTaskID(), gottenTasks.get(0).getTaskID());
        assertEquals(expectedTaskArrL.get(0).getDescription(), gottenTasks.get(0).getDescription());
        assertEquals(expectedTaskArrL.get(0).getDuration(), gottenTasks.get(0).getDuration());
        assertEquals(expectedTaskArrL.get(0).getMaxWindow(), gottenTasks.get(0).getMaxWindow());
        assertEquals(expectedTaskArrL.get(0).getStartHour(), gottenTasks.get(0).getStartHour());
        assertEquals(expectedTaskArrL.get(0).getAnimalID(), gottenTasks.get(0).getAnimalID());

        assertEquals(expectedTaskArrL.get(1).getTaskID(), gottenTasks.get(1).getTaskID());
        assertEquals(expectedTaskArrL.get(1).getDescription(), gottenTasks.get(1).getDescription());
        assertEquals(expectedTaskArrL.get(1).getDuration(), gottenTasks.get(1).getDuration());
        assertEquals(expectedTaskArrL.get(1).getMaxWindow(), gottenTasks.get(1).getMaxWindow());
        assertEquals(expectedTaskArrL.get(1).getStartHour(), gottenTasks.get(1).getStartHour());
        assertEquals(expectedTaskArrL.get(1).getAnimalID(), gottenTasks.get(1).getAnimalID());

    }

    /**
     * 
     * Testing the Animal constructor
     */
    @Test
    public void testAnimalConstructor() {
        Animal a = new Animal(1, "raccon");
        assertEquals(1, a.getAnimalID());
        assertEquals("raccon", a.getAnimalName());

    }

    /**
     * Testing the getters and setters
     */
    @Test
    public void testAnimalSetter() {
        Animal a = new Animal(1, "fox");
        a.setAnimalID(10);
        a.setAnimalNickname("calgary");
        assertEquals(10, a.getAnimalID());
        assertEquals("calgary", a.getAnimalName());

        /*
         * check with a null value
         */

        Animal b = new Animal(1, null);
        assertEquals(1, b.getAnimalID());
        assertNull(b.getAnimalName());

        Animal c = new Animal(1, "fox");
        Animal d = new Animal(2, "fox");
        Animal e = new Animal(1, "raccoon");
        assertFalse(c.equals(d));
        assertFalse(c.equals(e));

    }

    /*
     * Beaver animal test
     */
    @Test
    public void testBeaverConstructor() {
        Beaver beaver = new Beaver(1, "jacob");
        assertEquals(1, beaver.getAnimalID());
        assertEquals("jacob", beaver.getAnimalName());
    }

    /*
     * Testing the static methods
     */
    @Test
    public void testBeaverFeedHour() {
        int[] feedHour = Beaver.getFeedHour();
        assertEquals(3, feedHour.length);
        assertEquals(8, feedHour[0]);
        assertEquals(9, feedHour[1]);
        assertEquals(9, feedHour[1]);
        assertEquals(10, feedHour[2]);
        assertEquals(0, Beaver.getFoodPrep());
        assertEquals(5, Beaver.getFeedDuration());
        assertEquals(5, Beaver.getCleanTime());

    }

    /**
     * Checking the inheritance is correctly implemented
     */
    @Test
    public void testBeaverInheritance() {
        Animal a = new Beaver(1, "jacob");
        assertEquals(1, a.getAnimalID());
        assertEquals("jacob", a.getAnimalName());
        assertTrue(a instanceof Beaver);
        assertTrue(a instanceof Animal);

    }

    /**
     * Coyote Animal Test
     */
    @Test
    public void testCoyoteConstructor() {
        Coyote coyote = new Coyote(1, "jacob");
        assertEquals(1, coyote.getAnimalID());
        assertEquals("jacob", coyote.getAnimalName());
    }

    @Test
    public void testCoyoteFeedHour() {
        int[] feedHour = Coyote.getFeedHour();
        assertEquals(3, feedHour.length);
        assertEquals(19, feedHour[0]);
        assertEquals(20, feedHour[1]);
        assertEquals(20, feedHour[1]);
        assertEquals(21, feedHour[2]);
        assertEquals(10, Coyote.getFoodPrep());
        assertEquals(5, Coyote.getFeedDuration());
        assertEquals(5, Coyote.getCleanTime());

    }

    /*
     * Check if inheritance works
     */
    @Test
    public void testCoyoteInheritance() {
        Animal a = new Coyote(1, "jacob");
        assertEquals(1, a.getAnimalID());
        assertEquals("jacob", a.getAnimalName());
        assertTrue(a instanceof Coyote);
        assertTrue(a instanceof Animal);

    }

    /**
     * Testing Fox class
     */
    @Test
    public void testFoxConstructor() {
        Fox fox = new Fox(1, "jacob");
        assertEquals(1, fox.getAnimalID());
        assertEquals("jacob", fox.getAnimalName());
    }

    @Test
    public void testFoxFeedHour() {
        int[] feedHour = Fox.getFeedHour();
        assertEquals(3, feedHour.length);
        assertEquals(0, feedHour[0]);
        assertEquals(1, feedHour[1]);
        assertEquals(1, feedHour[1]);
        assertEquals(2, feedHour[2]);
        assertEquals(5, Fox.getFoodPrep());
        assertEquals(5, Fox.getFeedDuration());
        assertEquals(10, Fox.getCleanTime());

    }

    @Test
    public void testFoxInheritance() {
        Animal a = new Fox(1, "jacob");
        assertEquals(1, a.getAnimalID());
        assertEquals("jacob", a.getAnimalName());
        assertTrue(a instanceof Fox);
        assertTrue(a instanceof Animal);

    }

    /**
     * Porcupine test class
     */
    @Test
    public void testPorcupineConstructor() {
        Porcupine p = new Porcupine(1, "jacob");
        assertEquals(1, p.getAnimalID());
        assertEquals("jacob", p.getAnimalName());
    }

    @Test
    public void testPorcupineFeedHour() {
        int[] feedHour = Porcupine.getFeedHour();
        assertEquals(3, feedHour.length);
        assertEquals(19, feedHour[0]);
        assertEquals(20, feedHour[1]);
        assertEquals(20, feedHour[1]);
        assertEquals(21, feedHour[2]);
        assertEquals(0, Porcupine.getFoodPrep());
        assertEquals(5, Porcupine.getFeedDuration());
        assertEquals(10, Porcupine.getCleanTime());

    }

    /**
     * Checking if inheritance works properly
     */

    @Test
    public void testPorcupineInheritance() {
        Animal a = new Porcupine(1, "jacob");
        assertEquals(1, a.getAnimalID());
        assertEquals("jacob", a.getAnimalName());
        assertTrue(a instanceof Porcupine);
        assertTrue(a instanceof Animal);

    }

    /**
     * Testing raccoon class
     */

    @Test
    public void testRaccoonConstructor() {
        Raccoon raccoon = new Raccoon(1, "jacob");
        assertEquals(1, raccoon.getAnimalID());
        assertEquals("jacob", raccoon.getAnimalName());
    }

    @Test
    public void testRaccoonFeedHour() {
        int[] feedHour = Raccoon.getFeedHour();
        assertEquals(3, feedHour.length);
        assertEquals(0, feedHour[0]);
        assertEquals(1, feedHour[1]);
        assertEquals(1, feedHour[1]);
        assertEquals(2, feedHour[2]);
        assertEquals(0, Raccoon.getFoodPrep());
        assertEquals(5, Raccoon.getFeedDuration());
        assertEquals(5, Raccoon.getCleanTime());

    }

    /*
     * Testin if inheritance works properly
     */
    @Test
    public void testRaccoonInheritance() {
        Animal a = new Porcupine(1, "jacob");
        assertEquals(1, a.getAnimalID());
        assertEquals("jacob", a.getAnimalName());
        assertTrue(a instanceof Porcupine);
        assertTrue(a instanceof Animal);

    }

    /*
     * public void testRacoonConstructor(){
     * Raccoon r = new Raccoon(1, "raccon");
     * assertNull(r);
     * }
     * public void testFoxConstructor(){
     * Fox f = new Fox(1,"fox");
     * assertNull(f);
     * 
     * }
     * public void testPorcupineConstructor(){
     * Porcupine p = new Porcupine(1,"porcupine" );
     * assertNull(p);
     * }
     * public void testBeaverConstructor(){
     * Beaver b = new Beaver(1,"beaver");
     * assertNull(b);
     * 
     * }
     */

}
