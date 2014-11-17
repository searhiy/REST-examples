package examples.crud.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by serhii on 06.11.14.
 */
@Entity
@Table(name = "orders")
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.NONE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    @JsonProperty
    private int id;
    @Column(name = "order_number", nullable = false, unique = true)
    @JsonProperty
    private String orderNumber;
    @Column(name = "order_date", nullable = false)
    @JsonProperty
    private Date date;
    @Column(name = "client_id", nullable = false)
    @JsonIgnore
    private int clientId;

    /*@ManyToMany
    @JoinTable(name = "order_has_products", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @JsonIgnore
    private List<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber='" + orderNumber + '\'' +
                ", date=" + date +
                '}';
    }
}
