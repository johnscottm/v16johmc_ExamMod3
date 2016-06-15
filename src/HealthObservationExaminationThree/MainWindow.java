/*v16johmc Examination Three IK1052
 */
package HealthObservationExaminationThree;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * A class to create a health observations application with GUI
 * @author John McInnes
 */
public class MainWindow extends JFrame {
    //declare fields for GUI and for data
    private JPanel healthPanel;
    private JPanel healthPanelN;
    private JPanel healthPanelS;
    private JPanel healthPanelE;
    private JPanel healthPanelW;
    private JPanel healthPanelC;
    //labels for GUI text fields, and other displayable
    private JLabel dateLabel;
    private JLabel conditionLabel;
    private JLabel treatmentLabel;
    private JLabel temperLabel;
    private JLabel obsLabel;
    //text fields
    private JTextField dateTextField;
    private JTextField treatmentTextField;
    private JTextField temperTextField;
    private JTextField conditionTextField;
    //buttons
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton; 
    private JButton cancelButton;
    private JButton saveButton;
    private JButton openButton;
    private JButton feverWindowButton;
    //date input spinner
    private JSpinner clockSpinner ;
    //windows
    private FeverFrame feverWindow;
    private JComboBox observationSelectionComboBox;
    //data structures
    private Observations observations;
    
