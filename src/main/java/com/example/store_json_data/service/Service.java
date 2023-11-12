package com.example.store_json_data.service;

import com.example.store_json_data.entity.StoreData;
import com.example.store_json_data.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Repository repository;

    public StoreData saveEntity(StoreData storeData) {
        return repository.save(storeData);
    }

    public StoreData getEntityById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<StoreData> getAllEntities() {
        return repository.findAll();
    }

    public void deleteEntity(Long id) {
        repository.deleteById(id);
    }

    public StoreData updateEntity(Long id, String newJsonData) {
        StoreData existingEntity = repository.findById(id).orElse(null);

        if (existingEntity != null) {
            existingEntity.setJsonData(newJsonData);
            return repository.save(existingEntity);
        } else {
            return null; // Entity with the given ID not found
        }
    }

    public StoreData findStoreDataByName(String name){
        return repository.findStoreDataByName(name);
    }

    public List<StoreData> findStoreDataByNameAndCity(String name,String city){
        return repository.findStoreDataByNameAndCity(name,city);
    }
}
