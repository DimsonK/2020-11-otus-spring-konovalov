package ru.otus.spring.homework.spring15.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 2L;

    private Good good;
    private Integer quantity;
    private String orderNumber;
}