   /**
    * buildPanel method instantiates GUI controls; JButtons, JCombox, JTextField, JFrame
    * and registers and instantiates all appropriate event listeners..
    */
    private void buildPanel() throws JAXBException {
        //labels and text fields
        dateLabel = new JLabel();
        conditionLabel = new JLabel();
        treatmentLabel = new JLabel();
        temperLabel = new JLabel();
        obsLabel = new JLabel();
        dateTextField = new JTextField(20);
        conditionTextField = new JTextField(15);
        treatmentTextField = new JTextField(15);
        temperTextField = new JTextField(5);
        //set date to not editable
        dateTextField.setEditable(false);
        //set label control text
        dateLabel.setText("     Date");
        conditionLabel.setText("     Condition");
        treatmentLabel.setText("     Treatment");
        temperLabel.setText("     Temperature");
        obsLabel.setText("Select Observation: ");
        //text display gui controls
        observationSelectionComboBox = new JComboBox();
        //create date and time selector
        clockSpinner = new JSpinner (new SpinnerDateModel());
        clockSpinner.setSize(dateTextField.getPreferredSize());
        //JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(clockSpinner, "HH:mm");
                //clockSpinner.setEditor(timeEditor);
        //add a layout manager
        healthPanel.setLayout(new BorderLayout());
        //create subpanels
        healthPanelN = new JPanel();
        healthPanelS = new JPanel();
        healthPanelE = new JPanel();
        healthPanelW = new JPanel();
        healthPanelC = new JPanel(); 
        // add a border and a title to healthPanel
        healthPanel.setBorder(BorderFactory.createTitledBorder("Health Record Viewer"));
        //add Panels
        healthPanel.add(healthPanelN, BorderLayout.NORTH);
        healthPanel.add(healthPanelS, BorderLayout.SOUTH);
        healthPanel.add(healthPanelE, BorderLayout.EAST);
        healthPanel.add(healthPanelW, BorderLayout.WEST);        
        healthPanel.add(healthPanelC, BorderLayout.CENTER);
        //buttons
        addButton = new JButton("Add New Observation");
        deleteButton = new JButton("Delete Observation Record");   
        editButton = new JButton("Update Observation Record");
        saveButton = new JButton("Save File...");
        openButton = new JButton("Open File...");
        cancelButton = new JButton("Clear Text Boxes");
        feverWindowButton = new JButton("Show Fever Window");   
        //windows
        feverWindow = new FeverFrame( (ArrayList)observations.getObservations());
        //event handler listeners
        addButton.addActionListener(new AddButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        editButton.addActionListener(new EditButtonListener());
        saveButton.addActionListener(new SaveButtonListener());
        openButton.addActionListener(new OpenButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());
        feverWindowButton.addActionListener(new FeverButtonListener());
        observationSelectionComboBox.addItemListener(new ItemSelectedListener());
        //add controls to panel
        //add text fields and labels
        //by border panel regions
        healthPanelN.add(obsLabel);
        healthPanelN.add(observationSelectionComboBox);
        healthPanelW.add(dateLabel);
        healthPanelW.add(clockSpinner);
        healthPanelW.add(dateTextField);
        healthPanelW.add(temperLabel);
        healthPanelW.add(temperTextField);
        healthPanelC.add(treatmentLabel);
        healthPanelC.add(treatmentTextField);
        healthPanelE.add(conditionLabel);
        healthPanelE.add(conditionTextField);

        //south panel add buttons
        healthPanelS.add(saveButton);
        healthPanelN.add(addButton);
        healthPanelN.add(editButton);
        healthPanelN.add(deleteButton);
        healthPanelS.add(saveButton);
        healthPanelS.add(openButton);
        healthPanelN.add(cancelButton);
        healthPanelS.add(feverWindowButton);
        
        //set initial button state disabled
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        cancelButton.setEnabled(false);
        dateTextField.setVisible(false);
    }
    
    /**
     * Method to create objects for program from file from user specified file
     * @param openFileName
     * @throws JAXBException
     */
    public void testXMLOpenFile(String openFileName) throws JAXBException
    {
            try {
                    System.out.println("reading in file");
                    File healthData = new File (openFileName);
                    System.out.print(healthData);
                    JAXBContext context = JAXBContext.newInstance(Observations.class );
                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    observations = (Observations)unmarshaller.unmarshal(healthData);
                    System.out.println("Observations created" );
                    System.out.println(observations.getObservations().toString());

                } catch (JAXBException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
    }    

    /**
     * Method to create an XML text file from user specified file.
     * @param saveFileName
     * @throws JAXBException
     */
    public void testXMLSaveFile(String saveFileName) throws JAXBException
    {
        // create JAXB context and instantiate marshaller
        Marshaller observationsMarshaller ;
        JAXBContext context = JAXBContext.newInstance(Observations.class);
        observationsMarshaller = context.createMarshaller();
        observationsMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);                                   
        observationsMarshaller.marshal(observations, System.out);

        // Write to File
         System.out.println("save");
         File healthData = new File(saveFileName);
         observationsMarshaller.marshal(observations, healthData );
    }
    
    /**
     * MainWindow class constructor, creates GUI window and invokes setup methods.
     * @throws javax.xml.bind.JAXBException
     */
    public MainWindow () throws JAXBException
    {
        // create constants for window dimensions
        final int WINDOWHEIGHT = 200;
        final int WINDOWWIDTH = 1100;
    
        // set dimensions of window object
        this.setSize(WINDOWWIDTH, WINDOWHEIGHT);
        // set the title
        this.setTitle("My Health Record");
        // set the window to end program when closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // invoke setup method to add gui elements to window
        this.setup();
        // set visibility property of window object to true, or visible
        this.setVisible(true);
    }

    /**
     * Main method to start the operation of application. Invokes MainWindow class constructor.
     * @param args the command line arguments
     * @throws javax.xml.bind.JAXBException
     */
    public static void main(String[] args) throws JAXBException {
        // create instance of MainWindow
        MainWindow healthWindow = new MainWindow(); 
    
    }
    /**
    * openFile method creates JFileChooser object to get file name and invokes
    * testXMLOpenFile to open xml file
    */     
    private void openFile() {        
        JFileChooser openChoice = new JFileChooser();
        String openFileName= "";

        int successSave = openChoice.showOpenDialog(null);
        if(successSave == openChoice.APPROVE_OPTION)
        {
            File healthData = openChoice.getSelectedFile();
            openFileName = healthData.getPath();                   
        }
        //error code if user cancels
        
 /////////       
        
        try {
            testXMLOpenFile( openFileName);
        } catch (JAXBException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    updateGUI();    
        }
     /**
    * Save method creates JFileChooser object to get file name and invokes
    * testXMLSaveFile to write xml to file
    */   
    private void saveFile() {
        JFileChooser saveChoice = new JFileChooser();
            String saveFileName= "";
            int successSave = saveChoice.showSaveDialog(null);
            if(successSave == saveChoice.APPROVE_OPTION)
            {
                File healthData = saveChoice.getSelectedFile();
                saveFileName = healthData.getPath();                   
            }
            try {
                testXMLSaveFile(saveFileName);
            } catch (JAXBException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    /**
    * Setup method creates data structures, reads in data objects from file
    * and builds up GUI. Invokes readFile method and buildPanel method from this object.
    */   
    private void setup() throws JAXBException{
        //intialize data structures  
        //Observation startupObservation = new Observation();
        ArrayList startupArray  = new ArrayList();
        //startupArray.add(startupObservation);
        observations = new Observations();
        observations.setObservations(startupArray);
        healthPanel = new JPanel();
        buildPanel();
        this.add(healthPanel);           
    }
    
    /**
    * updateGUI method resets the observationSelectionComboBox GUI control with new data 
    */
    private void updateGUI(){   
        observationSelectionComboBox.setModel(new DefaultComboBoxModel(((ArrayList)(observations.getObservations())).toArray()));
        observationSelectionComboBox.setSelectedIndex(-1);
        clearObservationTextFields();
        //update the fever window Jlist component datat
        feverWindow.setFeverList((ArrayList)observations.getObservations());
    }
     /**
    * after cancelButton activated clear text fields, enable addButton
    * disable edit, delete, cancel buttons, clear selected indexes
    */
    private void cancelButtonClearObservation() {
        //invoke clear method for text fields
        clearObservationTextFields();
        //enable and disable appropriate buttons
        addButton.setEnabled(true);
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);
        cancelButton.setEnabled(false);
        updateGUI();
    }
     /**
    * sets text field controls to empty if they show Observation data
    */
    private void clearObservationTextFields() {
        //set text in text fields to null
        conditionTextField.setText(null);
        dateTextField.setText(null);
        temperTextField.setText(null);
        treatmentTextField.setText(null);
        cancelButton.setEnabled(false);
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);
        addButton.setEnabled(true);
        dateTextField.setVisible(false);
        clockSpinner.setVisible(true);
    }
    /**
    * GUI method to delete Observation object data from selection by user
    */
    private void deleteButtonObservationData() {
        //remove Observation object from observations.getObservations() 
        observations.getObservations().remove(observationSelectionComboBox.getSelectedIndex());
        //disable buttons to edit and delete observation, enable add button, update data display
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);
        addButton.setEnabled(true);
        dateTextField.setVisible(false);
        clockSpinner.setVisible(true);
        updateGUI();
    }

    /**
    * GUI method to delete Observation object data from selection by user
    */
    private void addButtonObservationData() {
        
        if(inputValidation())
        {
            //create Observation object from text fields
            Observation addedObservation = new Observation(
                    (Date)clockSpinner.getValue(),
                    treatmentTextField.getText(),//treatment
                    conditionTextField.getText(),//condition
                    Double.valueOf(temperTextField.getText())//temperature            
            );
            //add Observation to ArrayList
            observations.getObservations().add(addedObservation);   
            //disable buttons to edit and delete observation, enable add button, update data display
            deleteButton.setEnabled(false);
            editButton.setEnabled(false);
            addButton.setEnabled(true);
            dateTextField.setVisible(true);
            clockSpinner.setVisible(false);
            updateGUI();
        }
    }
    /**
    * GUI method to update text fields with selected observation data.
    * triggered by selecting item in observation JComboBox
    */
    private void displaySelectedObservationData() 
    {
        //update gui text controls with data from selected Observation object
        conditionTextField.setText(observations.getObservations().get(observationSelectionComboBox.getSelectedIndex()).getEntryCondition());
        treatmentTextField.setText(observations.getObservations().get(observationSelectionComboBox.getSelectedIndex()).getEntryTreatment());
        dateTextField.setText(observations.getObservations().get(observationSelectionComboBox.getSelectedIndex()).getEntryDate().getTime().toString());
        temperTextField.setText(observations.getObservations().get(observationSelectionComboBox.getSelectedIndex()).getEntryTemp().toString());        
        //enable edit, delete button and disable add button
        editButton.setEnabled(true);
        deleteButton.setEnabled(true);
        cancelButton.setEnabled(true);
        addButton.setEnabled(false);
        dateTextField.setVisible(true);
        clockSpinner.setVisible(false);
    }
    /**
    * GUI method to update Observation object data from user input data
    */
    private void editButtonUpdateObservationData()
    {
        if (inputValidation())
        {
        //get a reference to the appropriate Observation object based on selection in GUI
        Observation toUpdate = observations.getObservations().get(observationSelectionComboBox.getSelectedIndex());
        //invoke setter methods of Observation object to update
        //todo perform error checks
        toUpdate.setEntryTemp(Double.valueOf(temperTextField.getText()));
        toUpdate.setEntryCondition(conditionTextField.getText());
        toUpdate.setEntryTreatment(treatmentTextField.getText());
        //enable and disable appropriate buttons
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);
        addButton.setEnabled(true);
        updateGUI();
        }
    }

