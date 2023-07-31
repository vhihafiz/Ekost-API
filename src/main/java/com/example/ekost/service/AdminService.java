package com.example.ekost.service;

import com.example.ekost.entity.Admin;
import com.example.ekost.entity.Customer;
import com.example.ekost.model.Response.AdminResponse;
import com.example.ekost.model.Response.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AdminService {
    Admin createAdmin(Admin admin);
    List<AdminResponse> getAll();

    Admin getById(String id);

    void updateAdmin(Admin admin);

    void deleteAdmin(String id);
}
