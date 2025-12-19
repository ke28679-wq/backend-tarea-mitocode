package com.Tarea.demo.entity;

import com.Tarea.demo.model.RoomModel;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "room")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number", unique = true, nullable = false)
    private String number;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Float price;

    @Column(name = "available")
    private Boolean available;

    public Room(RoomModel dto) {
        this.id = dto.getId();
        this.number = dto.getNumber();
        this.type = dto.getType();
        this.price = dto.getPrice();
        this.available = dto.getAvailable();
    }
}
