package com.Tarea.demo.controller;

import com.Tarea.demo.model.ReservationModel;
import com.Tarea.demo.model.ResponseGeneric;
import com.Tarea.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Reservation")
public class Reservation {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<ResponseGeneric<?>> listarReservacion(){
        return reservationService.lisatrReservacion();
    }

    @PostMapping("/reservations")
    public ResponseEntity<ResponseGeneric<?>> registrarresevation(@RequestBody ReservationModel dto){
        return reservationService.registrarresevation(dto);
    }
}
