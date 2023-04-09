package edu.ucalgary.oop;


import java.util.*;
import org.junit.Test;

import edu.ucalgary.oop.Beaver;
import edu.ucalgary.oop.Coyote;
import edu.ucalgary.oop.Fox;
import edu.ucalgary.oop.Porcupine;
import edu.ucalgary.oop.Raccoon;
import edu.ucalgary.oop.Schedule;
import edu.ucalgary.oop.Task;
import edu.ucalgary.oop.VolunteerNeededException;
import edu.ucalgary.oop.VetNeededException;

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
	public void testScheduleBuildValid() {
		boolean failed = false;
		
		ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
		for(int i = 0; i < 2; i++){
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        } 
        Coyote[] coyotes = new Coyote[2];
		coyoteList.toArray(coyotes);
		
		ArrayList<Fox> foxList = new ArrayList<Fox>();
		for(int i = 2; i < 4; i++){
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        } 
        Fox[] foxes = new Fox[2];
		foxList.toArray(foxes);
		
		ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
		for(int i = 4; i < 6; i++){
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        } 
        Porcupine[] porcupines = new Porcupine[2];
		porcupineList.toArray(porcupines);
		
		ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
		for(int i = 6; i < 8; i++){
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        } 
        Beaver[] beavers = new Beaver[2];
		beaverList.toArray(beavers);
		
		ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();
		for(int i = 8; i < 10; i++){
            Raccoon newR = new Raccoon(i, Integer.toString(i));
            raccoonList.add(newR);
        } 
        Raccoon[] raccoons = new Raccoon[2];
		raccoonList.toArray(raccoons);
		
		ArrayList<Task> taskList = new ArrayList<Task>();
		for(int i = 0; i < 8; i++){
            Task newT = new Task(2, "Testing", 1, 1, i, i);
            taskList.add(newT);
        } 
        Task[] tasks = new Task[8];
		taskList.toArray(tasks);

		Schedule s = new Schedule();
		try{
			s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, raccoons);
		}
		catch(VolunteerNeededException e){
			failed = true;
		}
		catch(VetNeededException e){}
		assertFalse(failed);
	}
	
	
	@Test
	public void testGetScheduleTime () {
		ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
		for(int i = 0; i < 2; i++){
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        } 
        Coyote[] coyotes = new Coyote[2];
		coyoteList.toArray(coyotes);
		
		ArrayList<Fox> foxList = new ArrayList<Fox>();
		for(int i = 2; i < 4; i++){
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        } 
        Fox[] foxes = new Fox[2];
		foxList.toArray(foxes);
		
		ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
		for(int i = 4; i < 6; i++){
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        } 
        Porcupine[] porcupines = new Porcupine[2];
		porcupineList.toArray(porcupines);
		
		ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
		for(int i = 6; i < 8; i++){
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        } 
        Beaver[] beavers = new Beaver[2];
		beaverList.toArray(beavers);
		
		ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();
		for(int i = 8; i < 10; i++){
            Raccoon newR = new Raccoon(i, Integer.toString(i));
            raccoonList.add(newR);
        } 
        Raccoon[] raccoons = new Raccoon[2];
		raccoonList.toArray(raccoons);
		
		ArrayList<Task> taskList = new ArrayList<Task>();
		for(int i = 0; i < 2; i++){
            Task newT = new Task(2, "Testing", 1, 1, 10, i);
            taskList.add(newT);
        } 
        Task[] tasks = new Task[2];
		taskList.toArray(tasks);
		
		Schedule s = new Schedule();
		try{
			s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, raccoons);
		}
		catch(VolunteerNeededException e){}
		catch(VetNeededException e){}
		ArrayList<ArrayList<String>> scheduleTime = s.getScheduleTime();
		ArrayList<ArrayList<String>> expScheduleTime = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < 24; i++){
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
	
	@Test (expected = VolunteerNeededException.class)
	public void testVolunteerException() throws VolunteerNeededException{
		ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
		for(int i = 0; i < 2; i++){
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        } 
        Coyote[] coyotes = new Coyote[2];
		coyoteList.toArray(coyotes);
		
		ArrayList<Fox> foxList = new ArrayList<Fox>();
		for(int i = 2; i < 4; i++){
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        } 
        Fox[] foxes = new Fox[2];
		foxList.toArray(foxes);
		
		ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
		for(int i = 4; i < 6; i++){
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        } 
        Porcupine[] porcupines = new Porcupine[2];
		porcupineList.toArray(porcupines);
		
		ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
		for(int i = 6; i < 8; i++){
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        } 
        Beaver[] beavers = new Beaver[2];
		beaverList.toArray(beavers);
		
		ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();
		for(int i = 8; i < 10; i++){
            Raccoon newR = new Raccoon(i, Integer.toString(i));
            raccoonList.add(newR);
        } 
        Raccoon[] raccoons = new Raccoon[2];
		raccoonList.toArray(raccoons);
		
		ArrayList<Task> taskList = new ArrayList<Task>();
		for(int i = 0; i < 8; i++){
            Task newT = new Task(2, "Testing", 10, 1, 10, i);
            taskList.add(newT);
        } 
        Task[] tasks = new Task[8];
		taskList.toArray(tasks);

		Schedule s = new Schedule();
		try{
			s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, raccoons);
		}
		catch(VetNeededException e){}
		assertNotNull(s);
	}
	
	@Test
	public void testFindName() {
		ArrayList<Coyote> coyoteList = new ArrayList<Coyote>();
		for(int i = 0; i < 2; i++){
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        } 
        Coyote[] coyotes = new Coyote[2];
		coyoteList.toArray(coyotes);
		
		ArrayList<Fox> foxList = new ArrayList<Fox>();
		for(int i = 2; i < 4; i++){
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        } 
        Fox[] foxes = new Fox[2];
		foxList.toArray(foxes);
		
		ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
		for(int i = 4; i < 6; i++){
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        } 
        Porcupine[] porcupines = new Porcupine[2];
		porcupineList.toArray(porcupines);
		
		ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
		for(int i = 6; i < 8; i++){
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        } 
        Beaver[] beavers = new Beaver[2];
		beaverList.toArray(beavers);
		
		ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();
		for(int i = 8; i < 10; i++){
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
		for(int i = 0; i < 2; i++){
            Coyote newC = new Coyote(i, Integer.toString(i));
            coyoteList.add(newC);
        } 
        Coyote[] coyotes = new Coyote[2];
		coyoteList.toArray(coyotes);
		
		ArrayList<Fox> foxList = new ArrayList<Fox>();
		for(int i = 2; i < 4; i++){
            Fox newF = new Fox(i, Integer.toString(i));
            foxList.add(newF);
        } 
        Fox[] foxes = new Fox[2];
		foxList.toArray(foxes);
		
		ArrayList<Porcupine> porcupineList = new ArrayList<Porcupine>();
		for(int i = 4; i < 6; i++){
            Porcupine newP = new Porcupine(i, Integer.toString(i));
            porcupineList.add(newP);
        } 
        Porcupine[] porcupines = new Porcupine[2];
		porcupineList.toArray(porcupines);
		
		ArrayList<Beaver> beaverList = new ArrayList<Beaver>();
		for(int i = 6; i < 8; i++){
            Beaver newB = new Beaver(i, Integer.toString(i));
            beaverList.add(newB);
        } 
        Beaver[] beavers = new Beaver[2];
		beaverList.toArray(beavers);
		
		ArrayList<Raccoon> raccoonList = new ArrayList<Raccoon>();
		for(int i = 8; i < 10; i++){
            Raccoon newR = new Raccoon(i, Integer.toString(i));
            raccoonList.add(newR);
        } 
        Raccoon[] raccoons = new Raccoon[2];
		raccoonList.toArray(raccoons);
		
		ArrayList<Task> taskList = new ArrayList<Task>();
		for(int i = 0; i < 8; i++){
            Task newT = new Task(2, "Testing", 10, 1, 10, i);
            taskList.add(newT);
        } 
        Task[] tasks = new Task[8];
		taskList.toArray(tasks);
		
		Schedule s = new Schedule();
		int expHour = 10;
		int hour = 0;
		try{
			s.buildSchedule(tasks, coyotes, foxes, porcupines, beavers, raccoons);
		}
		catch(VolunteerNeededException e){
			hour = s.getVolunteerHour();
			
		}
		catch(VetNeededException e){}
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
		for(int i = 0; i < 24; i++){
			volunteer[i] = false;
		}
		assertArrayEquals(volunteer, volHours);
	}
	
}