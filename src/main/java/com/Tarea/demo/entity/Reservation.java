package com.Tarea.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "reservation")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Access(AccessType.FIELD)
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customername")
    private String customerName;

    @Column(name = "checkindate")
    private LocalDateTime checkInDate;

    @Column(name = "checkoutdate")
    private LocalDateTime checkOutDate;

    @Column(name = "room", nullable = false)
    private Integer roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    private Room room;
}
