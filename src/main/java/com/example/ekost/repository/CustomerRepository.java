package com.example.ekost.repository;

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
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = "SELECT * FROM m_customer", nativeQuery = true)
    List<Customer> findAll();

    @Query(value = "SELECT * FROM m_customer WHERE id = :id", nativeQuery = true)
    Optional<Customer> findBy(String id);

    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "UPDATE m_customer SET name = :name, address = :address, email = :email WHERE id = :id", nativeQuery = true)
    void updateCustomer(@Param("id") String id, @Param("name") String name, @Param("address") String address, @Param("email") String email);

    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query(value = "DELETE FROM m_customer WHERE id = :id", nativeQuery = true)
    void deleteCustomer(@Param("id") String id);



}
