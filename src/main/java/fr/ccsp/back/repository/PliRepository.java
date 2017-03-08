package fr.ccsp.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.ccsp.back.domain.Pli;

public interface PliRepository extends MongoRepository<Pli, String> {

}
