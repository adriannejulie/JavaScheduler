package edu.ucalgary.oop;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.*;


public class ScheduleGUI extends JFrame implements MouseListener {
    private JLabel instructions;
    private Client client;
    private String taskName;
    private JLabel newTask;
    private JTextField taskInput;
    

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
        
        generateSchedule.addActionListener(e -> {
            Client client = new Client();
            try {
                client.uploadSchedule();
            } catch (VolunteerNeededException v) {
                int volunteerAvailability = JOptionPane.showConfirmDialog(frame, "The schedule requires a volunteer. \nIs a volunteer available?");
                if (volunteerAvailability == JOptionPane.YES_OPTION) {
                    generateSchedule.doClick();
                } else {
                    int vetAvailable = JOptionPane.showConfirmDialog(frame, "No volunteer is available.\nIs a vet available to change the animal's medical requirements?");
                    if (vetAvailable == JOptionPane.YES_OPTION) {

                        /*
                         * Should we have multiple input text boxes that defines int taskID, 
                         * String description, int duration, int maxWindow, int startHour, int animalID?
                         * 
                         * 
                         */

                         //This implementation is wrong, Task is not taking inputs correctly
                        /*taskInput = new JTextField("Feeding", 15);
                        JPanel panel = new JPanel();
                        panel.add(new JLabel ("New Task: "));
                        panel.add(taskInput);
                      
                        int result = JOptionPane.showConfirmDialog(frame, panel, "Enter the new medical task", JOptionPane.OK_CANCEL_OPTION);

                        if (result == JOptionPane.OK_OPTION) {
                            Task newTask = taskInput.getText();
                            client.changeMedicalTask(newTask);
                            generateSchedule.doClick();
                        }*/
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Schedule cannot be generated with the requirements given.\nPlease change task requirements.");
                    }
                }
            }
        });
        
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
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Schedule Generator");
            new ScheduleGUI(frame);
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
        });
    }
    public void mouseClicked(MouseEvent event){
        
        if(event.getSource().equals(taskInput))
            taskInput.setText("");

    }

    public void mouseEntered(MouseEvent event){
        
    }

    public void mouseExited(MouseEvent event){
        
    }

    public void mousePressed(MouseEvent event){
        
    }

    public void mouseReleased(MouseEvent event){
        
    }
    
    
}