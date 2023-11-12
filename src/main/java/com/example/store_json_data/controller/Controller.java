package com.example.store_json_data.controller;

import com.example.store_json_data.entity.StoreData;
import com.example.store_json_data.repository.Repository;
import com.example.store_json_data.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    /*
    request body would be like that
    {
    "jsonData": "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}"
    }
     */
    @Autowired
    private Service service;
    @Autowired
    private Repository repository;

    @PostMapping
    public ResponseEntity<StoreData> saveEntity(@RequestBody StoreData storeData) {
        StoreData savedEntity = service.saveEntity(storeData);
        return ResponseEntity.ok(savedEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreData> getEntityById(@PathVariable Long id) {
        StoreData entity = service.getEntityById(id);
        if (entity != null) {
            return ResponseEntity.ok(entity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<StoreData>> getAllEntities() {
        List<StoreData> entities = service.getAllEntities();
        return ResponseEntity.ok(entities);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreData> updateEntity(
            @PathVariable Long id,
            @RequestBody String newJsonData) {

        StoreData updatedEntity = service.updateEntity(id, newJsonData);

        if (updatedEntity != null) {
            return ResponseEntity.ok(updatedEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<StoreData> findStoreDataByName(@PathVariable String name) {
        StoreData storeData = service.findStoreDataByName(name);

        if (storeData != null) {
            return new ResponseEntity<>(storeData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findByNameAndCity/{name}/{city}")
    public ResponseEntity<List<StoreData>> findStoreDataByNameAndCity(@PathVariable String name, @PathVariable String city) {
        List<StoreData> result = service.findStoreDataByNameAndCity(name, city);

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}


