package edu.ucalgary.oop;
/**
@author Jordan Vanbeselaere
jordan.vanbeselaere@ucalgary.ca
@version 1.0
@since 1.0
*/

import java.util.*;

public class Schedule {
    private ArrayList<String>[] scheduleTime;
	private int[] hourTimes;
	private ArrayList<Integer> volunteerHours;
	private int coyoteNumber = 0;
	private int foxNumber = 0;
	private int porcupineNumber = 0;
	private int beaverNumber = 0;
	private int racoonNumber = 0;
	private int coyoteCages = 0;
	private int foxCages = 0;
	private int porcupineCages = 0;
	private int beaverCages = 0;
	private int racoonCages = 0;

    /**
     * constructs a new Schedule object with empty array's/ArrayList's 
	 * to enable refence to the object when other methods throw errors.
	 * 
     */
    public Schedule() {
		this.hourTimes = new int[24];
        this.scheduleTime = new ArrayList[24];
		this.volunteerHours = new ArrayList<Integer>();
		for(int i = 0; i < this.scheduleTime.length; i++){
			this.scheduleTime[i] = new ArrayList<String>();
			this.hourTimes[i] = 60;
        }
    }

	/**
     * sets the value of the internal ArrayList<String>'s of the 
	 * scheduleTime attribute, while maintaining that the collection
	 * of tasks doesn't exceed 1 hour. Each hour prioritizes tasks 
	 * within the passed Task[], followed by feeding time for each
	 * type of animal, followed lastly by the cage cleaning time 
	 * for each type of animal
	 * 
	 * @param tasks the array of prioritized tasks
	 * @param coyotes an array of Coyote objects within the system
	 * @param foxes an array of Fox objects within the system
	 * @param porcupines an array of Porcupine objects within the system
	 * @param beavers an array of Beaver objects within the system
	 * @param racoons an array of Racoon objects within the system
     */
    public void buildSchedule(Task[] tasks, Coyote[] coyotes, Fox[] foxes, Porcupine[] porcupines, Beaver[] beavers, Racoon[] racoons, boolean[] trueVolunteerHours) throws VolunteerNeededException{
		this.hourTimes = new int[24];
        this.scheduleTime = new ArrayList[24];
		this.volunteerHours = new ArrayList<Integer>();
		for(int i = 0; i < this.scheduleTime.length; i++){
			this.scheduleTime[i] = new ArrayList<String>();
			this.hourTimes[i] = 60;
        }
		this.findNumberOfAnimals(coyotes, foxes, porcupines, beavers, racoons);
		this.coyoteCages = this.coyoteNumber;
		this.foxCages = this.foxNumber;
		this.porcupineCages = this.porcupineNumber;
		this.beaverCages = this.beaverNumber;
		this.racoonCages = this.racoonNumber;
        for(int i = 0; i < this.scheduleTime.length; i++){
			for(int l = 0; l < 24; l++){
				if(trueVolunteerHours[l] == true){
					this.hourTimes[i] = this.hourTimes[i] + 60;
				}
			}
			for(Task j:tasks){
				if(j.getStartHour() == i){
					String animalName = this.findAnimalName(j, coyotes, foxes, porcupines, beavers, racoons);
					this.hourTimes[i] = this.hourTimes[i] - j.getDuration();
					this.scheduleTime[i].add(j.getDescription + " (" + animalName + ")");
					if(j.getTaskID() == 1){
						int idx = 0;
						Coyote[] newCoyotes = new Coyote[coyotes.length - 1];
						for(int k = 0; k < coyotes.length; k++){
							if(j.getAnimalID != coyotes[k].getAnimalID){
								newCoyotes[idx] = coyotes[k];
								idx++;
							}
						}
						coyotes = newCoyotes;
					}
				}
			}
			if(this.hourTimes[i] < 0){
				this.volunteerHours.add(i);
			}
			while(this.hourTimes[i] > 0){
				int fedCoyotes = 0;
				int[] coyoteFeedingTimes = Coyote.getFeedHour();
				if(i == coyoteFeedingTimes[0] || i == coyoteFeedingTimes[1] || i == coyoteFeedingTimes[2]){
					if(this.hourTimes[i] > (Coyote.getFoodPrep() + Coyote.getFeedDuration() && this.coyoteNumber > 0)){
						this.hourTimes[i] = this.hourTimes[i] - Coyote.getFoodPrep();
						while((hourTimes[i] - Coyote.getFeedDuration()) > 0 && this.coyoteNumber > 0){
							this.coyoteNumber = this.coyoteNumber - 1;
							this.hourTimes[i] = this.hourTimes[i] - Coyote.getFeedDuration();
							fedCoyotes = fedCoyotes + 1;
						}
						StringBuilder coyoteNames = new StringBuilder();
						for(int k = 0; k <= fedCoyotes; k++){
							coyoteNames.append(coyotes[k].getAnimalName()).append(", ");
							if(k != fedCoyotes){
								coyoteNames.append(", ");
							}
						}
						this.scheduleTime[i].add("Feeding - coyote (" + fedCoyotes + ": " + coyoteNames.toString() + ")");
					}
				}
				int fedFoxes = 0;
				int[] foxFeedingTimes = Fox.getFeedHour();
				if(i == foxFeedingTimes[0] || i == foxFeedingTimes[1] || i == foxFeedingTimes[2]){
					if(this.hourTimes[i] > (Fox.getFoodPrep() + Fox.getFeedDuration()) && this.foxNumber > 0){
						this.hourTimes[i] = this.hourTimes[i] - Fox.getFoodPrep();
						while((hourTimes[i] - Fox.getFeedDuration()) > 0 && this.foxNumber > 0){
							this.foxNumber = this.foxNumber - 1;
							this.hourTimes[i] = this.hourTimes[i] - Fox.getFeedDuration();
							fedFoxes = fedFoxes + 1;
						}
						StringBuilder foxNames = new StringBuilder();
						for(int k = 0; k <= fedFoxes; k++){
							foxNames.append(foxes[k].getAnimalName());
							if(k != fedFoxes){
								foxNames.append(", ");
							}
						}
						this.scheduleTime[i].add("Feeding - fox (" + fedFoxes + ": " + foxNames.toString() + ")");
					}
				}
				int fedPorcupines = 0;
				int[] porcupineFeedingTimes = Porcupine.getFeedHour();
				if(i == porcupineFeedingTimes[0] || i == porcupineFeedingTimes[1] || i == porcupineFeedingTimes[2]){
					if(this.hourTimes[i] > (Porcupine.getFoodPrep() + Porcupine.getFeedDuration()) && this.porcupineNumber > 0){
						this.hourTimes[i] = this.hourTimes[i] - Porcupine.getFoodPrep();
						while((hourTimes[i] - Porcupine.getFeedDuration()) > 0 && this.porcupineNumber > 0){
							this.porcupineNumber = this.porcupineNumber - 1;
							this.hourTimes[i] = this.hourTimes[i] - Porcupine.getFeedDuration();
							fedPorcupines = fedPorcupines + 1;
						}
						StringBuilder porcupineNames = new StringBuilder();
						for(int k = 0; k <= fedPorcupines; k++){
							porcupineNames.append(porcupines[k].getAnimalName());
							if(k != fedPorcupines){
								porcupineNames.append(", ");
							}
						}
						this.scheduleTime[i].add("Feeding - porcupine (" + fedPorcupines + ": " + porcupineNames.toString() + ")");
					}
				}
				int fedBeavers = 0;
				int[] beaverFeedingTimes = Beaver.getFeedHour();
				if(i == beaverFeedingTimes[0] || i == beaverFeedingTimes[1] || i == beaverFeedingTimes[2]){
					if(this.hourTimes[i] > (Beaver.getFoodPrep() + Beaver.getFeedDuration()) && this.beaverNumber > 0){
						this.hourTimes[i] = this.hourTimes[i] - Beaver.getFoodPrep();
						while((hourTimes[i] - Beaver.getFeedDuration()) > 0 && this.beaverNumber > 0){
							this.beaverNumber = this.beaverNumber - 1;
							this.hourTimes[i] = this.hourTimes[i] - Beaver.getFeedDuration();
							fedBeavers = fedBeavers + 1;
						}
						StringBuilder beaverNames = new StringBuilder();
						for(int k = 0; k <= fedBeavers; k++){
							beaverNames.append(beavers[k].getAnimalName());
							if(k != fedBeavers){
								beaverNames.append(", ");
							}
						}
						this.scheduleTime[i].add("Feeding - beaver (" + fedBeavers + ": " + beaverNames.toString() + ")");
					}
				}
				int fedRacoons = 0;
				int[] racoonFeedingTimes = Racoon.getFeedHour();
				if(i == racoonFeedingTimes[0] || i == racoonFeedingTimes[1] || i == racoonFeedingTimes[2]){
					if(this.hourTimes[i] > (Racoon.getFoodPrep() + Racoon.getFeedDuration()) && this.racoonNumber > 0){
						this.hourTimes[i] = this.hourTimes[i] - Racoon.getFoodPrep();
						while((hourTimes[i] - Racoon.getFeedDuration()) > 0 && this.racoonNumber > 0){
							this.racoonNumber = this.racoonNumber - 1;
							this.hourTimes[i] = this.hourTimes[i] - Racoon.getFeedDuration();
							fedRacoons = fedRacoons + 1;
						}
						StringBuilder racoonNames = new StringBuilder();
						for(int k = 0; k <= fedRacoons; k++){
							racoonNames.append(racoons[k].getAnimalName());
							if(k != fedRacoons){
								racoonNames.append(", ");
							}
						}
						this.scheduleTime[i].add("Feeding - racoon (" + fedRacoons + ": " + racoonNames.toString() + ")");
					}
				}
				if(this.hourTimes[i] > Coyote.getCleanTime()){
					int coyoteCageNum = 0;
					while((hourTimes[i] - Coyote.getCleanTime()) > 0 && this.coyoteCages > 0){
						this.coyoteCages = this.coyoteCages - 1;
						this.hourTimes[i] = this.hourTimes[i] - Coyote.getCleanTime();
						coyoteCageNum = coyoteCageNum + 1;
					}
					StringBuilder coyoteCleaned = new StringBuilder();
					for(int k = 0; k <= coyoteCageNum; k++){
						coyoteCleaned.append(coyotes[k].getAnimalName());
						if(k != coyoteCageNum){
							coyoteCleaned.append(", ");
						}
					}
					this.scheduleTime[i].add("Cage Cleaning - coyote (" + coyoteCageNum + ": " + coyoteCleaned.toString() + ")");
				}
				if(this.hourTimes[i] > Fox.getCleanTime()){
					int foxCageNum = 0;
					while((hourTimes[i] - Fox.getCleanTime()) > 0 && this.foxCages > 0){
						this.foxCages = this.foxCages - 1;
						this.hourTimes[i] = this.hourTimes[i] - Fox.getCleanTime();
						foxCageNum = foxCageNum + 1;
					}
					StringBuilder foxCleaned = new StringBuilder();
					for(int k = 0; k <= foxCageNum; k++){
						foxCleaned.append(foxes[k].getAnimalName());
						if(k != foxCageNum){
							foxCleaned.append(", ");
						}
					}
					this.scheduleTime[i].add("Cage Cleaning - fox (" + foxCageNum + ": " + foxCleaned.toString() + ")");
				}
				if(this.hourTimes[i] > Porcupine.getCleanTime()){
					int porcupineCageNum = 0;
					while((hourTimes[i] - Porcupine.getCleanTime()) > 0 && this.porcupineCages > 0){
						this.porcupineCages = this.porcupineCages - 1;
						this.hourTimes[i] = this.hourTimes[i] - Porcupine.getCleanTime();
						porcupineCageNum = porcupineCageNum + 1;
					}
					StringBuilder porcupineCleaned = new StringBuilder();
					for(int k = 0; k <= porcupineCageNum; k++){
						porcupineCleaned.append(porcupines[k].getAnimalName());
						if(k != porcupineCageNum){
							porcupineCleaned.append(", ");
						}
					}
					this.scheduleTime[i].add("Cage Cleaning - porcupine (" + porcupineCageNum + ": " + porcupineCleaned.toString() + ")");
				}
				if(this.hourTimes[i] > Beaver.getCleanTime()){
					int beaverCageNum = 0;
					while((hourTimes[i] - Beaver.getCleanTime()) > 0 && this.beaverCages > 0){
						this.beaverCages = this.beaverCages - 1;
						this.hourTimes[i] = this.hourTimes[i] - Beaver.getCleanTime();
						beaverCageNum = beaverCageNum + 1;
					}
					StringBuilder beaverCleaned = new StringBuilder();
					for(int k = 0; k <= beaverCageNum; k++){
						beaverCleaned.append(beavers[k].getAnimalName());
						if(k != beaverCageNum){
							beaverCleaned.append(", ");
						}
					}
					this.scheduleTime[i].add("Cage Cleaning - beaver (" + beaverCageNum + ": " + beaverCleaned.toString() + ")");
				}
				if(this.hourTimes[i] > Racoon.getCleanTime()){
					int racoonCageNum = 0;
					while((hourTimes[i] - Racoon.getCleanTime()) > 0 && this.racoonCages > 0){
						this.racoonCages = this.racoonCages - 1;
						this.hourTimes[i] = this.hourTimes[i] - Racoon.getCleanTime();
						racoonCageNum = racoonCageNum + 1;
					}
					StringBuilder racoonCleaned = new StringBuilder();
					for(int k = 0; k <= racoonCageNum; k++){
						racoonCleaned.append(racoons[k].getAnimalName());
						if(k != racoonCageNum){
							racoonCleaned.append(", ");
						}
					}
					this.scheduleTime[i].add("Cage Cleaning - racoon (" + racoonCageNum + ": " + racoonCleaned.toString() + ")");
				}
			}
        }
		if(!this.volunteerHours.isEmpty()){
			throw new VolunteerNeededException();
		}
    }
	 
