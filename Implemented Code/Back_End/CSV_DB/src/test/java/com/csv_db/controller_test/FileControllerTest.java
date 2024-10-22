package com.csv_db.controller_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.csv_db.controller.FileController;
import com.csv_db.dto.StaffDto;
import com.csv_db.dto.StoreDto;
import com.csv_db.model_staff.ForApprovalStaff;
import com.csv_db.model_staff.Staff;
import com.csv_db.model_store.ForApprovalStore;
import com.csv_db.model_store.Store;
import com.csv_db.repositories_store.ApprovalRepository;
import com.csv_db.services_staff.ProcessStaff;
import com.csv_db.services_staff.SaveStaffInformation;
import com.csv_db.services_staff.StaffApprovalService;
import com.csv_db.services_store.ApprovalService;
import com.csv_db.services_store.ProcessStore;
import com.csv_db.services_store.SaveInformation;

@ExtendWith(MockitoExtension.class)
class FileControllerTest {

	@InjectMocks
	FileController fileController;

	@Mock
	public SaveInformation saveInformation;

	@Mock
	public SaveStaffInformation saveStaffInformation;

	@Mock
	ProcessStore processStore;

	@Mock
	ProcessStaff processStaff;

	@Mock
	ApprovalService approvalService;

	@Mock
	StaffApprovalService staffApprovalService;

	@Mock
	ApprovalRepository approvalRepository;

	@Spy
	ModelMapper modelMapper;

	public static final String RESPONSEKEY1 = "message";
	public static final String RESPONSEKEY2 = "detailsAvailable";
	public static final String RESPONSEVALUE1 = "Success";
	public static final String RESPONSEVALUE2 = "Failed";

	MultipartFile multipartFile;

