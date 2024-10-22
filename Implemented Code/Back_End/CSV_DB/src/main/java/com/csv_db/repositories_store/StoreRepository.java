package com.csv_db.repositories_store;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csv_db.model_store.Store;

public interface StoreRepository extends JpaRepository<Store, String> {
}