	 /**
     * takes a Task object and arrays of each animal type
	 * and returns the name of the animal attached to that task
	 * 
	 * @param j the Task object on which to find animal name attached
	 * @param coyotes an array of Coyote objects within the system
	 * @param foxes an array of Fox objects within the system
	 * @param porcupines an array of Porcupine objects within the system
	 * @param beavers an array of Beaver objects within the system
	 * @param racoons an array of Racoon objects within the system
	 * @return the name of the animal attached to the entered task
     */
	public String findAnimalName(Task j, Coyote[] coyotes, Fox[] foxes, Porcupine[] porcupines, Beaver[] beavers, Racoon[] racoons){
		String animalName = "";
		for(Coyote coyote:coyotes){
			if(j.getAnimalID() == coyote.getAnimalID()){
				animalName = coyote.getAnimalName();
			}
		}
		for(Fox fox:foxes){
			if(j.getAnimalID() == fox.getAnimalID()){
				animalName = fox.getAnimalName();
			}
		}
		for(Porcupine porcupine:porcupines){
			if(j.getAnimalID() == porcupine.getAnimalID()){
				animalName = porcupine.getAnimalName();
			}
		}
		for(Beaver beaver:beavers){
			if(j.getAnimalID() == beaver.getAnimalID()){
				animalName = beaver.getAnimalName();
			}
		}
		for(Racoon racoon:racoons){
			if(j.getAnimalID() == racoon.getAnimalID()){
				animalName = racoon.getAnimalName();
			}
		}
		return animalName;
	}
	
