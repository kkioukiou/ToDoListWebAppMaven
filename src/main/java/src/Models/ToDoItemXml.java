package src.Models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ToDoItemXml {
    @XmlElement public int id;
    @XmlElement public String value;
    @XmlElement public boolean check;

}
