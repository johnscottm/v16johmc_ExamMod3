/*v16johmc Examination Three IK1052
 */
package HealthObservationExaminationThree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A wrapper class for the List of root element Observation.
 * allows better operation of JAXB library to save XML text file of data
 * Necessary to allow JAXB library to read and create objects to and from XML text
 * although a java library it breaks object oriented encapsulation principles in usage
 * by giving direct access to its private fields
 * @author John McInnes
 * @version June 25, 2016
 */
//JAXB annotations for saving and recreating objects using XML text file
@XmlRootElement(name = "observations")
@XmlAccessorType (XmlAccessType.FIELD)

public class Observations 
{
//JAXB annotations for XML markup
@XmlElement(name = "observation")
    private List<Observation> observations = null;
    
    /**
     * returns the List observations
     * here for JAXB operation
     * @return  observations a List of the Observation objects
     */
    public List<Observation> getObservations() 
    {
        return observations;
    }
    /**
     * returns a deep copy of the List observations
     * @return  observations a List of the Observation objects
     */
    public List<Observation> getCopyObservations(List<Observation> listToCopy) 
    {
        List<Observation> deepCopy =  new ArrayList<>();
        for(Observation originalObservation : listToCopy)
        {
            Observation ObservationCopy = new Observation(originalObservation);
            deepCopy.add(ObservationCopy);        
        }
        return deepCopy;
    }
    /**
     * sets the List observations in the class equal to the sent List via parameter
     * @param observations is the List of Observation objects to use
     */
    public void setObservations(List<Observation> observations) 
    {
        this.observations = observations;
    }
    /**
     * Returns the average temperature of all observations
     * @return a Double object
     */
    public Double getObservationsAverageTemp() 
    {
        Double averageTemp = 0.0;
        Double totalTemp = 0.0;
        if(!observations.isEmpty())
        {           
            int numObser = 0;
            for (Observation currentObservation : observations)
            {       
                totalTemp = totalTemp + currentObservation.getEntryTemp();
                numObser++;   
                      System.out.println("number  " + numObser);
            }            
            averageTemp = totalTemp/numObser;
            System.out.println("total " + totalTemp.toString());
            System.out.println("average" + averageTemp.toString());
            return averageTemp ;
        }
        return averageTemp;
    }
    /**
     * Returns the highest temperature of all observations
     */
    public Double getObservationsHighestTemp() 
    {
        //inner comparator based on temperature of Observation record
        class temperatureCompare implements Comparator<Observation>
        {
            public int compare(Observation a, Observation b)
            {
                    return (a.getEntryTemp().compareTo(b.getEntryTemp()));
            }       
        }
        Double highestTemp = 0.0;
        if(!observations.isEmpty())
        {
            Observation high = Collections.max(observations, new temperatureCompare());
            highestTemp = high.getEntryTemp();
            System.out.println("highest temp" +highestTemp);
            return highestTemp;
        }
        return highestTemp;
    }
    
    public void sortObservationsByDate() 
    {
        class dateCompare implements Comparator<Observation>
        {
            public int compare(Observation a, Observation b)
            {
                    return (a.getEntryDate().compareTo(b.getEntryDate()));
            }   
        }
        observations.sort(new dateCompare());
    }
}