	 /**
     * Calculates the total number of each animal type within
	 * the system and stores the value within its own private
	 * variables.
	 * 
	 * @param coyotes an array of Coyote objects within the system
	 * @param foxes an array of Fox objects within the system
	 * @param porcupines an array of Porcupine objects within the system
	 * @param beavers an array of Beaver objects within the system
	 * @param racoons an array of Racoon objects within the system
     */
	public void findNumberOfAnimals(Coyote[] coyotes, Fox[] foxes, Porcupine[] porcupines, Beaver[] beavers, Racoon[] racoons){
		int coyotesNum = 0;
		int foxesNum = 0;
		int porcupinesNum = 0;
		int beaversNum = 0;
		int racoonsNum = 0;
		for(Coyote coyote:coyotes){
			coyotesNum = coyotesNum + 1;
		}
		for(Fox fox:foxes){
			foxesNum = foxesNum + 1;
		}
		for(Porcupine porcupine:porcupines){
			porcupinesNum = porcupinesNum + 1;
		}
		for(Beaver beaver:beavers){
			beaversNum = beaversNum + 1;
		}
		for(Racoon racoon:racoons){
			racoonsNum = racoonsNum + 1;
		}
		this.coyoteNumber = coyotesNum;
		this.foxNumber = foxesNum;
		this.porcupineNumber = porcupinesNum;
		this.beaverNumber = beaversNum;
		this.racoonNumber = racoonsNum;
	}
	 /**
     * returns the private ArrayList<Integer> volunteerHours
	 * 
	 * @return the ArrayList<Integer> volunteerHours of the Schedule object
     */
	public ArrayList<Integer> getVolunteerHours(){
		return this.volunteerHours;
	}
	
	/**
     * returns the private ArrayList<String>[] scheduleTime
	 * 
	 * @return the ArrayList<String>[] scheduleTime of the Schedule object
     */
	public ArrayList<String>[] getScheduleTime(){
		return this.scheduleTime;
	}
}