package examples.v2_0.data;


import examples.v2_0.validators.Email;

public class TicketVO extends examples.v1_0.data.TicketVO {

    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
