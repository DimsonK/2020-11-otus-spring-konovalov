package ru.otus.spring.homework.spring15.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<InvoiceItem> productItems;
    private String orderNumber;
}
