/*v16johmc Examination Three IK1052
 */
package HealthObservationExaminationThree;

import java.util.Comparator;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A wrapper class for the List of root element Observation.
 * allows better operation of JAXB library to save XML text file of data
 * @author John McInnes
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
     * @return  observations 
     */
    public List<Observation> getObservations() 
    {
        return observations;
    }

    /**
     * sets the List observations in the class equal to the sent List via parameter
     * @param observations
     */
    public void setObservations(List<Observation> observations) 
    {
        this.observations = observations;
    }
    /**
     * Returns the average temperature of all observations
     */
    public Double getObservationsAverageTemp() 
    {
        if(!observations.isEmpty())
        {
            Double averageTemp = 0.0;
            int numObser = 0;
            for (Object loopObject : observations)
            {
                Observation currentObservation = (Observation)loopObject;          
                averageTemp =+currentObservation.getEntryTemp();
                numObser++;       
            }
            return averageTemp = averageTemp/numObser;
        }
        return 0.0;
    }
    /**
     * Returns the highest temperature of all observations
     */
    public Double getObservationsHighestTemp() 
    {
        class temperatureCompare implements Comparator<Observation>
        {
            public int compare(Observation a, Observation b)
            {
                    return (a.getEntryTemp().compareTo(b.getEntryTemp()));
            }
        
        }
        if(!observations.isEmpty())
        {
//            Double highestTemp = 0.0;
            
                    observations.sort(new temperatureCompare());
                     Double highestTemp = observations.get(0).getEntryTemp();
                     System.out.println("highest temp" +highestTemp);
                     return highestTemp;
//            int numObser = 0;
//            
//            for (Object loopObject : observations)
//            {
//                Observation currentObservation = (Observation)loopObject;          
//                highestTemp =+currentObservation.getEntryTemp();
//                numObser++;       
//            }
//            return averageTemp = averageTemp/numObser;
        }
        return 0.0;
    }
}
