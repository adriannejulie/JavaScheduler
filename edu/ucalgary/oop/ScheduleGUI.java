/**
@author Adrianne Julia Lat
adruiannejulia.lat@ucalgary.ca
@version 1.5
@since 1.0
*/

package edu.ucalgary.oop;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

/*
 * ScheduleGUI generates a user interactive application that
 * generates a schedule based on the parameters given by an SQL Database
 * ScheduleGUI accounts for time conflicts and allows the user to make changes to the database
 */


public class ScheduleGUI extends JFrame implements MouseListener, ActionListener {
    private Task[] changeTasks;
    private ArrayList<ArrayList<Task>> scheduleTask;
    private ArrayList<ArrayList<String>> schedule;
    private ArrayList<String> hour;
    private ArrayList<Task> hourTasks;

    private int oldStartTime;
    private  Task[] taskoptions;
    private Task currentTaskOption;



    private JLabel instructions;
    private JPanel pane;

    private JLabel vetInstructionsText;
    private JTextField vetInput;
    private int newTime;

    private JButton[] vetButtons;
    private Client client;

    private int volunteerHours;

    private  String[] options;



    
    /*
    * Constructor that generates schedule, allows for user interaction when time conflicts within the schedule are identified
    * PARAMATERS: frame: JFrame
    * PROMIMSES: DATA STORED INTO VALUES, NO RETURN VALUE
    */
    public ScheduleGUI(JFrame frame) {
       /*
        
        instructions = new JLabel("Would you like to generate the schedule?");

        JButton generateSchedule = new JButton("Generate Schedule");
        
        generateSchedule.addActionListener(e -> {

            int volunteerAvailable = JOptionPane.showConfirmDialog(frame, "The schedule requires a volunteer. \nIs a volunteer available?");
            if (volunteerAvailable == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "Schedule Geneterated as a Text file.");
            }
            else {
                int vetAvailable = JOptionPane.showConfirmDialog(frame, "No volunteer is available.\nIs a vet available to change the animal's medical requirements?");
                if (vetAvailable == JOptionPane.YES_OPTION) {
                    generateSchedule.doClick();
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Schedule cannot be generated with the requirements given.\nPlease change task requirements.");
                }
            }
        }); */
        
        instructions = new JLabel("Would you like to generate the schedule?");
         
        JButton generateSchedule = new JButton("Generate Schedule");
         //Layout is not centered :( fix it
         JPanel panel = new JPanel();
         panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
         panel.add(Box.createVerticalGlue());
         panel.add(instructions);
         panel.add(Box.createVerticalStrut(50));
         panel.add(Box.createHorizontalGlue());
         panel.add(generateSchedule);
         panel.add(Box.createHorizontalGlue());
         panel.add(Box.createVerticalGlue());
         frame.add(panel);
         frame.pack();
 
         
         frame.setVisible(true);
        
        
        generateSchedule.addActionListener(e -> {
                Client client = new Client(); //creates new client
                boolean continueLoop = true;
                while(continueLoop) {
                    try{
                        client.buildSchedule();
                        System.out.println("Done!");
                        continueLoop = !continueLoop;
                        client.uploadSchedule();
                    } catch (VolunteerNeededException v) {
                        this.volunteerHours = client.getSchedule().getVolunteerHour(); //gets hour that needs a volunteer
                        this.oldStartTime = volunteerHours;
                        int volunteerAvailable = JOptionPane.showConfirmDialog(frame, "The schedule requires a volunteer at hour: " + oldStartTime + ". \nIs a volunteer available?");
                        if (volunteerAvailable == JOptionPane.YES_OPTION) { //if user selects YES, a volunteer is a available
                            
                            client.getSchedule().setTrueVolunteerHoursByIndex(oldStartTime);

                            
                            //JOptionPane.showMessageDialog(frame, "Schedule Geneterated as a Text file.");
                        } else if (volunteerAvailable == JOptionPane.NO_OPTION){
                            int vetAvailable = JOptionPane.showConfirmDialog(frame, "No volunteer is available.\nIs a vet available to change the animal's medical requirements?");
                            if (vetAvailable == JOptionPane.YES_OPTION) { //vet is available to change Task start times
                                
                                JPanel pane = new JPanel();  //create a new pop up panel

                                pane.setLayout(new GridLayout(2, 2)); //creates a grid of buttons
    
                                
                                /*call the tasks and assign them to taskoptions*/
    
                                this.scheduleTask = client.getSchedule().getScheduleTasks();
                                this.hourTasks = this.scheduleTask.get(oldStartTime);
                                this.taskoptions = this.hourTasks.toArray(new Task[0]);
    
    
                                this.schedule = client.getSchedule().getScheduleTime();
                                this.hour = this.schedule.get(oldStartTime);
                                this.options = this.hour.toArray(new String[this.hour.size()]);

                                vetButtons = new JButton[options.length]; //JButton[] is initialized
    
                                ScheduleGUI buttonListener = new ScheduleGUI(frame); //button listener

    
                                for (int i = 0; i < vetButtons.length; i++) {
                                    vetButtons[i] = new JButton(options[i]); //each button is given a task description
                                    this.currentTaskOption = this.taskoptions[i];
                                    vetButtons[i].addActionListener(buttonListener); 
                                    pane.add(vetButtons[i]); //adds to buttons to the grid
                                }

                                vetInstructionsText = new JLabel("Please enter a start time for the selected task");
                                vetInput = new JTextField("e.g. 4", 3);
                                vetInput.addMouseListener(this);

                                JPanel clientPanel = new JPanel();
                                clientPanel.setLayout(new FlowLayout());
                                clientPanel.add(vetInput);
                                this.add(clientPanel, BorderLayout.CENTER);
                                pane.add(vetInstructionsText);
                                pane.add(vetInput);
                                pane.add(new JLabel("Please select a task"), BorderLayout.NORTH); //labels

                                frame.getContentPane().add(BorderLayout.NORTH, pane);

                                System.out.println("BITCH");

                                frame.add(pane, BorderLayout.CENTER);

                                frame.add(panel);
                                
                                frame.pack();
                                frame.setVisible(true);

                                
 
    
                        } else {
                            JOptionPane.showMessageDialog(frame, "Schedule is unable to be generated.");

                        }
                    }
                    } catch (VetNeededException ex) {
                        int vetAvailable = JOptionPane.showConfirmDialog(frame, "No volunteer is available.\nIs a vet available to change the animal's medical requirements?");
                        if (vetAvailable == JOptionPane.YES_OPTION) { //vet is available to change Task start times

                            vetInstructionsText = new JLabel("Please enter a start time for the selected task");
                            vetInput = new JTextField("e.g. 4", 3);
                            vetInput.addMouseListener(this);
                            System.out.println("vetInput is: " + vetInput);

                            //newTime = Integer.parseInt(vetInput.getText()); //New start time (integer)

                            /*call the tasks and assign them to taskoptions*/

                            this.scheduleTask = client.getSchedule().getScheduleTasks();
                            this.hourTasks = this.scheduleTask.get(oldStartTime);
                            this.taskoptions = (Task[]) this.hourTasks.toArray();


                            this.schedule = client.getSchedule().getScheduleTime();
                            this.hour = this.schedule.get(oldStartTime);
                            this.options = (String[]) this.hour.toArray();
                        
                            pane = new JPanel();  //display those tasks as button options
                            pane.setLayout(new GridLayout(2, 2)); //creates a grid of buttons

                            vetButtons = new JButton[options.length]; //JButton[] is initialized

                            ScheduleGUI buttonListener = new ScheduleGUI(frame); //button listener

                            for (int i = 0; i < vetButtons.length; i++) {
                                vetButtons[i] = new JButton(options[i]); //each button is given a task description
                                this.currentTaskOption = this.taskoptions[i];
                                vetButtons[i].addActionListener(buttonListener); 
                                pane.add(vetButtons[i]); //adds to buttons to the grid
                            }
                            frame.add(new JLabel("Please select a task"), BorderLayout.NORTH); //labels
                            frame.getContentPane().add(BorderLayout.NORTH, pane);

                            frame.add(pane, BorderLayout.CENTER);
                            /*Do this need to be here if it's at the bottom? */
                            frame.pack();
                            frame.setVisible(true);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    
                        }

                        else {
                            JOptionPane.showMessageDialog(frame, "Schedule cannot be generated with the requirements given.\nPlease change task requirements.");
                        }
                            
                        
                        } 
                }
            
            
        });

        System.out.println("Done building Schedule");

        
        
       

        System.out.println("Hola");
        
       
    }  

