package edu.ucalgary.oop;

import java.awt.EventQueue;

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
    
}