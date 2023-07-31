package com.example.ekost.repository;

import com.example.ekost.entity.Admin;
import com.example.ekost.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface AdminRepository extends JpaRepository<Admin,String> {
    @Query(value = "SELECT * FROM m_admin", nativeQuery = true)
    List<Admin> findAll();

    @Query(value = "SELECT * FROM m_admin WHERE id = :id", nativeQuery = true)
    Optional<Admin> findBy(String id);

    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "UPDATE m_admin SET address = :address, email = :email WHERE id = :id", nativeQuery = true)
    void updateAdmin(@Param("id") String id, @Param("address") String address, @Param("email") String email);

    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "DELETE FROM m_admin WHERE id = :id", nativeQuery = true)
    void deleteAdmin(@Param("id") String id);
}
