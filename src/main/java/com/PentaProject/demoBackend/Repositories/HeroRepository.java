package com.PentaProject.demoBackend.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.PentaProject.demoBackend.Model.Hero;

public interface HeroRepository extends MongoRepository<Hero, Integer> {
}
