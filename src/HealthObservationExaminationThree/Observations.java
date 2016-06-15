/*v16johmc Examination Three IK1052
 */
package HealthObservationExaminationThree;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A wrapper class for the List of root element Observation
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
}
