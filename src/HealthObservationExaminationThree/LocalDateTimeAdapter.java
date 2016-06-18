/*v16johmc Examination Three IK1052
 */
package HealthObservationExaminationThree;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * A class to assist JAXB library in creating LocalDateTime objects
 * as it has no built in recognition of the class
 * @author John McInnes
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime>
{
    /**
     * override unmarshal method to use LocalDateTime parse method instead
     * @param fromFile
     * @return 
     * @throws java.lang.Exception 
     */
    @Override
    public LocalDateTime unmarshal(String fromFile) throws Exception
    {
        return LocalDateTime.parse(fromFile);
    }
    /**
     * override marshal, marshal methods to use LocalDateTime method toString
     * @param toFile
     * @return String
     * @throws java.lang.Exception
     */
    @Override
    public String marshal(LocalDateTime toFile) throws Exception
    {
        return toFile.toString();
    }
}