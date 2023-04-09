

import java.awt.event.ActionEvent;
import java.beans.Transient;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import org.junit.Test;

import edu.ucalgary.oop.Beaver;
import edu.ucalgary.oop.Client;
import edu.ucalgary.oop.Coyote;
import edu.ucalgary.oop.Fox;
import edu.ucalgary.oop.Porcupine;
import edu.ucalgary.oop.Raccoon;
import edu.ucalgary.oop.Schedule;
import edu.ucalgary.oop.ScheduleGUI;
import edu.ucalgary.oop.Task;
import edu.ucalgary.oop.VolunteerNeededException;

import static org.junit.Assert.*;



public class TestingScheduleBuild {
    private ScheduleGUI scheduleGUI;

    @Before
    public void setUp() {
        JFrame frame = new JFrame();
        scheduleGUI = new ScheduleGUI(new JFrame());
    }
    
    @Test
    public void testConstructor(){
        assertNotNull(scheduleGUI.instructions);
        assertNotNull(scheduleGUI.closingInstructions);
        assertNotNull(scheduleGUI.generateSchedule);

    }

    @Test
    public void testActionPerformed() {
        ActionEvent event = new ActionEvent(new JButton(), 0, "");
        
        scheduleGUI.actionPerformed(event);

        assertThat(scheduleGUI.getCurrentTaskOption(), null);
    }

    @Test 
    public void testClientInitialization() {
        ScheduleGUI gui = new ScheduleGUI(new JFrame());
        Client client = gui.getClient();
        assertThat(client, is(notNullValue()));
    }

