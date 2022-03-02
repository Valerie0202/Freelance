package org.perscholas.freelance.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "invoice_line")
public class InvoiceLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "item")
    private String item;

    @Column(name = "notes")
    private String notes;

    @Column(name = "price",
            columnDefinition = "DECIMAL")
    private Double price;

    @Column(name = "quantity",
            columnDefinition = "DECIMAL")
    private Double quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}