package ru.otus.spring.homework.spring15.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good implements Serializable {

    private static final long serialVersionUID = 3L;

    private String name;
    private BigDecimal price;
    private Float weight;
}
