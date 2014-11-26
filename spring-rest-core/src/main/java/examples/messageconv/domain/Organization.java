package examples.messageconv.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by serhii on 25.11.14.
 */
@Entity(name = "organizations")
@XmlRootElement
public class Organization {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String organizationName;

    public Long getId() {
        return id;
    }

    @XmlTransient
    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    @XmlElement(name = "organizationName")
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
