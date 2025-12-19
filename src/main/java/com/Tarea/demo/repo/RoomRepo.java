package com.Tarea.demo.repo;

import com.Tarea.demo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {

    boolean existsByNumber(String number);

    Optional<Room> findByNumber(String number);
}
