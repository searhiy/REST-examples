package examples.client.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by serhii on 19.11.14.
 */
@Entity
@Table(name = "items")
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.NONE)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;
    @JsonProperty
    @Column
    private String name;
    @JsonProperty
    @Column
    private String param1;
    @JsonProperty
    @Column
    private String param2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
}
