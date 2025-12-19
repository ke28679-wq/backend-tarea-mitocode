package com.Tarea.demo.service.impl;

import com.Tarea.demo.entity.Room;
import com.Tarea.demo.model.ResponseGeneric;
import com.Tarea.demo.model.RoomAvailabilityDTO;
import com.Tarea.demo.model.RoomModel;
import com.Tarea.demo.repo.RoomRepo;
import com.Tarea.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepo roomRepo;

    @Override
    public ResponseEntity<ResponseGeneric<?>> guardar(RoomModel data) {
        ResponseGeneric<RoomModel> response = new ResponseGeneric<>();
        try{
            if (roomRepo.existsByNumber(data.getNumber())) {
                response.setStatus(false);
                response.setMessage("El número de habitación ya existe");
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(response);
            }

            Room saved = roomRepo.save(new Room(data));
            RoomModel result = new RoomModel(saved);

            response.setStatus(true);
            response.setMessage("Registro guardado correctamente");
            response.setData(result);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage("Error interno: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseGeneric<?>> listar() {
        ResponseGeneric<List<RoomModel>> response = new ResponseGeneric<>();

        try {
            List<Room> rooms = roomRepo.findAll();

            List<RoomModel> result = rooms.stream()
                    .map(RoomModel::new)   // constructor RoomModel(Room)
                    .collect(Collectors.toList());

            response.setStatus(true);
            response.setMessage("Listado de habitaciones");
            response.setData(result);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage("Error al listar habitaciones: " + e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseGeneric<?>> actualziaDisponible(Integer id, RoomAvailabilityDTO data) {
        ResponseGeneric<RoomModel> response = new ResponseGeneric<>();

        try {
            Room room = roomRepo.findById(id).orElseThrow(() -> new RuntimeException("La habitación no existe"));

            room.setAvailable(data.getAvailable());

            Room saved = roomRepo.save(room);

            RoomModel result = new RoomModel(saved);

            response.setStatus(true);
            response.setMessage("Disponibilidad actualizada correctamente");
            response.setData(result);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);

        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage("Error interno: " + e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }
}
