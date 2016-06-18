/*v16johmc Examination Three IK1052
 */
package HealthObservationExaminationThree;

import java.time.LocalDateTime;
//import java.util.Calendar;
//import java.util.Date;

/**
 * Observation class to hold health observation data and calculation methods.
 * @author John McInnes
 */
class Observation  
{
    //fields for health observation description
    private String entryTreatment;
//        private Date entryDate;
    //private Calendar entryDate;
    //private LocalDateTime entryDate;
    private String entryDate;
    private String entryCondition ;
    private Double entryTemp;
 
    /**
    * Observation class constructor.
    * Empty parameter list required by JAXB library
    * to construct objects from XML
    */   
    public Observation()
    {
        //this(Calendar.getInstance().getTime(),"","",0.0);
                this("","","",0.0);

    }
     public Observation(String sentDate, String enteredTreatment, String enteredCondition, Double enteredTemp) {
        entryDate = sentDate;
        entryTreatment = enteredTreatment; 
        entryCondition = enteredCondition;
        entryTemp = enteredTemp;                    
    }   
    /**
    * Observation class constructor.
    * @param sentDate new value of entryDate
    * @param enteredTreatment new value of entryTreatment
    * @param enteredCondition new value of entryCondition
    * @param enteredTemp new value of entryTemp
    */   
    public Observation(LocalDateTime sentDate, String enteredTreatment, String enteredCondition, Double enteredTemp) {
        entryCondition = enteredCondition;
        //entryDate = Calendar.getInstance();
 //       entryDate = LocalDateTime.now();
 //       entryDate.setTime(sentDate);
        entryDate = sentDate.toString();
        entryTemp = enteredTemp;
        entryTreatment = enteredTreatment;                     
    }
    /**
    * Observation class constructor.
    * @param enteredTreatment new value of entryTreatment
    * @param enteredCondition new value of entryCondition
    * @param enteredTemp new value of entryTemp
    */
    public Observation(String enteredTreatment, String enteredCondition, Double enteredTemp) {
        this(LocalDateTime.now(), enteredTreatment, enteredCondition, enteredTemp);                    
    }


    /**
     * Get the value of entryCondition
     * @return the value of entryCondition
     */
    public String getEntryCondition() {
        return entryCondition;
    }
    
    /**
     * Get the value of entryDate
     * @return the value of entryDate
     */
    public String getEntryDate() {
        return entryDate;
    }
    
    /**
     * Get the value of entryTemp
     * @return the value of entryTemp
     */
    public Double getEntryTemp() {
        return entryTemp;
    }
    /**
     * Get the value of entryTreatment
     * @return the value of entryTreatment
     */
    public String getEntryTreatment() {
        return entryTreatment;
    }
    /**
     * Set the value of entryDate
     */
    public void setEntryDate( String enteredDate) {
         entryDate = enteredDate;
    }
    
    /**
     * Set the value of entryCondition
     * @param entryCondition new value of entryCondition
     */
    public void setEntryCondition(String entryCondition) {
        this.entryCondition = entryCondition;
    }
    
    /**
     * Set the value of entryCondition
     * @param entryTemp new value of entryTemp
     */
    public void setEntryTemp(Double entryTemp) {
        this.entryTemp = entryTemp;
    }
    
    /**
     * Set the value of entryTreatment
     * @param entryTreatment new value of entryTreatment
     */
    public void setEntryTreatment(String entryTreatment) {
        this.entryTreatment = entryTreatment;
    }

         /**
     * Return a String description of the object with the entryDate and entryTemp
     * @return String for Observation object field only for entryDate 
     */
    @Override
    public String toString() {
            return  entryDate.toString() ;
    }   
}
