package org.perscholas.freelance.form;


import lombok.Getter;
import lombok.Setter;

// TODO implement validation

@Getter
@Setter
public class ClientFormBean {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address1;
    private String address2;
    private String phone;
    private String notes;

}
