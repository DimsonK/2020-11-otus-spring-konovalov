package ru.otus.spring.homework.springproject.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "ORDER_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "ORDERS")
public class Order extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQUENCE")
    private Long id;

    private LocalDateTime orderTime;

}
