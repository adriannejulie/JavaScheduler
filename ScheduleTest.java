


import java.util.*;
import org.junit.Test;

import edu.ucalgary.oop.Beaver;
import edu.ucalgary.oop.Coyote;
import edu.ucalgary.oop.Fox;
import edu.ucalgary.oop.Porcupine;
import edu.ucalgary.oop.Schedule;
import edu.ucalgary.oop.Task;

import static org.junit.Assert.*;

public class ScheduleTest{

	public ScheduleTest() {
	}
	
	@Test
	public void testScheduleConstructor() {
		Schedule s = new Schedule();
		assertNotNull(s);
	}
	
	@Test
	public void testScheduleBuildValid {
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
	
}