    /*
    * Createsa new task using data passed in by the JButton pressed inside the constructor
    * Changes Task start time by accessing changeMedicalTask() from Client class
    * PARAMATERS: All data called by addActionListener()
    * PROMIMSES: DATA STORED INTO VALUES, NO RETURN VALUE
    */
    public void actionPerformed(ActionEvent event){
         //Creating a new task using newTime
        //take the selected task and create a new task by replacing the Task's startTime
        //call changeMedicalTask( {the new task here})
        newTime = Integer.parseInt(vetInput.getText()); //New start time (integer)

        this.changeTasks = client.getTreatments();

        for (Task i : this.changeTasks) {
            if (i == currentTaskOption){
                i.setStartHour(this.newTime);
                client.changeMedicalTask(i);
            }
        }
    }

    /*
    * Changes JTextField to a blank textbox
    * PARAMATERS: event: MouseEvent
    * PROMIMSES: NO RETURN VALUE
    */
    public void mouseClicked(MouseEvent event){
        
        if(event.getSource().equals(vetInput))
            vetInput.setText("");
                
    }


    /*
    * Unused methods that must be present when implementing MouseListener
    * PARAMATERS: event: MouseEvent
    * PROMIMSES: DATA STORED INTO VALUES, NO RETURN VALUE
    */
    public void mouseEntered(MouseEvent event){
        
    }

    public void mouseExited(MouseEvent event){
        
    }

    public void mousePressed(MouseEvent event){
        
    }

    public void mouseReleased(MouseEvent event){
        
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Schedule Generator");
            new ScheduleGUI(frame).setVisible(true);
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
            
        });
    }
    
}