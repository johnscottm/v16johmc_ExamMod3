/*v16johmc Examination Three IK1052
 */
package HealthObservationExaminationThree;

import java.util.Calendar;
import java.util.Date;

/**
 * Observation class to hold health observation data and calculation methods.
 * @author John McInnes
 */
class Observation  
{
    //fields for health observation description
    private String entryTreatment;
//        private Date entryDate;
    private Calendar entryDate;
    private String entryCondition ;
    private Double entryTemp;
 
    /**
    * Observation class constructor.
    * Empty parameter list required by JAXB library
    * to construct objects from XML
    */
    
    public Observation()
    {
        this(Calendar.getInstance().getTime(),"","",0.0);
    }
    
    /**
    * Observation class constructor.
    * @param sentDate new value of entryDate
    * @param enteredTreatment new value of entryTreatment
    * @param enteredCondition new value of entryCondition
    * @param enteredTemp new value of entryTemp
    */   
    public Observation(Date sentDate, String enteredTreatment, String enteredCondition, Double enteredTemp) {
        entryCondition = enteredCondition;
        entryDate = Calendar.getInstance();
        entryDate.setTime(sentDate);
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
        this(new Date(), enteredTreatment, enteredCondition, enteredTemp);                    
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
    public Calendar getEntryDate() {
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
    public void setEntryDate(Calendar enteredDate) {
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
    //        return "Observation{" + "entryTreatment=" + entryTreatment 
    //                + ", entryDate=" + entryDate.getTime() + ", entryCondition=" 
    //                + entryCondition + ", entryTemp=" + entryTemp + '}';
            return  entryDate.getTime() +"        " + entryTemp ;
    }   
}
