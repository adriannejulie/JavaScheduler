package edu.ucalgary.oop;

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

    private int volunteerHours;
    private  Task[] taskoptions;
    private Task currentTaskOption;
    private Client client;



    private JLabel instructions;
    private JPanel pane;

    private JLabel vetInstructionsText;
    private JTextField vetInput;
    private int newTime;

    private JButton[] vetButtons;
    

    

    private  String[] options;



    
    /*
    * Constructor that generates schedule, allows for user interaction when time conflicts within the schedule are identified
    * PARAMATERS: frame: JFrame
    * PROMIMSES: DATA STORED INTO VALUES, NO RETURN VALUE
    */
    public ScheduleGUI(JFrame frame) {
        instructions = new JLabel("Would you like to generate the schedule?");
         
         JButton generateSchedule = new JButton("Generate Schedule");

        generateSchedule.addActionListener(e -> {

            frame.remove(instructions);
            frame.remove(generateSchedule);
                this.client = new Client(); //creates new client
                boolean continueLoop = true;
                while(true){
                    try{
                        client.buildSchedule();
                        System.out.println("Done!");
                        continueLoop = !continueLoop;
                        client.uploadSchedule();
                        JOptionPane.showMessageDialog(frame, "Schedule Geneterated as a Text file.");

                    } catch (VolunteerNeededException v) {
                        


                        this.volunteerHours = client.getSchedule().getVolunteerHour(); //gets hour that needs a volunteer
                       
                        int volunteerAvailable = JOptionPane.showConfirmDialog(frame, "The schedule requires a volunteer at hour: " + volunteerHours + ". \nIs a volunteer available?");
                        
                        if (volunteerAvailable == JOptionPane.YES_OPTION) { //if user selects YES, a volunteer is a available

                            client.getSchedule().setTrueVolunteerHoursByIndex(volunteerHours);

                            
                        } else {
                            //frame.removeAll();

                            //Create buttons using "Generate Schedule" method
                            int vetAvailable = JOptionPane.showConfirmDialog(frame, "No volunteer is available.\nIs a vet available to change the animal's medical requirements?");
                            if (vetAvailable == JOptionPane.YES_OPTION) { //vet is available to change Task start times
                                    //call the tasks and assign them to taskoptions
        
                                    this.scheduleTask = client.getSchedule().getScheduleTasks();
                                    this.hourTasks = this.scheduleTask.get(volunteerHours);
                                    this.taskoptions = this.hourTasks.toArray(new Task[this.hourTasks.size()]);
        
        
                                    this.schedule = client.getSchedule().getScheduleTime();
                                    this.hour = this.schedule.get(volunteerHours);
                                    this.options = this.hour.toArray(new String[this.hour.size()]);

                                    JPanel buttonPanel = new JPanel(new GridLayout(options.length, 1));

                                // create a new JDialog
                                    JDialog dialog = new JDialog(frame, "Select Task Start Time", true);
                                    JPanel panel = new JPanel(new BorderLayout());
                                    dialog.getContentPane().add(panel);

                                    // add label and text field to dialog
                                    JLabel label = new JLabel("Please enter a start time for the selected task:");
                                    JTextField textField = new JTextField(5);
                                    JPanel inputPanel = new JPanel();
                                    inputPanel.add(label);
                                    inputPanel.add(textField);
                                    panel.add(inputPanel, BorderLayout.CENTER);

                                    vetButtons = new JButton[options.length]; //JButton[] is initialized
                                    for (int i = 0; i < vetButtons.length; i++) {
                                        vetButtons[i] = new JButton(options[i]); //each button is given a task description
                                        this.currentTaskOption = this.taskoptions[i];
                                        vetButtons[i].addActionListener(this);
                                    }
                                    for (int b = 0; b < vetButtons.length; b++) {
                                        buttonPanel.add(vetButtons[b]);
                                    }
                                    frame.add(buttonPanel);
                                    frame.pack();

                                    // add buttons to dialog
                                   
                                    JButton okButton = new JButton("Update Schedule");
                                    okButton.addActionListener(e1 -> {
                                        // handle OK button action
                                        dialog.dispose(); // close the dialog
                                        String input = textField.getText();
                                        this.newTime = Integer.parseInt(input); // convert input to int

                                        // handle new start time

                                        
                                    });

                                    /*JButton cancelButton = new JButton("Cancel");
                                    cancelButton.addActionListener(e2 -> {
                                        // handle Cancel button action
                                        dialog.dispose(); // close the dialog
                                    });*/

                                    buttonPanel.add(okButton);
                                    //buttonPanel.add(cancelButton);
                                    panel.add(buttonPanel, BorderLayout.SOUTH);

                                    // set dialog size and show it
                                    dialog.pack();
                                    dialog.setLocationRelativeTo(frame);
                                    dialog.setVisible(true);
    
                        } else {
                            JOptionPane.showMessageDialog(frame, "Schedule is unable to be generated.");

                        }
                    }
                    } catch (VetNeededException ex) {
                    }
                }
        });
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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setVisible(true);
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

        this.changeTasks = this.client.getTreatments(); //BRAADEN

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
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Schedule Generator");
            new ScheduleGUI(frame);
        });
    }
    
}

/*import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.*;


public class ScheduleGUI extends JFrame {
    private JLabel instructions;
    private Client client;
    

    public ScheduleGUI(JFrame frame) {
        instructions = new JLabel("Would you like to generate the schedule?");
    
        JButton generateSchedule = new JButton("Generate Schedule");
    
        generateSchedule.addActionListener(e -> {
            int volunteerAvailable = JOptionPane.showConfirmDialog(frame, "The schedule requires a volunteer. \nIs a volunteer available?");
            if (volunteerAvailable == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "Schedule Generated as a Text file.");
            }
            else {
                int vetAvailable = JOptionPane.showConfirmDialog(frame, "No volunteer is available.\nIs a vet available to change the animal's medical requirements?");
                if (vetAvailable == JOptionPane.YES_OPTION) {
                    JPanel pane = new JPanel();
                    JButton test = new JButton("test button");
                    pane.add(test);
                    frame.add(pane);
                    frame.pack();
                    frame.setVisible(true);
    
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Schedule cannot be generated with the requirements given.\nPlease change task requirements.");
                }
            }
        });
        /*
         instructions = new JLabel("Would you like to generate the schedule?");
        JButton generateSchedule = new JButton("Generate Schedule");
        
        generateSchedule.addActionListener(e -> {
            try{
                Client client = new Client();
                client.uploadSchedule();
                JOptionPane.showConfirmDialog(frame, "The schedule requires a volunteer. \nIs a volunteer available?");
            } catch (VolunteerNeededException ex) {
                int vetAvailable = JOptionPane.showConfirmDialog(frame, "No volunteer is available.\nIs a vet available to change the animal's medical requirements?");
                if (vetAvailable == JOptionPane.YES_OPTION) {
                    generateSchedule.doClick();
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Schedule cannot be generated with the requirements given.\nPlease change task requirements.");
                }
            }
        });
         */

        /* 
        
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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Schedule Generator");
            new ScheduleGUI(frame);
        });
    }
    
}*/