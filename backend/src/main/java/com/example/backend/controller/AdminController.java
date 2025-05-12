package com.example.backend.controller;

import com.example.backend.enums.ResultCode;
import com.example.backend.model.ResponseVO;
import com.example.backend.security.CustomUserDetails;
import com.example.backend.service.AdminService;
import com.example.backend.service.impl.RecordServiceDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/records")
    public ResponseEntity<ResponseVO> getAllRecords(@AuthenticationPrincipal CustomUserDetails userDetails) {
        logger.info("Calling admin/getAllRecords()");
        return new ResponseEntity<>(new ResponseVO(ResultCode.RSC_2000, adminService.getAllRecords()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<ResponseVO> getAllUsers(@AuthenticationPrincipal CustomUserDetails userDetails) {
        logger.info("Calling admin/getAllUsers()");
        return new ResponseEntity<>(new ResponseVO(ResultCode.RSC_2000, adminService.getAllUsers()), HttpStatus.OK);
    }
}