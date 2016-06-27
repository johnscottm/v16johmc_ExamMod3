/*v16johmc Examination Three IK1052
 */
package HealthObservationExaminationThree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import static java.awt.FlowLayout.LEFT;
import javax.swing.*;
import java.awt.event.*;

import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * A class to create a health observationsWrapper application with GUI
 * @author John McInnes
 * @version June 25, 2016
 */
public class MainWindow extends JFrame {
    //declare fields for GUI and for data
    //Jpanels for containing controls
    private JPanel healthPanel;
    private JPanel healthPanelN;
    private JPanel healthPanelS;
    private JPanel healthPanelE;
    private JPanel healthPanelW;
    private JPanel healthPanelC;
    //box layouts for positioning within healthPanels
    private Container healthPanelBox;
    private Container healthPanelBoxLabels;
    private Container healthPanelBoxTextFields;
    private Container healthPanelBoxRecordButtons;
    private Container healthPanelBoxSelect;
    private Container healthPanelBoxAverageTemp;
    //labels for GUI text fields, and other displayable
    private JLabel dateLabel;
    private JLabel conditionLabel;
    private JLabel treatmentLabel;
    private JLabel temperLabel;
    private JLabel obsLabel;
    private JLabel highTempLabel;
    private JLabel averageTempLabel;
    //text fields
    private JTextField dateTextField;
    private JTextField treatmentTextField;
    private JTextField temperTextField;
    private JTextField conditionTextField;
    private JLabel highTempTextLabel;
    private JLabel averageTempTextLabel;
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
    private Observations observationsWrapper;
    private ArrayList observationsListCopy;
   /**
    * buildClockSpinner sets up clockSpinner control
    */
    private void buildClockSpinner()
    {    
        //create date and time selector
        //get a Calendar object set to current time
        Calendar calendarJSpinner = Calendar.getInstance();
        //create date limits for SpinnerDateModel to limit JSpinner date selections
        Date startingDateJSpinner = calendarJSpinner.getTime();
        calendarJSpinner.add(Calendar.MONTH, -1);
        Date earliestDate = calendarJSpinner.getTime();
        calendarJSpinner.add(Calendar.MONTH, 1);
        Date latestDate = calendarJSpinner.getTime();
        SpinnerDateModel JSpinnerDateModel = new SpinnerDateModel(
                startingDateJSpinner,earliestDate,latestDate,Calendar.YEAR);
        //instantiate clockSpinner using created SpinnerDateModel object
        clockSpinner = new JSpinner (JSpinnerDateModel);
        clockSpinner.setOpaque(true);  
        //Set the clockSpinner to proper format and make it not editable to start
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(clockSpinner, "MMM dd, yyyy HH:mm");
        timeEditor.getTextField().setEditable(false);
        clockSpinner.setEditor(timeEditor);        
    }
    
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
        averageTempLabel = new JLabel();
        highTempLabel = new JLabel();
        dateTextField = new JTextField(20);
        conditionTextField = new JTextField(15);
        treatmentTextField = new JTextField(15);
        temperTextField = new JTextField(5);
        averageTempTextLabel = new JLabel("None");
        highTempTextLabel = new JLabel("None");       
        //set date to not editable
        dateTextField.setEditable(false);
        //set label control text
        dateLabel.setText("     Date");
        conditionLabel.setText("     Condition");
        treatmentLabel.setText("     Treatment");
        temperLabel.setText("     Temperature");
        obsLabel.setText("Select Observation: ");
        averageTempLabel.setText("Average temperature");
        highTempLabel.setText("High Temperature");
        //text display gui controls
        String [] comboBoxMessage = {"Open file or add new record"};
        observationSelectionComboBox = new JComboBox(comboBoxMessage);
        observationSelectionComboBox.setEnabled(false);
        
