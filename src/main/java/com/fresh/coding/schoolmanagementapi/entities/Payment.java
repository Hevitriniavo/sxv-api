package com.fresh.coding.schoolmanagementapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @Column(unique = true, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn( nullable = false)
    private Student student;

    @Column( nullable = false)
    private String paymentName;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column( nullable = false)
    private Integer price;

    @Column( nullable = false)
    private Integer month;

    @Column( nullable = false)
    private Integer amount;

}
