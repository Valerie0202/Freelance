package org.perscholas.freelance.form;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ClientFormBean {

    private Integer id;

    @NotEmpty(message = "First name must not be empty.")
    @Length(max = 45, message = "First name must be less than 45 characters in length.")
    private String firstName;

    @Length(max = 45, message = "Last name must be less than 45 characters in length.")
    private String lastName;

    @Length(max = 100, message = "Email address must be under 100 characters in length.")
    @Pattern(regexp = "^.+@.+$" , message = "Invalid email format")
    private String email;

    @Length(max = 64, message = "Address line 1 must be less than 64 characters in length.")
    private String address1;

    @Length(max = 64, message = "Address line 2 must be less than 64 characters in length.")
    private String address2;

    @Pattern(regexp = "^\\(([0-9]{3})\\) ([0-9]{3})-([0-9]{4})$",
            message="Please enter a valid phone number.")
    private String phone;

    @Length(max = 500, message = "Notes must be less than 500 characters in length.")
    private String notes;

}