	@Test
	void controllerTest1() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, "INDUCTED");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(saveInformation.saveToDB(multipartFile)).thenReturn("Upload Successfull");
		mppRE = fileController.storeUpload(multipartFile);
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest2() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, "FAILED");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(saveInformation.saveToDB(multipartFile)).thenReturn("Upload Unsuccessfull");
		mppRE = fileController.storeUpload(multipartFile);
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest3() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, "INDUCTED");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(saveStaffInformation.saveToDB(multipartFile)).thenReturn("Upload Successfull");
		mppRE = fileController.staffUpload(multipartFile);
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest4() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, "FAILED");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(saveStaffInformation.saveToDB(multipartFile)).thenReturn("Upload Unsuccessfull");
		mppRE = fileController.staffUpload(multipartFile);
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest5() throws IOException {
		Optional<Store> store = Optional.ofNullable(new Store("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm"));
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY2, "Yes");
		mp.put("storeNum", store.get().getStoreNum());
		mp.put("address", store.get().getAddress());
		mp.put("phoneNumber", store.get().getPhoneNumber());
		mp.put("areaRegionCode", store.get().getAreaRegionCode());
		mp.put("monHours", store.get().getMonHours());
		mp.put("tueHours", store.get().getTueHours());
		mp.put("wedHours", store.get().getWedHours());
		mp.put("thuHours", store.get().getThuHours());
		mp.put("friHours", store.get().getFriHours());
		mp.put("satHours", store.get().getSatHours());
		mp.put("sunHours", store.get().getSunHours());
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(processStore.getStoreById("adb4cf4WQA")).thenReturn(store);
		mppRE = fileController.getStoreData("adb4cf4WQA");
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest6() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY2, "No");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(processStore.getStoreById("adb4cf4WQA")).thenReturn(null);
		mppRE = fileController.getStoreData("adb4cf4WQA");
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest7() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY2, "No");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(processStore.getStoreById("adb4cf4WQA")).thenReturn(Optional.empty());
		mppRE = fileController.getStoreData("adb4cf4WQA");
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest8() throws IOException {
		StoreDto store = new StoreDto("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		ForApprovalStore convertedentity = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE1);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(approvalService.saveForApproval(convertedentity)).thenReturn(true);
		mppRE = fileController.addForApproval(store);
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest9() throws IOException {
		StoreDto store = new StoreDto("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		ForApprovalStore convertedentity = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE2);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(approvalService.saveForApproval(convertedentity)).thenReturn(false);
		mppRE = fileController.addForApproval(store);
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest10() throws IOException {
		StoreDto store = new StoreDto("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		ForApprovalStore convertedentity = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE2);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(approvalService.saveForApproval(convertedentity)).thenThrow();
		mppRE = fileController.addForApproval(store);
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest11() throws IOException {
		ForApprovalStore forappstore = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		Map<String, String> mp = new HashMap<>();
		mp.put("storeNumF", null);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(approvalService.getApprovalChanges()).thenReturn(forappstore);
		mppRE = fileController.getApprovalData();
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest1112() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put("storeNumF", null);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(approvalService.getApprovalChanges()).thenReturn(null);
		mppRE = fileController.getApprovalData();
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest12() throws IOException {
		Optional<Store> store = Optional.ofNullable(new Store("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm"));
		ForApprovalStore forappstore = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		Map<String, String> mp = new HashMap<>();
		mp.put("storeNumF", store.get().getStoreNum());
		mp.put("addressF", store.get().getAddress());
		mp.put("phoneNumberF", store.get().getPhoneNumber());
		mp.put("areaRegionCodeF", store.get().getAreaRegionCode());
		mp.put("monHoursF", store.get().getMonHours());
		mp.put("tueHoursF", store.get().getTueHours());
		mp.put("wedHoursF", store.get().getWedHours());
		mp.put("thuHoursF", store.get().getThuHours());
		mp.put("friHoursF", store.get().getFriHours());
		mp.put("satHoursF", store.get().getSatHours());
		mp.put("sunHoursF", store.get().getSunHours());
		mp.put("storeNumT", forappstore.getStoreNum());
		mp.put("addressT", forappstore.getAddress());
		mp.put("phoneNumberT", forappstore.getPhoneNumber());
		mp.put("areaRegionCodeT", forappstore.getAreaRegionCode());
		mp.put("monHoursT", forappstore.getMonHours());
		mp.put("tueHoursT", forappstore.getTueHours());
		mp.put("wedHoursT", forappstore.getWedHours());
		mp.put("thuHoursT", forappstore.getThuHours());
		mp.put("friHoursT", forappstore.getFriHours());
		mp.put("satHoursT", forappstore.getSatHours());
		mp.put("sunHoursT", forappstore.getSunHours());
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(approvalService.getApprovalChanges()).thenReturn(forappstore);
		when(processStore.getStoreById(forappstore.getStoreNum())).thenReturn(store);
		mppRE = fileController.getApprovalData();
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest13() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE1);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(approvalService.updateApprovalData()).thenReturn(true);
		mppRE = fileController.updateStoreApproval();
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest14() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE2);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(approvalService.updateApprovalData()).thenReturn(false);
		mppRE = fileController.updateStoreApproval();
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest18() throws IOException {
		Optional<Staff> staff = Optional.ofNullable(new Staff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw"));
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY2, "Yes");
		mp.put("staffId", staff.get().getStaffId());
		mp.put("position", staff.get().getPosition());
		mp.put("email", staff.get().getEmail());
		mp.put("officePhoneNo", staff.get().getOfficePhoneNo());
		mp.put("cellPhone", staff.get().getCellPhone());
		mp.put("deptAreaRegionDistrict", staff.get().getDeptAreaRegionDistrict());
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(processStaff.getStaffById("123456")).thenReturn(staff);
		mppRE = fileController.getStaffData("123456");
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest19() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY2, "No");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(processStaff.getStaffById("123456")).thenReturn(null);
		mppRE = fileController.getStaffData("123456");
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest20() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY2, "No");
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(processStaff.getStaffById("123456")).thenReturn(Optional.empty());
		mppRE = fileController.getStaffData("123456");
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest21() throws IOException {
		StaffDto staff = new StaffDto("123456", "Clerak", "clerk@gmail.com", "+12345678998", "1234567892",
				"Medical,keshnavanagr,mudhwa,punw");
		ForApprovalStaff convertedentity = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE1);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(staffApprovalService.saveForApproval(convertedentity)).thenReturn(true);
		mppRE = fileController.addStaffForApproval(staff);
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest22() throws IOException {
		StaffDto staff = new StaffDto("123456", "Clerak", "clerk@gmail.com", "+12345678998", "1234567892",
				"Medical,keshnavanagr,mudhwa,punw");
		ForApprovalStaff convertedentity = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE2);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(staffApprovalService.saveForApproval(convertedentity)).thenReturn(false);
		mppRE = fileController.addStaffForApproval(staff);
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest23() throws IOException {
		StaffDto staff = new StaffDto("123456", "Clerak", "clerk@gmail.com", "+12345678998", "1234567892",
				"Medical,keshnavanagr,mudhwa,punw");
		ForApprovalStaff convertedentity = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE2);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(staffApprovalService.saveForApproval(convertedentity)).thenThrow();
		mppRE = fileController.addStaffForApproval(staff);
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest24() throws IOException {
		ForApprovalStaff forApprovalStaff = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		Map<String, String> mp = new HashMap<>();
		mp.put("staffIdF", null);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(staffApprovalService.getApprovalChanges()).thenReturn(forApprovalStaff);
		mppRE = fileController.getApprovalStaffData();
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest25() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put("staffIdF", null);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(staffApprovalService.getApprovalChanges()).thenReturn(null);
		mppRE = fileController.getApprovalStaffData();
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest27() throws IOException {
		Optional<Staff> staff = Optional.ofNullable(new Staff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw"));
		ForApprovalStaff forApprovalStaff = new ForApprovalStaff("123456", "Clerak", "clerk@gmail.com", "+12345678998",
				"1234567892", "Medical,keshnavanagr,mudhwa,punw");
		Map<String, String> mp = new HashMap<>();
		mp.put("staffIdF", staff.get().getStaffId());
		mp.put("positionF", staff.get().getPosition());
		mp.put("emailF", staff.get().getEmail());
		mp.put("officePhoneNoF", staff.get().getOfficePhoneNo());
		mp.put("cellPhoneF", staff.get().getCellPhone());
		mp.put("deptAreaRegionDistrictF", staff.get().getDeptAreaRegionDistrict());
		mp.put("staffIdT", forApprovalStaff.getStaffId());
		mp.put("positionT", forApprovalStaff.getPosition());
		mp.put("emailT", forApprovalStaff.getEmail());
		mp.put("officePhoneNoT", forApprovalStaff.getOfficePhoneNo());
		mp.put("cellPhoneT", forApprovalStaff.getCellPhone());
		mp.put("deptAreaRegionDistrictT", forApprovalStaff.getDeptAreaRegionDistrict());
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(staffApprovalService.getApprovalChanges()).thenReturn(forApprovalStaff);
		when(processStaff.getStaffById(forApprovalStaff.getStaffId())).thenReturn(staff);
		mppRE = fileController.getApprovalStaffData();
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest28() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE1);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(staffApprovalService.updateApprovalData()).thenReturn(true);
		mppRE = fileController.updateStaffApproval();
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest29() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE2);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(mp, HttpStatus.OK);
		when(staffApprovalService.updateApprovalData()).thenReturn(false);
		mppRE = fileController.updateStaffApproval();
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest16() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE1);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(null, HttpStatus.OK);
		mppRE = fileController.deleteStoreApproval();
		assertEquals(mpRE.toString(), mppRE.toString());
	}

	@Test
	void controllerTest30() throws IOException {
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE1);
		ResponseEntity<Map<String, String>> mpRE = new ResponseEntity<>(mp, HttpStatus.OK);
		ResponseEntity<Map<String, String>> mppRE = new ResponseEntity<>(null, HttpStatus.OK);
		mppRE = fileController.deleteStaffApproval();
		assertEquals(mpRE.toString(), mppRE.toString());
	}
}
