package com.example.backend.controller;

import com.example.backend.enums.ResultCode;
import com.example.backend.model.ResponseVO;
import com.example.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/records")
    public ResponseEntity<ResponseVO> getAllRecords() {
        return new ResponseEntity<>(new ResponseVO(ResultCode.RSC_2000, adminService.getAllRecords()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<ResponseVO> getAllUsers() {
        return new ResponseEntity<>(new ResponseVO(ResultCode.RSC_2000, adminService.getAllUsers()), HttpStatus.OK);
    }
}