package com.PentaProject.demoBackend.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.PentaProject.demoBackend.Model.Party;

public interface PartyRepository extends MongoRepository<Party, Integer> {

}
