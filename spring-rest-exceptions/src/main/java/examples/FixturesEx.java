package examples;

import examples.exceptions.domain.AddressEx;
import examples.exceptions.domain.TicketEx;
import examples.service.TicketExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class FixturesEx {

    @Autowired
    private TicketExService itemService;

    @Autowired
    private MessageSourceAccessor messageAccessor;

    @PostConstruct
    public void init() {
        List<TicketEx> tickets = new ArrayList<>();
        tickets.add(createTicket("firstName", "lastName",
                "AU12345678", "AU12345678",
                "(380)-123-1235", "street",
                "city", "12345"));
        itemService.save(tickets);
        // AU-123-45678
    }

    private TicketEx createTicket(String firstName, String lastName,
                                  String packageNum, String confirmPN,
                                  String phoneNum, String street,
                                  String city, String zip) {
        TicketEx ticket = new TicketEx();

        ticket.setPackageNumber(packageNum);
        ticket.setConfirmPackageNumber(confirmPN);
        ticket.setFirstName(firstName);
        ticket.setLastName(lastName);
        ticket.setPhoneNumber(phoneNum);

        AddressEx addressEx = new AddressEx();
        addressEx.setStreet(street);
        addressEx.setCity(city);
        addressEx.setZip(zip);
        ticket.setAddress(addressEx);

        //String message = messageAccessor.getMessage("error.validation.package_number_do_not_match");

        return ticket;
    }

}
