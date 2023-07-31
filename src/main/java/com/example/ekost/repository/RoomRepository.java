package com.example.ekost.repository;

import com.example.ekost.entity.Customer;
import com.example.ekost.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface RoomRepository extends JpaRepository<Room, String> {

    @Query(value = "SELECT * FROM m_room", nativeQuery = true)
    List<Room> findAll();

    @Query(value = "SELECT * FROM m_room WHERE id = :id", nativeQuery = true)
    Optional<Room> findBy(String id);

    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "UPDATE m_room SET description = :description WHERE id = :id", nativeQuery = true)
    void updateRoom(@Param("id") String id, @Param("description") String description);
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "DELETE FROM m_room WHERE id = :id", nativeQuery = true)
    void deleteRoom(@Param("id") String id);

}
