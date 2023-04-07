

import java.awt.event.ActionEvent;
import java.beans.Transient;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import org.junit.Test;

import edu.ucalgary.oop.ScheduleGUI;

import static org.junit.Assert.*;



public class TestingGUI {
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


}
