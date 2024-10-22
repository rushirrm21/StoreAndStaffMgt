package com.csv_db.service_staff_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import com.csv_db.model_staff.ForApprovalStaff;
import com.csv_db.repositories_staff.ApprovalStaffRepository;
import com.csv_db.repositories_staff.StaffRepository;
import com.csv_db.services_staff.SaveStaffInformation;
import com.csv_db.services_staff.StaffApprovalService;

@ExtendWith(MockitoExtension.class)
class ApprovalStaffServiceTest {

	@InjectMocks
	StaffApprovalService staffApprovalService;

	@Mock
	ApprovalStaffRepository approvalStaffRepository;

	@Mock
	StaffRepository staffRepository;

	@Spy
	SaveStaffInformation saveInfo;

	@Spy
	ModelMapper modelMapper;

	@Test
	void saveForApprovalTest1() throws IOException {
		ForApprovalStaff staff = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		when(approvalStaffRepository.save(staff)).thenReturn(staff);
		assertEquals(true, staffApprovalService.saveForApproval(staff));
	}

	@Test
	void saveForApprovalTest2() throws IOException {
		ForApprovalStaff staff = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		when(approvalStaffRepository.save(staff)).thenThrow();
		assertEquals(false, staffApprovalService.saveForApproval(staff));
	}

	@Test
	void getApprovalChangesTest1() throws IOException {
		ForApprovalStaff staff1 = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		List<ForApprovalStaff> staff = new ArrayList<>();
		staff.add(staff1);
		when(approvalStaffRepository.findAll()).thenReturn(staff);
		assertEquals(staff.get(0), staffApprovalService.getApprovalChanges());
	}

	@Test
	void getApprovalChangesTest2() throws IOException {
		ForApprovalStaff staff1 = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		List<ForApprovalStaff> staff = new ArrayList<>();
		staff.add(staff1);
		when(approvalStaffRepository.findAll()).thenReturn(null);
		assertEquals(null, staffApprovalService.getApprovalChanges());
	}

	@Test
	void deleteApprovalDataTest1() throws IOException {
		ForApprovalStaff staff1 = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		List<ForApprovalStaff> staff = new ArrayList<>();
		staff.add(staff1);
		when(approvalStaffRepository.findAll()).thenReturn(staff);
		assertEquals(true, staffApprovalService.deleteApprovalData());
	}

	@Test
	void deleteApprovalDataTest2() throws IOException {
		ForApprovalStaff staff1 = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		List<ForApprovalStaff> staff = new ArrayList<>();
		staff.add(staff1);
		when(approvalStaffRepository.findAll()).thenThrow();
		assertEquals(false, staffApprovalService.deleteApprovalData());
	}

	@Test
	void updateApprovalDataTest1() throws IOException {
		ForApprovalStaff staff1 = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		List<ForApprovalStaff> staff = new ArrayList<>();
		staff.add(staff1);
		when(approvalStaffRepository.findAll()).thenThrow();
		assertEquals(false, staffApprovalService.updateApprovalData());
	}

	@Test
	void updateApprovalDataTest2() throws IOException {
		ForApprovalStaff staff1 = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		List<ForApprovalStaff> staff = new ArrayList<>();
		staff.add(staff1);
		when(approvalStaffRepository.findAll()).thenReturn(staff);
		assertEquals(true, staffApprovalService.updateApprovalData());
	}
}