    /**
    * performs input validation returns false if fields empty or not within numeric range
    */
    private boolean inputValidation() {
        //check text fields for some input
        // check temperature for appropriate value
        String temperatureValue = temperTextField.getText();
        Double doubleTempValue = 0.0;
        String treatmentValue = treatmentTextField.getText();
        String conditionValue = conditionTextField.getText();
        //check for empty fields display message dialog if empty
        if (treatmentValue.isEmpty()  || conditionValue.isEmpty() || temperatureValue.isEmpty()) 
        { 
            JOptionPane.showMessageDialog(healthPanel, "Text boxes cannot be empty");
            return false;
        }
        //check if input is in numeric form
        try{
             doubleTempValue = Double.valueOf(temperatureValue);
        }
        catch(NumberFormatException e)
        { 
            JOptionPane.showMessageDialog(healthPanel, "Temperature value is not a number");
            return false;
        }
        if (doubleTempValue > 50 || doubleTempValue < 10) 
        { 
            JOptionPane.showMessageDialog(healthPanel, "Temperature value is out of range");
            return false;
        }
        return true;
    }

        /**
        * Inner event listener class for GUI button
        */
        private class AddButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.out.println("Add");
                addButtonObservationData();            
            }
        }
        /**
        * Inner event listener class for GUI button
        */
        private class DeleteButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                if (observationSelectionComboBox.getSelectedIndex()!=-1)
                {
                    deleteButtonObservationData();                   
                    System.out.println("delete success");
                    updateGUI();
                }
                System.out.println("delete failed");
            }
        } 
        /**
        * Inner event listener class for GUI button
        */   
        private class EditButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.out.println("Edit");
                if (observationSelectionComboBox.getSelectedIndex()!=-1)//isSelectionEmpty()
                {
                    System.out.println("edit display success");
                    editButtonUpdateObservationData();
                }
            }
        }
        /**
        * Inner event listener class for GUI button save
        * Creates a JFileChooser to allow user to create a file to save
        */    
        private class SaveButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                saveFile();               
            }
        }   
        
                /**
        * Inner event listener class for GUI button open
        * invokes fileOpen method to open a data file
        */    
        private class OpenButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                openFile();
            }
        }
     
        /**
        * Inner event listener class for GUI button
        */  
        private class CancelButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.out.println("cancel");
                cancelButtonClearObservation();
            }
        } 
        /**
        * Inner event listener class for GUI button
        */          
         private class FeverButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.out.println("Fever Button"); 
                feverWindow.setVisible(true);                                               
            }
        }        
        /**
        * Inner event listener class for GUI JComboBox
        */  
         private class ItemSelectedListener implements java.awt.event.ItemListener {
            public void itemStateChanged(ItemEvent event)
            {
                if ( event.getStateChange()== ItemEvent.SELECTED  )
                {
                    System.out.println("List Item Selected");
                    Integer selectedTest = observationSelectionComboBox.getSelectedIndex();
                    System.out.println("index selected is " + selectedTest.toString());
                    //display selected observation data in JTextFields
                    displaySelectedObservationData();
                }
            }                      
        }
         
}
