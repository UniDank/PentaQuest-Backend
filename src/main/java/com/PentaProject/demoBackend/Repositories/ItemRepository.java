package com.PentaProject.demoBackend.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.PentaProject.demoBackend.Model.Item;

public interface ItemRepository extends MongoRepository<Item, Integer> {
}
