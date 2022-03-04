package org.perscholas.freelance.form;

import lombok.Getter;
import lombok.Setter;
import org.perscholas.freelance.database.entity.Client;

import java.util.Date;

// TODO implement validation

@Getter
@Setter
public class InvoiceFormBean {

    private Integer id;
    private String date;
    private String notes;
    private String title;
    private Client client;
    private Integer clientId;
    private String clientName;
    private Double invoiceSubtotal;
    private Double tax;
    private Double invoiceTotal;

}
