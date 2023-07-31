package com.example.ekost.controller;

import com.example.ekost.entity.Admin;
import com.example.ekost.model.Response.AdminResponse;
import com.example.ekost.model.Response.CommonResponse;
import com.example.ekost.model.Response.CustomerResponse;
import com.example.ekost.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/admin")

public class AdminController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<?> createNewAdmin(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<Admin>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully create new admin")
                        .data(adminService.createAdmin(admin))
                        .build());
    }


    @GetMapping
    public ResponseEntity<?> getAllAdmin() {
        List<AdminResponse> adminResponses = adminService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get all admin")
                        .data(adminResponses)
                        .build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get admin by id")
                        .data(adminService.getById(id))
                        .build());
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody Admin admin) {
        adminService.updateAdmin(admin);
        return ResponseEntity.ok("Admin updated successfully.");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable String id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully delete admin")
                        .build());
    }

}
