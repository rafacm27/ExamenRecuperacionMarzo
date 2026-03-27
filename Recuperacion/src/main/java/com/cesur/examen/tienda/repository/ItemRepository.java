package com.cesur.examen.tienda.repository;

import com.cesur.examen.tienda.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findByCategory(String category);
    Optional<Item> findById(String id);
    Optional<Item> findByEAN(String ean);
}