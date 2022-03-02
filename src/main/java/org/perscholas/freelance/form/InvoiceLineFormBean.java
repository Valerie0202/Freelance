package org.perscholas.freelance.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceLineFormBean {

    private Integer id;
    private String item;
    private Double price;
    private String notes;
    private Double quantity;
    private Integer invoiceId;

}
