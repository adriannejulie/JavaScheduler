/**
@author Adrianne Julia Lat
adriannejulia.lat@ucalgary.ca
@version 1.9
@since 1.0
*/

package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    public JLabel instructions;
    public JLabel closingInstructions;
    private JTextField vetInput;
    private int newTime;

    private JButton[] vetButtons;
    private  String[] options;
	public Object generateSchedule;

    /**
    * Constructor that generates schedule, allows for user interaction when time conflicts within the schedule are identified
    * If a time conflict is detected by throwing a volunteerException or a vetException
    * If a volunteerException is caled, the user is prompted to answer a question.
    * If the user says yes, then a text file is generated.
    * If the user says no, the vetException is called until no excpetions are called in the loop.
    * @param frame is a JFrame component that creates the GUI
    */
    public ScheduleGUI(JFrame frame) {
        instructions = new JLabel("Would you like to generate the schedule?");
        closingInstructions = new JLabel("Schedule has been generated as a Text File");
         
         JButton generateSchedule = new JButton("Generate Schedule");

        generateSchedule.addActionListener(e -> {

                this.client = new Client(); //creates new client
                boolean continueLoop = true;
                while(continueLoop){
                    
                    try{
                        client.buildSchedule();
                        System.out.println("Done!");
                        continueLoop = !continueLoop;
                        client.uploadSchedule();
                        JOptionPane.showMessageDialog(frame, "Schedule Geneterated as a Text file.");
                     
                        System.exit(0);


                    } catch (VolunteerNeededException v) {
                        
                        this.volunteerHours = client.getSchedule().getVolunteerHour(); //gets hour that needs a volunteer
                       
                        int volunteerAvailable = JOptionPane.showConfirmDialog(frame, "The schedule requires a volunteer at hour: " + volunteerHours + ". \nIs a volunteer available?\n If you've previously selected no on this panel, please select no again.");
                        
                        if (volunteerAvailable == JOptionPane.YES_OPTION) { //if user selects YES, a volunteer is a available

                            client.getSchedule().setTrueVolunteerHoursByIndex(volunteerHours);

                            
                        } else {

                            //Create buttons using "Generate Schedule" method
                            int vetAvailable = JOptionPane.showConfirmDialog(frame, "No volunteer is available.\nAtleast one or more of the hour's tasks needs to be modified.\nIs a vet available to change the animal's medical requirements?");
                            if (vetAvailable == JOptionPane.YES_OPTION) { //vet is available to change Task start times
                                    //call the tasks and assign them to taskoptions
                                    vetImplementation(frame);
                                    
                            } else {
                                JOptionPane.showMessageDialog(frame, "Schedule was unable to be geneterated.");

                                System.exit(0);
                            
                                
                            }
                    }
                    } catch (VetNeededException ex) {
                        int vetAvailable = JOptionPane.showConfirmDialog(frame, "Volunteer cannot be called.\nAt least one or more tasks needs to be modified.\nIs a vet available to change the animal's medical requirements?");
                            if (vetAvailable == JOptionPane.YES_OPTION) { //vet is available to change Task start times
                                    //call the tasks and assign them to taskoptions
                                    vetImplementation(frame);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Schedule was unable to be geneterated.");

                                System.exit(0);
                            }
                        }   
                    }           
        });
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JPanel innerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        innerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(instructions);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerPanel.add(instructions);


        panel.add(Box.createVerticalStrut(50));
        panel.add(Box.createHorizontalGlue());
        panel.add(generateSchedule);
        generateSchedule.setAlignmentX(Component.CENTER_ALIGNMENT);
        innerPanel.add(generateSchedule);
      
        panel.add(innerPanel);
        panel.add(Box.createVerticalGlue());
        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(270, 200);
        frame.setVisible(true);
    }  

    /**
    * Createsa new task using data passed in by the JButton pressed inside the constructor
    * Changes Task start time by accessing changeMedicalTask() from Client class
    * @param e All data called by addActionListener()
    */
    public void actionPerformed(ActionEvent e) {
        

        // get the source of the event
        Object source = e.getSource();
        
        // check if the source is a button
        if (source instanceof JButton) {
            // get the index of the button that was clicked
            int index = Arrays.asList(vetButtons).indexOf(source);
            
            // do something with the index
            System.out.println("Button at index " + index + " was clicked.");
            this.currentTaskOption = this.taskoptions[index];
        }
    }
    /**
     * An implementation of the vetException
     * This method creates a panel that allows the user to select a Task object
     * and change the hour that Task object occurs
     * The user interface is implemented using buttons and a text box
     * @param frame
     */

    public void vetImplementation(JFrame frame){
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
                        //final int index = i;
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
                        
                        try {
                            int textChecker = Integer.parseInt(input);
                        } catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(frame, "Input was not an integer.\nPlease enter an integer.");
                        } 
                        this.newTime = Integer.parseInt(input); // convert input to int

                        this.changeTasks = this.client.getTreatments(); 
                       
                        for (Task i : this.changeTasks ) {
                            if (i == currentTaskOption){
                                i.setStartHour(this.newTime);
                                client.changeMedicalTask(i);
                            }
                        }
                        
                    });

                    buttonPanel.add(okButton);
                    panel.add(buttonPanel, BorderLayout.SOUTH);

                    // set dialog size and show it
                    dialog.pack();
                    dialog.setLocationRelativeTo(frame);
                    dialog.setVisible(true);

        
    }

    /**
    * Changes JTextField to a blank textbox
    *
    * @param event this is the button clicked inside the frame
    */
    public void mouseClicked(MouseEvent event){
        
        if(event.getSource().equals(vetInput))
            vetInput.setText("");
                
    }


    /**
    * Unused methods that must be present when implementing MouseListener
    * @param event this is the button pressed inside the frame
    */
    public void mouseEntered(MouseEvent event){
        
    }

    public void mouseExited(MouseEvent event){
        
    }

    public void mousePressed(MouseEvent event){
        
    }

    public void mouseReleased(MouseEvent event){
        
    }
    /**
     * getCurrentTaskOption()
     * returns varibable currentTaskOption
     * Has no functionality within the SchdeduleGUI class
     * Present for testing purposes
     * @return currentTaskOptions, the Task object that contains the current Task object
     */
    public Task getCurrentTaskOption() {
        return currentTaskOption;
    }

    /**
     * getClient()
     * Has no functionality within the ScheduleGUI class
     * Present onlt for testing purposes
     * @return Client object
     */
    public Client getClient() {
        return this.client;
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Schedule Generator");
            new ScheduleGUI(frame);
        });
    }

    
    
}

