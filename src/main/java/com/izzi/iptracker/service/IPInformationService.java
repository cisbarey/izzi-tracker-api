package com.izzi.iptracker.service;

import com.izzi.iptracker.client.IPInformationClient;
import com.izzi.iptracker.document.IPInformation;
import com.izzi.iptracker.dto.request.IPInformationRequest;
import com.izzi.iptracker.dto.response.IPGeoLocationResponse;
import com.izzi.iptracker.dto.response.IPInformationResponse;
import com.izzi.iptracker.helper.IPInformationHelper;
import com.izzi.iptracker.repository.IPInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IPInformationService {

    private final IPInformationRepository repository;
    private final IPInformationClient client;
    private final IPInformationHelper helper;

    @Autowired
    public IPInformationService(IPInformationRepository repository,
                                IPInformationClient client,
                                IPInformationHelper helper) {
        this.repository = repository;
        this.client = client;
        this.helper = helper;
    }

    public IPInformationResponse getIPInformation(String ip) {
        IPGeoLocationResponse response = this.client.getIpInformation(ip);
        return this.helper.toResponse(response);
    }

    public IPInformationResponse saveIPInformation(IPInformationRequest request) {
        IPInformation document = this.helper.toDocument(request);
        document = this.repository.save(document);
        return this.helper.toResponse(document);
    }

    public Page<IPInformationResponse> findAll(Pageable pageable) {
        return this.repository.findAll(pageable).map(this.helper::toResponse);
    }

}
