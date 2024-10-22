package com.csv_db.service_staff_test;

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
import com.csv_db.repositories_staff.StaffRepository;
import com.csv_db.services_staff.SaveStaffInformation;

@ExtendWith(MockitoExtension.class)
class ServiceExceptionTest {

	@InjectMocks
	SaveStaffInformation saveStaffInformation;

	@Mock
	StaffRepository staffRepository;

	@Test
	void saveToDBTest1() throws IOException {
		FileInputStream csvFileInput = new FileInputStream("C:/Staff_Data.csv");
		MultipartFile multipartFile = new MockMultipartFile("data", csvFileInput);
		assertEquals("Upload Successfull", saveStaffInformation.saveToDB(multipartFile));
	}
}
