package com.Tarea.demo.model;

import com.Tarea.demo.entity.Room;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomModel {
    private Integer id;
    private String number;
    private String type;
    private Float price;
    private Boolean available;

    public RoomModel(Room entity) {
        this.id = entity.getId();
        this.number = entity.getNumber();
        this.type = entity.getType();
        this.price = entity.getPrice();
        this.available = entity.getAvailable();
    }
}
