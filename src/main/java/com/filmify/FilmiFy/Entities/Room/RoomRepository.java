package com.filmify.FilmiFy.Entities.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE r.room_code = :code")
    Optional<Room> findRoomByCode(String code);
    @Query("SELECT r FROM Room r WHERE r.room_name = :name")
    Optional<Room> findRoomByName(String name);
}
