package com.csv_db.service_staff_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.csv_db.model_staff.Staff;
import com.csv_db.repositories_staff.StaffRepository;
import com.csv_db.services_staff.ProcessStaff;

@ExtendWith(MockitoExtension.class)
class ProcessStaffTest {

	@InjectMocks
	ProcessStaff processStaff;

	@Mock
	StaffRepository staffRepository;

	@Test
	void getStoreByIdTest1() throws IOException {
		Optional<Staff> staff = Optional.ofNullable(new Staff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw"));
		when(staffRepository.findById("123456")).thenReturn(staff);
		assertEquals(staff, processStaff.getStaffById("123456"));
	}
}
