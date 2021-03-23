package ru.otus.spring.homework.spring15.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<OrderItem> orderItems;
    private String number;

    public void addItem(Good good, int quantity) {
        this.orderItems.add(new OrderItem(good, quantity, this.number));
    }

}
