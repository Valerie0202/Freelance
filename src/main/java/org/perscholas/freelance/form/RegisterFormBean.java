package org.perscholas.freelance.form;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.perscholas.freelance.validation.EmailUnique;
import org.perscholas.freelance.validation.TwoFieldsAreEqual;
import org.perscholas.freelance.validation.UsernameUnique;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@TwoFieldsAreEqual(fieldOneName = "confirmPassword", fieldTwoName = "password", message = "Passwords must match.")
public class RegisterFormBean {

    private Integer id;

    @NotEmpty(message = "Email is required.")
    @Pattern(regexp = "^.+@.+$" , message = "Invalid email format")
    @EmailUnique(message = "Email is already registered to an account.")
    private String email;

    @Length(min = 0, max = 45,
            message = "First name must be shorter than 45 characters.")
    private String firstName;

    @Length(min = 0, max = 45,
            message = "Last name must be shorter than 45 characters.")
    private String lastName;

    @NotEmpty(message = "Username is required")
    @Length(min = 6, max = 45,
            message = "Username must be between 6 and 45 characters in length.")
    @UsernameUnique(message = "This username is already taken.")
    private String username;

    @Length(min = 0, max = 64,
            message = "Address line 1 must be shorter than 64 characters.")
    private String address1;

    @Length(min = 0, max = 64,
            message = "Address line 2 must be shorter than 64 characters.")
    private String address2;

    @Pattern(regexp = "^\\(([0-9]{3})\\)\\s([0-9]{3})-([0-9]{4})$",
            message="Please enter a valid phone number.")
    private String phone;

    private String password;
    private String confirmPassword;

    private List<String> errorMessages = new ArrayList<>();



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}