        buildClockSpinner();
        //add a layout manager
        healthPanel.setLayout(new BorderLayout());
        //create subpanels
        healthPanelN = new JPanel();
        healthPanelS = new JPanel();
        healthPanelE = new JPanel();
        healthPanelW = new JPanel();
        healthPanelC = new JPanel(); 
        //create box layout
        healthPanelBoxSelect = Box.createHorizontalBox();
        healthPanelBoxAverageTemp = Box.createHorizontalBox();
        healthPanelBoxLabels = Box.createVerticalBox();
        healthPanelBoxTextFields = Box.createVerticalBox();
        healthPanelBoxRecordButtons = Box.createVerticalBox();
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
        observationsListCopy = (ArrayList) observationsWrapper.getCopyObservations(observationsWrapper.getObservations());
        feverWindow = new FeverFrame( observationsListCopy );
        //event handler listeners
        addButton.addActionListener(new AddButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        editButton.addActionListener(new EditButtonListener());
        saveButton.addActionListener(new SaveButtonListener());
        openButton.addActionListener(new OpenButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());
        feverWindowButton.addActionListener(new FeverButtonListener());
        observationSelectionComboBox.addItemListener(new ItemSelectedListener());
        //add controls to select box
        healthPanelBoxSelect.add(obsLabel);
        healthPanelBoxSelect.add(Box.createHorizontalStrut(10));
        healthPanelBoxSelect.add(observationSelectionComboBox);
        healthPanelBoxSelect.add(Box.createHorizontalStrut(30));
        //add controls to average temp box
        healthPanelBoxAverageTemp.add(highTempLabel);
        healthPanelBoxAverageTemp.add(Box.createHorizontalStrut(10));
        healthPanelBoxAverageTemp.add(highTempTextLabel);
        healthPanelBoxAverageTemp.add(Box.createHorizontalStrut(20));
        healthPanelBoxAverageTemp.add(averageTempLabel);
        healthPanelBoxAverageTemp.add(Box.createHorizontalStrut(10));
        healthPanelBoxAverageTemp.add(averageTempTextLabel);
        
        //add labels to label box
        healthPanelBoxLabels.add(Box.createVerticalStrut(15));
        healthPanelBoxLabels.add(dateLabel);
        healthPanelBoxLabels.add(Box.createVerticalStrut(15));
        healthPanelBoxLabels.add(temperLabel);
        healthPanelBoxLabels.add(Box.createVerticalStrut(15));
        healthPanelBoxLabels.add(conditionLabel);
        healthPanelBoxLabels.add(Box.createVerticalStrut(15));
        healthPanelBoxLabels.add(treatmentLabel);      
        //add controls to text fields  box
        healthPanelBoxTextFields.add(Box.createVerticalStrut(10));
        healthPanelBoxTextFields.add(dateTextField);
        healthPanelBoxTextFields.add(Box.createVerticalStrut(10));
        healthPanelBoxTextFields.add(clockSpinner);
        healthPanelBoxTextFields.add(Box.createVerticalStrut(10));
        healthPanelBoxTextFields.add(temperTextField);
        healthPanelBoxTextFields.add(Box.createVerticalStrut(10));
        healthPanelBoxTextFields.add(conditionTextField);
        healthPanelBoxTextFields.add(Box.createVerticalStrut(10));
        healthPanelBoxTextFields.add(treatmentTextField);   
        //add controls to buttons for record  box
        healthPanelBoxRecordButtons.add(Box.createVerticalStrut(15));
        healthPanelBoxRecordButtons.add(addButton);
        healthPanelBoxRecordButtons.add(Box.createVerticalStrut(5));
        healthPanelBoxRecordButtons.add(editButton);
        healthPanelBoxRecordButtons.add(Box.createVerticalStrut(5));
        healthPanelBoxRecordButtons.add(deleteButton);
        healthPanelBoxRecordButtons.add(Box.createVerticalStrut(5));
        healthPanelBoxRecordButtons.add(cancelButton);
        //add controls to panel
        //add text fields and labels
        //by border panel regions
        healthPanelN.add(healthPanelBoxSelect);
        healthPanelN.add(healthPanelBoxAverageTemp);
        healthPanelN.setLayout(new FlowLayout(LEFT));
        healthPanelW.add(healthPanelBoxLabels);
        healthPanelW.add(healthPanelBoxTextFields);
        healthPanelC.add(healthPanelBoxRecordButtons);
        //south panel add buttons
        healthPanelS.add(saveButton);
        healthPanelS.add(saveButton);
        healthPanelS.add(openButton);

        healthPanelS.add(feverWindowButton);
//        healthPanelN.add(highTempLabel);
//        healthPanelN.add(highTempTextLabel);
//        healthPanelN.add(averageTempLabel);
//        healthPanelN.add(averageTempTextLabel);
        //set initial button state disabled
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        cancelButton.setEnabled(false);
        dateTextField.setVisible(false);
        //highTempTextField.setEnabled(false);
        //averageTempTextField.setEnabled(false);
        highTempTextLabel.setForeground(Color.red);
        averageTempTextLabel.setForeground(Color.blue);

    }
    
