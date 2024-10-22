package com.csv_db.service_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import com.csv_db.model_store.Store;
import com.csv_db.repositories_store.StoreRepository;
import com.csv_db.services_store.SaveInformation;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

	@InjectMocks
	SaveInformation saveInformation;

	@Mock
	StoreRepository storeRepository;

	@Spy
	ModelMapper modelMapper = new ModelMapper();

	@Test
	void saveToDBTest1() throws IOException {
		FileInputStream csvFileInput = new FileInputStream("C:/Store_Data.csv");
		MultipartFile multipartFile = new MockMultipartFile("data", csvFileInput);
		Store store = new Store("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		when(storeRepository.save(store)).thenReturn(store);
		assertEquals("Upload Successfull", saveInformation.saveToDB(multipartFile));
	}

	@Test
	void saveToDBTest2() throws IOException {
		FileInputStream csvFileInput = new FileInputStream("C:/Store_Data.csv");
		MultipartFile multipartFile = new MockMultipartFile("data", csvFileInput);
		assertEquals("Upload Successfull", saveInformation.saveToDB(multipartFile));
	}

	@Test
	void saveToDBTest3() throws IOException {
		FileInputStream csvFileInput = new FileInputStream("C:/Staff_Data.csv");
		MultipartFile multipartFile = new MockMultipartFile("data", csvFileInput);
		assertEquals("Upload Unsuccessfull", saveInformation.saveToDB(multipartFile));
	}

	@Test
	void checkEmptyHrsTest1() throws IOException {
		assertEquals("09am-07pm", saveInformation.checkEmptyHrs("09am-07pm"));
	}

	@Test
	void checkEmptyHrsTest2() {
		assertEquals("Holiday", saveInformation.checkEmptyHrs(null));
	}

	@Test
	void checkHoursTest1() {
		assertEquals("09am-07pm", saveInformation.checkHours("09am-07pm"));
	}

	@Test
	void checkHoursTest2() {
		assertEquals("Invalid Time", saveInformation.checkHours("09am-07am"));
	}

	@Test
	void checkHoursTest3() {
		assertEquals("Invalid Time", saveInformation.checkHours("09pm-07pm"));
	}

	@Test
	void checkHoursTest4() {
		assertEquals("Invalid Time", saveInformation.checkHours("09pm-07am"));
	}

	@Test
	void checkHoursTest5() {
		assertEquals("12am-07pm", saveInformation.checkHours("12am-07pm"));
	}

	@Test
	void checkHoursTest6() {
		assertEquals("12pm-07pm", saveInformation.checkHours("12pm-07pm"));
	}

	@Test
	void checkHoursTest7() {
		assertEquals("12pm-07pm", saveInformation.checkHours("12pm-07pm"));
	}

	@Test
	void checkHoursTest8() {
		assertEquals("12am-12pm", saveInformation.checkHours("12am-12pm"));
	}

	@Test
	void checkHoursTest9() {
		assertEquals("Invalid Time", saveInformation.checkHours("12am-12am"));
	}

}
