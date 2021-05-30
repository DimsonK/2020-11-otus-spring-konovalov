package ru.otus.spring.homework.springproject.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.homework.springproject.models.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String id;
    private String orderNumber;
    private LocalDateTime orderTime;
    private OrderStatus status;
    private List<String> bookIds;
    private String userId;
}
