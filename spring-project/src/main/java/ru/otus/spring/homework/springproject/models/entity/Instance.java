package ru.otus.spring.homework.springproject.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "INSTANCE_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "INSTANCES")
public class Instance extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSTANCE_SEQUENCE")
    private Long id;

    @Column(name = "INVENTORY_NUMBER", nullable = false, unique = true)
    private String inventoryNumber;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @OneToMany(mappedBy = "instance", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<IssueInstances> issues;

}
