package com.Tarea.demo.model;

import com.Tarea.demo.entity.Reservation;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationModel {
    private Integer id;
    private String customerName;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Integer roomId;

    public ReservationModel(Reservation entity) {
        this.id = entity.getId();
        this.customerName = entity.getCustomerName();
        this.checkInDate = entity.getCheckInDate();
        this.checkOutDate = entity.getCheckOutDate();
        this.roomId = entity.getRoom().getId();
    }
}

