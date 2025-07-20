package com.PentaProject.demoBackend.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.PentaProject.demoBackend.Model.Save;

public interface SaveRepository extends MongoRepository<Save, Integer> {
}
