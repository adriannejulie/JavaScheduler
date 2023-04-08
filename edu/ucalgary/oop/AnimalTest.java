/**
  @author Bernard Aire
  bernard.aire@ucalgary.ca
  @version 1.0
  @since 1.0
 */



package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class AnimalTest {

    public AnimalTest(){

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


    
    
    






    
}
