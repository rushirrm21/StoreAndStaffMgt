package com.csv_db.service_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.csv_db.model_store.Store;
import com.csv_db.repositories_store.StoreRepository;
import com.csv_db.services_store.ProcessStore;

@ExtendWith(MockitoExtension.class)
class ProcessStoreTest {

	@InjectMocks
	ProcessStore processStore;

	@Mock
	StoreRepository storeRepository;

	@Test
	void getStoreByIdTest1() throws IOException {
		Optional<Store> store = Optional.ofNullable(new Store("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm"));
		when(storeRepository.findById("AQw1234Q")).thenReturn(store);
		assertEquals(store, processStore.getStoreById("AQw1234Q"));
	}

}
