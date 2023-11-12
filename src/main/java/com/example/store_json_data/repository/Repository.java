package com.example.store_json_data.repository;

import com.example.store_json_data.entity.StoreData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<StoreData,Long> {
    @Query("SELECT e FROM StoreData e WHERE JSON_EXTRACT(e.jsonData, '$.name') = :name ")

    StoreData findStoreDataByName(String name);
    @Query("SELECT e FROM StoreData e WHERE JSON_EXTRACT(e.jsonData, '$.name') = :name AND JSON_EXTRACT(e.jsonData, '$.city') = :city")
    List<StoreData> findStoreDataByNameAndCity(@Param("name") String name, @Param("city") String city);
}
