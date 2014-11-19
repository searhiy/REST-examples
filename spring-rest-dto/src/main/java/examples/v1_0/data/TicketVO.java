package examples.v1_0.data;

import examples.v1_0.validators.Alphabetic;
import examples.v1_0.validators.MatchingPackageNumbers;
import examples.v1_0.validators.PackageNumber;
import examples.v1_0.validators.PhoneNumber;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;

@MatchingPackageNumbers
public class TicketVO {

    private Long id;

    @Alphabetic
    private String firstName;

    @Alphabetic
    private String lastName;

    @Valid
    private AddressVO address;

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

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO address) {
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
