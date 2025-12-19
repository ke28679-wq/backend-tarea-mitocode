package com.Tarea.demo.controller;

import com.Tarea.demo.model.ResponseGeneric;
import com.Tarea.demo.model.RoomAvailabilityDTO;
import com.Tarea.demo.model.RoomModel;
import com.Tarea.demo.service.RoomService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Room")
public class Room {

    @Autowired
    private RoomService roomService;

    @PostMapping("/rooms")
    public ResponseEntity<ResponseGeneric<?>> guardar(@RequestBody RoomModel data){
        return roomService.guardar(data);
    }

    @GetMapping("/rooms")
    public ResponseEntity<ResponseGeneric<?>> listar(){
        return roomService.listar();
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<ResponseGeneric<?>> actualizarDisponiblidad(@PathVariable Integer id, @RequestBody RoomAvailabilityDTO data){
        return roomService.actualziaDisponible(id, data);
    }


}
