package com.example.ekost.service.impl;

import com.example.ekost.entity.Admin;
import com.example.ekost.entity.Customer;
import com.example.ekost.model.Response.AdminResponse;
import com.example.ekost.model.Response.CustomerResponse;
import com.example.ekost.repository.AdminRepository;
import com.example.ekost.repository.CustomerRepository;
import com.example.ekost.service.AdminService;
import com.example.ekost.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.saveAndFlush(admin);
    }

    @Override
    public List<AdminResponse> getAll() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminResponse> adminResponses = new ArrayList<>();

        for (Admin admin : admins) {
            adminResponses.add(AdminResponse.builder()
                    .email(admin.getEmail())
                    .address(admin.getAddress())
                    .build());
        }
        return adminResponses;
    }

    @Override
    public Admin getById(String id) {
        Optional<Admin> admin = adminRepository.findBy(id);
        if(admin.isPresent()){
            return admin.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id admin not found");
        }


    }

    @Override
    public void updateAdmin(Admin admin) {
        getById(admin.getId());
        adminRepository.updateAdmin(admin.getId(), admin.getAddress(), admin.getEmail());
    }

    @Override
    public void deleteAdmin(String id) {
        Admin admin = getById(id);
        adminRepository.deleteAdmin(admin.getId());
    }
}