    public ScheduleTest() {
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
		for(int i = 0; i < 2; i++){
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        } 
        coyotes = coyoteList.toArray();
		
		ArrayList<Fox> foxList = new ArrayList<Fox>();
		for(int i = 2; i < 4; i++){
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        } 
        foxes = foxList.toArray();
		
		ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
		for(int i = 4; i < 6; i++){
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        } 
        porcupines = porcupineList.toArray();
		
		ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
		for(int i = 6; i < 8; i++){
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        } 
        beavers = beaverList.toArray();
		
		ArrayList<Racoon> racoonList = new ArrayList<Racoon>();
		for(int i = 8; i < 10; i++){
            Racoon newR = new Racoon(i, Integer.toString(i));
            racoonList.add(newR);
        } 
        racoons = racoonList.toArray();
		
		ArrayList<Task> taskList = new ArrayList<Task>();
		for(int i = 0; i < 8; i++){
            Task newT = new Task(2, "Testing", 1, 1, i, i);
            taskList.add(newT);
        } 
        tasks = taskList.toArray();
		
		boolean[] volunteer = [false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false];
		
		Schedule s = new Schedule();
		try{
			s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, racoons, volunteer);
		}
		catch(VolunteerNeededException e){
			failed = true;
		}
		assertFalse(failed);
	}
	
	
	@Test
	public void testGetScheduleTime {
		ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
		for(int i = 0; i < 2; i++){
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        } 
        coyotes = coyoteList.toArray();
		
		ArrayList<Fox> foxList = new ArrayList<Fox>();
		for(int i = 2; i < 4; i++){
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        } 
        foxes = foxList.toArray();
		
		ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
		for(int i = 4; i < 6; i++){
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        } 
        porcupines = porcupineList.toArray();
		
		ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
		for(int i = 6; i < 8; i++){
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        } 
        beavers = beaverList.toArray();
		
		ArrayList<Racoon> racoonList = new ArrayList<Racoon>();
		for(int i = 8; i < 10; i++){
            Racoon newR = new Racoon(i, Integer.toString(i));
            racoonList.add(newR);
        } 
        racoons = racoonList.toArray();
		
		ArrayList<Task> taskList = new ArrayList<Task>();
		for(int i = 0; i < 8; i++){
            Task newT = new Task(2, "Testing", 1, 1, 10, i);
            taskList.add(newT);
        } 
        tasks = taskList.toArray();
		
		boolean[] volunteer = [false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false];
		
		Schedule s = new Schedule();
		s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, racoons, volunteer);
		
		ArrayList<String>[] scheduleTime = s.getScheduleTime();
		ArrayList<String>[] expScheduleTime = new ArrayList[24]();
		for(int i = 0; i < this.scheduleTime.length; i++){
			expScheduleTime[i] = new ArrayList<String>();
        }
		expScheduleTime[10].add("Testing (0, 1, 2, 3, 4, 5, 6, 7)");
		expScheduleTime[19].add("Feeding - coyote (2: 0, 1)");
		expScheduleTime[0].add("Feeding - fox (2: 2, 3)");
		expScheduleTime[19].add("Feeding - porcupine (2: 4, 5)");
		expScheduleTime[8].add("Feeding - beaver (2: 6, 7)");
		expScheduleTime[0].add("Feeding - racoon (2: 8, 9)");
		expScheduleTime[0].add("Cage Cleaning - coyote (2: 0, 1)");
		expScheduleTime[0].add("Cage Cleaning - fox (2: 2, 3)");
		expScheduleTime[0].add("Cage Cleaning - beaver (1: 6)");
		expScheduleTime[1].add("Cage Cleaning - porcupine (2: 4, 5)");
		expScheduleTime[1].add("Cage Cleaning - beaver (1: 7)");
		expScheduleTime[1].add("Cage Cleaning - racoon (2: 8, 9)");
		
		assertEquals(expScheduleTime, scheduleTime);
	}
	
	@Test (expected = VolunteerException.class)
	public void testVolunteerException() {
		ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
		for(int i = 0; i < 5; i++){
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        } 
        coyotes = coyoteList.toArray();
		
		ArrayList<Fox> foxList = new ArrayList<Fox>();
		for(int i = 5; i < 10; i++){
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        } 
        foxes = foxList.toArray();
		
		ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
		for(int i = 10; i < 15; i++){
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        } 
        porcupines = porcupineList.toArray();
		
		ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
		for(int i = 15; i < 20; i++){
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        } 
        beavers = beaverList.toArray();
		
		ArrayList<Racoon> racoonList = new ArrayList<Racoon>();
		for(int i = 20; i < 25; i++){
            Racoon newR = new Racoon(i, Integer.toString(i));
            racoonList.add(newR);
        } 
        racoons = racoonList.toArray();
		
		ArrayList<Task> taskList = new ArrayList<Task>();
		for(int i = 0; i < 24; i++){
            Task newT = new Task(2, "Testing", 20, 1, 10, i);
            taskList.add(newT);
        } 
        tasks = taskList.toArray();
		
		boolean[] volunteer = [false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false];
		
		Schedule s = new Schedule();
		s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, racoons, volunteer);
		assertNotNull(s);
	}
	
	@Test
	public void testFindName() {
		ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
		for(int i = 0; i < 5; i++){
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        } 
        coyotes = coyoteList.toArray();
		
		ArrayList<Fox> foxList = new ArrayList<Fox>();
		for(int i = 5; i < 10; i++){
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        } 
        foxes = foxList.toArray();
		
		ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
		for(int i = 10; i < 15; i++){
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        } 
        porcupines = porcupineList.toArray();
		
		ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
		for(int i = 15; i < 20; i++){
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        } 
        beavers = beaverList.toArray();
		
		ArrayList<Racoon> racoonList = new ArrayList<Racoon>();
		for(int i = 20; i < 25; i++){
            Racoon newR = new Racoon(i, Integer.toString(i));
            racoonList.add(newR);
        } 
        racoons = racoonList.toArray();
		
        Task task = new Task(2, "Testing", 1, 1, 10, "5");
		
		Schedule s = new Schedule();
		
		String expName = "5";
		String name = s.findAnimalName(task, coyotes, foxes, porcupines, beavers, racoons);
		assertEquals(expName, name);
	}
	
	@Test
	public void testGetVolunteerHours() {
		ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
		for(int i = 0; i < 5; i++){
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        } 
        coyotes = coyoteList.toArray();
		
		ArrayList<Fox> foxList = new ArrayList<Fox>();
		for(int i = 5; i < 10; i++){
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        } 
        foxes = foxList.toArray();
		
		ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
		for(int i = 10; i < 15; i++){
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        } 
        porcupines = porcupineList.toArray();
		
		ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
		for(int i = 15; i < 20; i++){
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        } 
        beavers = beaverList.toArray();
		
		ArrayList<Racoon> racoonList = new ArrayList<Racoon>();
		for(int i = 20; i < 25; i++){
            Racoon newR = new Racoon(i, Integer.toString(i));
            racoonList.add(newR);
        } 
        racoons = racoonList.toArray();
		
        ArrayList<Task> taskList = new ArrayList<Task>();
		for(int i = 0; i < 24; i++){
            Task newT = new Task(2, "Testing", 20, 1, 10, i);
            taskList.add(newT);
        } 
        tasks = taskList.toArray();
		
		boolean[] volunteer = [false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false];
		
		Schedule s = new Schedule();
		try{
			s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, racoons, volunteer);
		}
		catch(VolunteerException e){
			ArrayList<Integer> hours = s.getVolunteerHours();
			ArrayList<Integer> expHours = ArrayList<Integer>(10);
		}
		assertEquals(expHours, hours);
	}

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
    /**
     * 
     * Testing the Animal constructor
     */
    @Test
    public void testAnimalConstructor(){
        Animal a = new Animal(1,"raccon");
        assertEquals(1, a.getAnimalID());
        assertEquals("raccon", a.getAnimalName());

    }
    /**
     * Testing the getters and setters
     */
    @Test
    public void testAnimalSetter(){
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
    public void testBeaverConstructor(){
        Beaver beaver = new Beaver(1, "jacob");
        assertEquals(1, beaver.getAnimalID());
        assertEquals("jacob", beaver.getAnimalName());
    }
    /*
     * Testing the static methods
     */
    @Test
    public void testBeaverFeedHour(){
        int [] feedHour = Beaver.getFeedHour();
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
    public void testBeaverInheritance(){
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
    public void testCoyoteConstructor(){
        Coyote coyote = new Coyote(1, "jacob");
        assertEquals(1, coyote.getAnimalID());
        assertEquals("jacob", coyote.getAnimalName());
    }
    @Test
    public void testCoyoteFeedHour(){
        int [] feedHour = Coyote.getFeedHour();
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
    public void testCoyoteInheritance(){
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
    public void testFoxConstructor(){
        Fox fox = new Fox(1, "jacob");
        assertEquals(1, fox.getAnimalID());
        assertEquals("jacob", fox.getAnimalName());
    }
    @Test
    public void testFoxFeedHour(){
        int [] feedHour = Fox.getFeedHour();
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
    public void testFoxInheritance(){
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
    public void testPorcupineConstructor(){
        Porcupine p= new Porcupine(1, "jacob");
        assertEquals(1, p.getAnimalID());
        assertEquals("jacob", p.getAnimalName());
    }
    @Test
    public void testPorcupineFeedHour(){
        int [] feedHour = Porcupine.getFeedHour();
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
    public void testPorcupineInheritance(){
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
    public void testRaccoonConstructor(){
        Raccoon raccoon = new Raccoon(1, "jacob");
        assertEquals(1, raccoon.getAnimalID());
        assertEquals("jacob", raccoon.getAnimalName());
    }
    @Test
    public void testRaccoonFeedHour(){
        int [] feedHour = Raccoon.getFeedHour();
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
    public void testRaccoonInheritance(){
        Animal a = new Porcupine(1, "jacob");
        assertEquals(1, a.getAnimalID());
        assertEquals("jacob", a.getAnimalName());
        assertTrue(a instanceof Porcupine);
        assertTrue(a instanceof Animal);

    }


    
    
    


    /*public void testRacoonConstructor(){
        Raccoon r = new Raccoon(1, "raccon");
        assertNull(r);  
    }
    public void testFoxConstructor(){
        Fox f = new Fox(1,"fox");
        assertNull(f);

    }
    public void testPorcupineConstructor(){
        Porcupine p = new Porcupine(1,"porcupine" );
        assertNull(p);
    }
    public void testBeaverConstructor(){
        Beaver b = new Beaver(1,"beaver");
        assertNull(b);

    }*/



}
