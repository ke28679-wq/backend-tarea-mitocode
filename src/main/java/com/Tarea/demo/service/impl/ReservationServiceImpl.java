package com.Tarea.demo.service.impl;

import com.Tarea.demo.entity.Reservation;
import com.Tarea.demo.entity.Room;
import com.Tarea.demo.model.*;
import com.Tarea.demo.repo.ReservationRepo;
import com.Tarea.demo.repo.RoomRepo;
import com.Tarea.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Override
    public ResponseEntity<ResponseGeneric<?>> lisatrReservacion() {
        ResponseGeneric<List<ReservationModel>> response = new ResponseGeneric<>();

        try {
            List<Reservation> reservations = reservationRepo.findAll();

            List<ReservationModel> result = reservations.stream()
                    .map(ReservationModel::new)
                    .collect(Collectors.toList());

            response.setStatus(true);
            response.setMessage("Listado de reservas");
            response.setData(result);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage("Error al listar reservas: " + e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseGeneric<?>> registrarresevation(ReservationModel dto) {
        ResponseGeneric<ReservationModel> response = new ResponseGeneric<>();

        try {
            // 1️⃣ Validar fechas
            if (dto.getCheckOutDate().isBefore(dto.getCheckInDate())
                    || dto.getCheckOutDate().isEqual(dto.getCheckInDate())) {

                response.setStatus(false);
                response.setMessage("La fecha de salida debe ser mayor a la fecha de ingreso");
                return ResponseEntity.badRequest().body(response);
            }

            // 2️⃣ Buscar habitación
            Room room = roomRepo.findById(dto.getRoomId())
                    .orElse(null);

            if (room == null) {
                response.setStatus(false);
                response.setMessage("La habitación no existe");
                return ResponseEntity.badRequest().body(response);
            }

            // 3️⃣ Validar disponibilidad
            if (!Boolean.TRUE.equals(room.getAvailable())) {
                response.setStatus(false);
                response.setMessage("La habitación no está disponible");
                return ResponseEntity.badRequest().body(response);
            }

            // 4️⃣ Validar cruce de fechas
            long conflicts = reservationRepo.countConflictingReservations(
                    dto.getRoomId(),
                    dto.getCheckInDate(),
                    dto.getCheckOutDate()
            );

            if (conflicts > 0) {
                response.setStatus(false);
                response.setMessage("La habitación ya está reservada en esas fechas");
                return ResponseEntity.badRequest().body(response);
            }

            // 5️⃣ Guardar reserva
            Reservation reservation = new Reservation();
            reservation.setCustomerName(dto.getCustomerName());
            reservation.setCheckInDate(dto.getCheckInDate());
            reservation.setCheckOutDate(dto.getCheckOutDate());
            reservation.setRoomId(dto.getRoomId());

            Reservation saved = reservationRepo.save(reservation);

            // 6️⃣ Marcar habitación como NO disponible
            room.setAvailable(false);
            roomRepo.save(room);

            // 7️⃣ Respuesta
            ReservationModel result = new ReservationModel();
            result.setId(saved.getId());
            result.setCustomerName(saved.getCustomerName());
            result.setCheckInDate(saved.getCheckInDate());
            result.setCheckOutDate(saved.getCheckOutDate());
            result.setRoomId(saved.getRoomId());

            response.setStatus(true);
            response.setMessage("Reserva registrada correctamente");
            response.setData(result);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage("Error al registrar reserva: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
