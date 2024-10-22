package com.csv_db.service_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.csv_db.repositories_store.StoreRepository;
import com.csv_db.services_store.SaveInformation;

@ExtendWith(MockitoExtension.class)
class ServiceExceptionTest {

	@InjectMocks
	SaveInformation saveInformation;

	@Mock
	StoreRepository storeRepository;

	@Test
	void saveToDBTest1() throws IOException {
		FileInputStream csvFileInput = new FileInputStream("C:/Store_Data.csv");
		MultipartFile multipartFile = new MockMultipartFile("data", csvFileInput);
		assertEquals("Upload Successfull", saveInformation.saveToDB(multipartFile));
	}
}
