package com.csv_db.service_staff_test;

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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import com.csv_db.model_staff.Staff;
import com.csv_db.repositories_staff.StaffRepository;
import com.csv_db.services_staff.SaveStaffInformation;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

	@InjectMocks
	SaveStaffInformation saveStaffInformation;

	@Mock
	StaffRepository staffRepository;

	@Spy
	ModelMapper modelMapper = new ModelMapper();

	@Test
	void saveToDBTest1() throws IOException {
		FileInputStream csvFileInput = new FileInputStream("C:/Staff_Data.csv");
		MultipartFile multipartFile = new MockMultipartFile("data", csvFileInput);
		Staff staff = new Staff("123456", "Clerk", "ru12@gmail.com", "+11234567898", "1234567898",
				"medical,abc,xyz,pop");
		when(staffRepository.save(staff)).thenReturn(staff);
		assertEquals("Upload Successfull", saveStaffInformation.saveToDB(multipartFile));
	}

	@Test
	void saveToDBTest2() throws IOException {
		FileInputStream csvFileInput = new FileInputStream("C:/Staff_Data.csv");
		MultipartFile multipartFile = new MockMultipartFile("data", csvFileInput);
		assertEquals("Upload Successfull", saveStaffInformation.saveToDB(multipartFile));
	}

	@Test
	void saveToDBTest3() throws IOException {
		FileInputStream csvFileInput = new FileInputStream("C:/Store_Data.csv");
		MultipartFile multipartFile = new MockMultipartFile("data", csvFileInput);
		assertEquals("Upload Unsuccessfull", saveStaffInformation.saveToDB(multipartFile));
	}
}
