package org.perscholas.freelance.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.perscholas.freelance.database.entity.Client;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class InvoiceFormBean {

    private Integer id;

    @Pattern(regexp = "^([0-9]{4})-([0-9]{2})-([0-9]{2})$", message = "Date must be in format: YYYY-MM-DD")
    private String date;

    @Length(max = 500, message = "Notes must be less than 500 characters in length.")
    private String notes;

    @Length(max = 100, message = "Project title must be less than 100 characters in length.")
    @NotEmpty(message = "Project title must not be empty.")
    private String title;

    private Client client;

    private Integer clientId;

    private String clientName;

    private Double invoiceSubtotal;

    @Max(value = 999, message = "Tax must be less than 1000.")
    private Double tax;

    private Double invoiceTotal;

}
