package com.izzi.iptracker.repository;

import com.izzi.iptracker.document.IPInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPInformationRepository extends MongoRepository<IPInformation, String> {

}
