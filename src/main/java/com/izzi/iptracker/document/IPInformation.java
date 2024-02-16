package com.izzi.iptracker.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ip_information")
public class IPInformation {

    @Id
    private String id;
    private String country;
    private String region_name;
    private String city;
    private String zip;
    private Double lat;
    private Double lon;
    private String timezone;

    @Indexed(unique = true)
    private String ip;

}
