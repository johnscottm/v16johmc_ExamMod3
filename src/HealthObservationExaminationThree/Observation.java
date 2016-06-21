/*v16johmc Examination Three IK1052
 */
package HealthObservationExaminationThree;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
//import java.util.Calendar;
//import java.util.Date;

/**
 * Observation class to hold health observation data and calculation methods.
 * @author John McInnes
 * @version June 25, 2016
 */
class Observation  
{
    //fields for health observation description
    private String entryTreatment;
    private LocalDateTime entryDate;
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
                this(LocalDateTime.now(),"","",0.0);
    }  
    /**
    * Observation class constructor.
    * @param sentDate new value of entryDate
    * @param enteredTreatment new value of entryTreatment
    * @param enteredCondition new value of entryCondition
    * @param enteredTemp new value of entryTemp
    */   
    public Observation(LocalDateTime sentDate, String enteredTreatment, String enteredCondition, Double enteredTemp) {
        entryDate = sentDate;
        entryTreatment = enteredTreatment; 
        entryCondition = enteredCondition;
        entryTemp = enteredTemp;                  
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
    * Observation class deep copy constructor.
    * Creates new Double and LocalDateTime objects from existing references
    * returning a true copy with no references
    * @param objectToCopy the Observation object to duplicate
    */   
    public Observation(Observation objectToCopy) {
        this.entryDate =  objectToCopy.getEntryDate().plusNanos(0);
        this.entryTreatment = objectToCopy.getEntryTreatment(); 
        this.entryCondition = objectToCopy.getEntryCondition();
        this.entryTemp = Double.valueOf(objectToCopy.getEntryTemp().doubleValue());                  
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
    public LocalDateTime getEntryDate() {
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
    @XmlJavaTypeAdapter( LocalDateTimeAdapter.class )
    @XmlElement( name = "Date" )
    public void setEntryDate( LocalDateTime enteredDate) {
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
     * Return a String description of the object with the entryDate 
     * @return String for Observation object field only for entryDate 
     */
    @Override
    public String toString() {
            DateTimeFormatter customDate = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
            return  entryDate.format(customDate) ;
    }   
}
