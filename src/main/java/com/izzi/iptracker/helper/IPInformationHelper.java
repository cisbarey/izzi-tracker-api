package com.izzi.iptracker.helper;

import com.izzi.iptracker.document.IPInformation;
import com.izzi.iptracker.dto.request.IPInformationRequest;
import com.izzi.iptracker.dto.response.IPGeoLocationResponse;
import com.izzi.iptracker.dto.response.IPInformationResponse;
import org.springframework.stereotype.Component;

@Component
public class IPInformationHelper {

    public IPInformationResponse toResponse(IPInformation document) {
        return IPInformationResponse.builder()
                .city(document.getCity())
                .country(document.getCountry())
                .lat(document.getLat())
                .lon(document.getLon())
                .ip(document.getIp())
                .zip(document.getZip())
                .timezone(document.getTimezone())
                .regionName(document.getRegion_name())
                .build();
    }

    public IPInformationResponse toResponse(IPGeoLocationResponse response) {
        return IPInformationResponse.builder()
                .city(response.getCity())
                .country(response.getCountry())
                .lat(response.getLat())
                .lon(response.getLon())
                .ip(response.getQuery())
                .zip(response.getZip())
                .timezone(response.getTimezone())
                .regionName(response.getRegionName())
                .build();
    }

    public IPInformation toDocument(IPInformationRequest request) {
        return IPInformation.builder()
                .city(request.getCity())
                .country(request.getCountry())
                .lat(request.getLat())
                .lon(request.getLon())
                .zip(request.getZip())
                .ip(request.getIp())
                .region_name(request.getRegionName())
                .timezone(request.getTimezone())
                .build();
    }

}
