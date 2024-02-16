package com.izzi.iptracker.controller;

import com.izzi.iptracker.dto.request.IPInformationRequest;
import com.izzi.iptracker.dto.response.IPInformationResponse;
import com.izzi.iptracker.service.IPInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/ip-information")
public class IPInformationController {

    private final IPInformationService service;

    @Autowired
    public IPInformationController(IPInformationService service) {
        this.service = service;
    }

    @GetMapping("/lookup/{ip}")
    public IPInformationResponse getIPInformation(@PathVariable String ip) {
        return this.service.getIPInformation(ip);
    }

    @PostMapping
    public ResponseEntity<IPInformationResponse> saveIPInformation(@RequestBody IPInformationRequest request) {
        IPInformationResponse savedDocument = this.service.saveIPInformation(request);
        return ResponseEntity.ok(savedDocument);
    }

    @GetMapping
    public Page<IPInformationResponse> list(Pageable pageable) {
        return this.service.findAll(pageable);
    }
}
