/*v16johmc Examination Three IK1052
 */
package HealthObservationExaminationThree;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *  A class to show a view of health observation data dealing with temperature and date.
 * GUI displays a JList in its own JFrame.
 * @author John McInnes
 */
class FeverFrame extends JFrame{
    // create constants for window dimensions
    private final int WINDOWHEIGHT = 350;
    private final int WINDOWWIDTH = 350;
    //text display gui controls
    private JList feverList;
    /**
    * FeverFrame class constructor. 
    * @param observationsList is an ArrayList of Observation objects 
    */ 
    public FeverFrame (ArrayList observationsList)

    {    
        // set dimensions of window object
        setSize(WINDOWWIDTH, WINDOWHEIGHT);
        // set the title
        setTitle("My Fever Record by Date and Fever");
        // set the window to end program when closed
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // set visibility property of window object to false
        setVisible(false);
        //add a feverList Jlist control;
        feverList = new JList();
        this.add(feverList);
    }   
    /**
    * Set method to update the list data 
    * @param updateList is the array of type string to update the data
    */ 
    public void setFeverList(ArrayList updateList)
    {    
        feverList.setListData(updateList.toArray()); 
    }
}