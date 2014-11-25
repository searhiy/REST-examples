package examples.exceptions.domain;

import examples.exceptions.validators.Alphabetic;
import examples.exceptions.validators.MatchingPackageNumbers;
import examples.exceptions.validators.PackageNumber;
import examples.exceptions.validators.PhoneNumber;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity(name="ticket_ex")
@MatchingPackageNumbers
public class TicketEx {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Alphabetic
    private String firstName;

    @Alphabetic
    private String lastName;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private AddressEx address;

    @PhoneNumber
    private String phoneNumber;

    @PackageNumber
    private String packageNumber;

    private String confirmPackageNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressEx getAddress() {
        return address;
    }

    public void setAddress(AddressEx address) {
        this.address = address;
    }

    public String getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getConfirmPackageNumber() {
        return confirmPackageNumber;
    }

    public void setConfirmPackageNumber(String confirmPackageNumber) {
        this.confirmPackageNumber = confirmPackageNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
