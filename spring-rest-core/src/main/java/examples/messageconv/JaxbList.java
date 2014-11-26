package examples.messageconv;

import examples.messageconv.domain.Organization;

import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * Created by serhii on 26.11.14.
 */
@XmlRootElement(name="data")
/*@XmlSeeAlso({User.class, LogEntry.class})*/
@XmlSeeAlso({Organization.class})
public class JaxbList<T> {
    protected List<T> data;

    public JaxbList(){}

    public JaxbList(List<T> data){
        this.data = data;
    }

    /*
    alternative:
    @XmlElementRefs({
        @XmlElementRef(name="item", type=Object.class),
        @XmlElementRef(name="user", type=User.class),
        @XmlElementRef(name="entry", type=LogEntry.class)
    })
    */
    //@XmlElementRef(name="item", type=Organization.class)
    @XmlMixed
    public List<T> getData(){
        return data;
    }
}