    /**
     * Method to create objects for program from file from user specified file
     * @param openFileName
     */
    public void testXMLOpenFile(String openFileName)
    {
        try 
        {
            System.out.println("reading in file");
            File healthData = new File (openFileName);
            System.out.print(healthData);
            JAXBContext context = JAXBContext.newInstance(Observations.class );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            observationsWrapper = (Observations)unmarshaller.unmarshal(healthData);
            System.out.println("Observations created" );
            System.out.println(observationsWrapper.getObservations().toString());
        } 
        catch (JAXBException ex) 
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    /**
     * Method to create an XML text file from user specified file.
     * @param saveFileName
     */
    public void testXMLSaveFile(String saveFileName) 
    {
        try
        {
            // create JAXB context and instantiate marshaller
            Marshaller observationsMarshaller ;
            JAXBContext context = JAXBContext.newInstance(Observations.class);
            observationsMarshaller = context.createMarshaller();
            observationsMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);                                   
            observationsMarshaller.marshal(observationsWrapper, System.out);
            // Write to File
             System.out.println("save");
             File healthData = new File(saveFileName);
             observationsMarshaller.marshal(observationsWrapper, healthData );
        }
        catch (JAXBException ex) 
        {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * MainWindow class constructor, creates GUI window and invokes setup methods.
     * @throws javax.xml.bind.JAXBException
     */
    public MainWindow () throws JAXBException
    {
        // create constants for window dimensions
        final int WINDOWHEIGHT = 400;
        final int WINDOWWIDTH = 800;
    
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
    public static void main(String[] args) throws JAXBException  
    {
        // create instance of MainWindow
        MainWindow healthWindow = new MainWindow();    
    }
    /**
    * openFile method creates JFileChooser object to get file name and invokes
    * testXMLOpenFile to open xml file
    */     
    private void openFile() 
    {        
        JFileChooser openChoice = new JFileChooser();
        String openFileName= "";

        int successSave = openChoice.showOpenDialog(null);
        if(successSave == openChoice.APPROVE_OPTION)
        {
            File healthData = openChoice.getSelectedFile();
            openFileName = healthData.getPath();                   
        }          
        testXMLOpenFile(openFileName);
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
            testXMLSaveFile(saveFileName);
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
        observationsWrapper = new Observations();
        observationsWrapper.setObservations(startupArray);
        healthPanel = new JPanel();
        buildPanel();
        this.add(healthPanel);           
    }
    
    /**
    * updateGUI method resets the observationSelectionComboBox GUI control with new data 
    */
    private void updateGUI(){ 
        observationsWrapper.sortObservationsByDate();
        //set the data for the combobox using a copy of the observationsWrapper
        ArrayList observationsListCopy = (ArrayList) observationsWrapper.getCopyObservations(observationsWrapper.getObservations());
        observationSelectionComboBox.setModel(new DefaultComboBoxModel( observationsListCopy.toArray() ) );
        observationSelectionComboBox.setSelectedIndex(-1);
        observationSelectionComboBox.setEnabled(true);
        clearObservationTextFields();
        //update the fever window Jlist component datat
        feverWindow.setFeverList(observationsListCopy);
        averageTempTextLabel.setText(observationsWrapper.getObservationsAverageTemp());
        highTempTextLabel.setText(observationsWrapper.getObservationsHighestTemp());
    }
     /**
    * after cancelButton activated clear text fields, enable addButton
    * disable edit, delete, cancel buttons, clear selected indexes
    */
    private void clearButtonClearObservation() {
        //invoke clear method for text fields
        clearObservationTextFields();      
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
        clockSpinner.setEnabled(true);
        clockSpinner.setOpaque(true);
    }
    /**
    * GUI method to delete Observation object data from selection by user
    */
    private void deleteButtonObservationData() {
        //remove Observation object from observationsWrapper.getObservations() 
        observationsListCopy.remove(observationSelectionComboBox.getSelectedIndex());
        observationsWrapper.setObservations(observationsListCopy);
        //disable buttons to edit and delete observation, enable add button, update data display
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);
        addButton.setEnabled(true);
        dateTextField.setVisible(false);
        clockSpinner.setVisible(true);
        clockSpinner.setEnabled(true);
        clockSpinner.setOpaque(true);
        updateGUI();
    }

    /**
    * GUI method to delete Observation object data from selection by user
    */
    private void addButtonObservationData() {
        
        if(inputValidation())
        {
            //get Date object from JSpinner and convert to LocalDateTime object
            Date spinnerDate = (Date)clockSpinner.getValue();
            LocalDateTime passDate = LocalDateTime.ofInstant(spinnerDate.toInstant(), ZoneId.systemDefault());
            //create Observation object from text fields
            Observation addedObservation = new Observation(
                    passDate,
                    treatmentTextField.getText(),//treatment
                    conditionTextField.getText(),//condition
                    Double.valueOf(temperTextField.getText())//temperature            
            );
            //add Observation to ArrayList
            observationsListCopy.add(addedObservation); 
            observationsWrapper.setObservations(observationsListCopy);
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
        conditionTextField.setText(observationsWrapper.getObservations().get(observationSelectionComboBox.getSelectedIndex()).getEntryCondition());
        treatmentTextField.setText(observationsWrapper.getObservations().get(observationSelectionComboBox.getSelectedIndex()).getEntryTreatment());
        DateTimeFormatter customDate = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        dateTextField.setText(observationsWrapper.getObservations().get(observationSelectionComboBox.getSelectedIndex()).getEntryDate().format(customDate));
        temperTextField.setText(observationsWrapper.getObservations().get(observationSelectionComboBox.getSelectedIndex()).getEntryTemp().toString());        
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
        Observation toUpdate = observationsWrapper.getObservations().get(observationSelectionComboBox.getSelectedIndex());
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
        temperatureValue.trim();
        Double doubleTempValue = 0.0;
        String treatmentValue = treatmentTextField.getText();
        String conditionValue = conditionTextField.getText();
        //check for empty fields display message dialog if empty
        if (treatmentValue.isEmpty()  || conditionValue.isEmpty() || temperatureValue.isEmpty()) 
        { 
            JOptionPane.showMessageDialog(healthPanel, "Text boxes cannot be empty");
            return false;
        }
        //check length of input
        if(temperatureValue.length()>5)
        { 
            JOptionPane.showMessageDialog(healthPanel, "Please enter a temperature value of less than 4 digits");
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
        * Inner event listener class for Add GUI button 
        */
        private class AddButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.out.println("Add");
                addButtonObservationData();            
            }
        }
        /**
        * Inner event listener class for Delete GUI button
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
        * Inner event listener class for Edit GUI button
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
        *  invokes saveFile method to open a data file for saving
        */    
        private class SaveButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                saveFile();               
            }
        }   
        
                /**
        * Inner event listener class for GUI button open
        * invokes fileOpen method to open a data file for reading
        */    
        private class OpenButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                openFile();
            }
        }
     
        /**
        * Inner event listener class for Cancel, Clear GUI button
        */  
        private class CancelButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.out.println("cancel");
                clearButtonClearObservation();
            }
        } 
        /**
        * Inner event listener class for Fever Windw GUI button
        */          
         private class FeverButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.out.println("Fever Button"); 
                feverWindow.setVisible(true);                                               
            }
        }        
        /**
        * Inner event listener class for Selection GUI JComboBox
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
