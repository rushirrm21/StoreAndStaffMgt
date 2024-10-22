package com.csv_db.services_store;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csv_db.model_store.Store;
import com.csv_db.repositories_store.StoreRepository;

@Service
public class ProcessStore {

	@Autowired
	private StoreRepository storeRepository;

	public Optional<Store> getStoreById(String storeId) {
		return storeRepository.findById(storeId);
	}
}
