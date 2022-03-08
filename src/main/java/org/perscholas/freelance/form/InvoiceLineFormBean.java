package org.perscholas.freelance.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class InvoiceLineFormBean {

    private Integer id;

    @NotEmpty(message = "Please give invoice line item a title.")
    @Length(max = 100, message = "Title must be less than 100 characters.")
    private String item;

    @Max(value = 99999999, message = "Price must be less than $10,000,000.")
    private Double price;

    @Length(max = 200, message = "Notes must be under 200 characters in length.")
    private String notes;

    @Max(value = 9999999, message = "Quantity must be less than 1,000,000.")
    private Double quantity;

    private Integer invoiceId;

}
