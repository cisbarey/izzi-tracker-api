package com.izzi.iptracker.client;

import com.izzi.iptracker.dto.response.IPGeoLocationResponse;
import com.izzi.iptracker.exception.IPNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IPInformationClient {

    private final RestTemplate restTemplate;
    @Value("${ip_information.host}")
    private String host;

    public IPInformationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public IPGeoLocationResponse getIpInformation(String ip) {
        String url = String.join("", "http://", this.host, "/json/", ip);
        ResponseEntity<IPGeoLocationResponse> response = this.restTemplate.getForEntity(url, IPGeoLocationResponse.class);

        if (response.getStatusCode() == HttpStatus.NOT_FOUND || response.getBody() == null
                || response.getBody().getStatus().equalsIgnoreCase("fail")) {
            throw new IPNotFoundException("IP information not found for: " + ip);
        } else if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            throw new RuntimeException("Error retrieving IP information for: " + ip);
        }
        return response.getBody();
    }
}
