package com.Tarea.demo.service;

import com.Tarea.demo.model.ReservationModel;
import com.Tarea.demo.model.ResponseGeneric;
import org.springframework.http.ResponseEntity;

public interface ReservationService {
    ResponseEntity<ResponseGeneric<?>> lisatrReservacion();

    ResponseEntity<ResponseGeneric<?>> registrarresevation(ReservationModel dto);
}
