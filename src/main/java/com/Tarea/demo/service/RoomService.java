package com.Tarea.demo.service;

import com.Tarea.demo.model.ResponseGeneric;
import com.Tarea.demo.model.RoomAvailabilityDTO;
import com.Tarea.demo.model.RoomModel;
import org.springframework.http.ResponseEntity;

public interface RoomService {
    ResponseEntity<ResponseGeneric<?>> guardar(RoomModel data);

    ResponseEntity<ResponseGeneric<?>> listar();

    ResponseEntity<ResponseGeneric<?>> actualziaDisponible(Integer id, RoomAvailabilityDTO data);
}
