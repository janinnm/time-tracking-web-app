package com.example.backend.controller;

import com.example.backend.enums.ResultCode;
import com.example.backend.model.ResponseVO;
import com.example.backend.security.CustomUserDetails;
import com.example.backend.service.RecordService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/records")
public class RecordController {

    private static final Logger logger = LoggerFactory.getLogger(RecordController.class);

    @Autowired
    private RecordService recordService;

    @PostMapping
    public ResponseEntity<ResponseVO> create(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody String request) {
        logger.info("Calling records/create()");
        return new ResponseEntity<>(new ResponseVO(ResultCode.RSC_2000, recordService.createRecord(userDetails.getUserEntity(), request)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseVO> update(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id, @RequestBody String request) {
        logger.info("Calling records/update()");
        return new ResponseEntity<>(new ResponseVO(ResultCode.RSC_2000, recordService.updateRecord(userDetails.getUserEntity(), id, request)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseVO> delete(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        logger.info("Calling records/delete()");
        return new ResponseEntity<>(new ResponseVO(ResultCode.RSC_2000, recordService.deleteRecord(userDetails.getUserEntity(), id)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVO> getById(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        logger.info("Calling records/getById()");
        return new ResponseEntity<>(new ResponseVO(ResultCode.RSC_2000, recordService.getById(userDetails.getUserEntity(), id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseVO> getAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        logger.info("Calling records/getAll()");
        return new ResponseEntity<>(new ResponseVO(ResultCode.RSC_2000, recordService.getAll(userDetails.getUserEntity())), HttpStatus.OK);
    }
}
