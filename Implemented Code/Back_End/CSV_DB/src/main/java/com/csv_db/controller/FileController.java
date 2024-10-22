package com.csv_db.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.csv_db.dto.StaffDto;
import com.csv_db.dto.StoreDto;
import com.csv_db.model_staff.ForApprovalStaff;
import com.csv_db.model_staff.Staff;
import com.csv_db.model_store.ForApprovalStore;
import com.csv_db.model_store.Store;
import com.csv_db.services_staff.ProcessStaff;
import com.csv_db.services_staff.SaveStaffInformation;
import com.csv_db.services_staff.StaffApprovalService;
import com.csv_db.services_store.ApprovalService;
import com.csv_db.services_store.ProcessStore;
import com.csv_db.services_store.SaveInformation;

import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FileController {

	Logger logger = LoggerFactory.getLogger(FileController.class);

	public static final String RESPONSEKEY1 = "message";
	public static final String RESPONSEKEY2 = "detailsAvailable";
	public static final String RESPONSEVALUE1 = "Success";
	public static final String RESPONSEVALUE2 = "Failed";
	public static final String RESPONSEVALUE3 = "storeNumF";
	public static final String RESPONSEVALUE4 = "staffIdF";

	@Autowired
	SaveInformation saveInfo;

	@Autowired
	SaveStaffInformation saveStaffInfo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProcessStore processStore;

	@Autowired
	ProcessStaff processStaff;

	@Autowired
	ApprovalService approvalService;

	@Autowired
	StaffApprovalService staffApprovalService;

	@PostMapping("/loadstoredata")
	public ResponseEntity<Map<String, String>> storeUpload(@RequestParam("file") MultipartFile file)
			throws IOException {
		logger.info("storeUpload method invoked");
		String msg = saveInfo.saveToDB(file);
		if (msg.equalsIgnoreCase("Upload Successfull")) {
			Map<String, String> mpp = new HashMap<>();
			mpp.put(RESPONSEKEY1, "INDUCTED");
			logger.info("storeUpload method's IF part invoked");
			return new ResponseEntity<>(mpp, HttpStatus.OK);
		} else {
			Map<String, String> mpp = new HashMap<>();
			mpp.put(RESPONSEKEY1, "FAILED");
			logger.info("storeUpload method's else part invoked");
			return new ResponseEntity<>(mpp, HttpStatus.OK);
		}

	}

	@PostMapping("/loadstaffdata")
	public ResponseEntity<Map<String, String>> staffUpload(@RequestParam("file") MultipartFile file)
			throws IOException {
		logger.info("staffUpload method invoked");
		String msg = saveStaffInfo.saveToDB(file);
		if (msg.equalsIgnoreCase("Upload Successfull")) {
			Map<String, String> mpp = new HashMap<>();
			mpp.put(RESPONSEKEY1, "INDUCTED");
			logger.info("staffUpload method's IF part invoked");
			return new ResponseEntity<>(mpp, HttpStatus.OK);
		} else {
			Map<String, String> mpp = new HashMap<>();
			mpp.put(RESPONSEKEY1, "FAILED");
			logger.info("staffUpload method's else part invoked");
			return new ResponseEntity<>(mpp, HttpStatus.OK);
		}
	}

	@GetMapping("/retrievestore/{storeId}")
	public ResponseEntity<Map<String, String>> getStoreData(@PathVariable String storeId) {
		logger.info("getStoreData invoked");
		Optional<Store> store = processStore.getStoreById(storeId);
		try {
			if (store.isPresent()) {
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
				logger.info("Returning positive response to front end from getStoreData");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			} else {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEKEY2, "No");
				logger.info("Returning negative response to front end from getStoreData");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			}

		} catch (Exception e) {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY2, "No");
			logger.info("Exception Returning negative response to front end from getStoreData");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}
	}

	@PostMapping("/sotreforapproval")
	public ResponseEntity<Map<String, String>> addForApproval(@RequestBody StoreDto sdt1) {
		try {
			ForApprovalStore convertedentity = this.modelMapper.map(sdt1, ForApprovalStore.class);
			boolean flag = approvalService.saveForApproval(convertedentity);
			if (flag) {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEKEY1, RESPONSEVALUE1);
				logger.info("Returning positive response to front end from addForApproval from addForApproval");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			} else {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEKEY1, RESPONSEVALUE2);
				logger.info("save Db faild Returning negative response to front end from addForApproval");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			}
		} catch (Exception e) {

			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY1, RESPONSEVALUE2);
			logger.info("Exception Returning negative response to front end from addForApproval");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}

	}

	@GetMapping("/approvaldata")
	public ResponseEntity<Map<String, String>> getApprovalData() {
		logger.info("getApprovalData invoked");
		ForApprovalStore forappstore = approvalService.getApprovalChanges();
		if (forappstore != null) {
			Optional<Store> store = processStore.getStoreById(forappstore.getStoreNum());
			if (store.isPresent()) {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEVALUE3, store.get().getStoreNum());
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
				logger.info("positive response from getApprovalData");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			} else {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEVALUE3, null);
				logger.info("data not present negative response from getApprovalData");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			}
		} else {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEVALUE3, null);
			logger.info("data is null negative response from getApprovalData");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}
	}

	@PutMapping("/updatestore")
	public ResponseEntity<Map<String, String>> updateStoreApproval() {
		logger.info("updateStoreApproval invoked");
		boolean flag = approvalService.updateApprovalData();
		if (flag) {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY1, RESPONSEVALUE1);
			logger.info("updateStoreApproval Returning Positive response to front end");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		} else {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY1, RESPONSEVALUE2);
			logger.info("updateStoreApproval Returning Negative response to front end");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}

	}

	@DeleteMapping("/deletestore")
	public ResponseEntity<Map<String, String>> deleteStaffApproval() {
		logger.info("deleteStaffApproval invoked");
		approvalService.deleteApprovalData();
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE1);
		logger.info("deleteStaffApproval Returning Positive response to front end");
		return new ResponseEntity<>(mp, HttpStatus.OK);

	}

	@GetMapping("/retrievestaff/{staffId}")
	public ResponseEntity<Map<String, String>> getStaffData(@PathVariable String staffId) {
		logger.info("getStaffData invoked");
		Optional<Staff> staff = processStaff.getStaffById(staffId);
		try {
			if (staff.isPresent()) {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEKEY2, "Yes");
				mp.put("staffId", staff.get().getStaffId());
				mp.put("position", staff.get().getPosition());
				mp.put("email", staff.get().getEmail());
				mp.put("officePhoneNo", staff.get().getOfficePhoneNo());
				mp.put("cellPhone", staff.get().getCellPhone());
				mp.put("deptAreaRegionDistrict", staff.get().getDeptAreaRegionDistrict());
				return new ResponseEntity<>(mp, HttpStatus.OK);
			} else {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEKEY2, "No");
				logger.info("Returning negative response to front end");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			}

		} catch (Exception e) {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY2, "No");
			logger.info("Returning negative response to front end");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}

	}

	@PostMapping("/staffforapproval")
	public ResponseEntity<Map<String, String>> addStaffForApproval(@RequestBody StaffDto sdt1) {
		logger.info("addStaffForApproval invoked");
		try {
			ForApprovalStaff convertedentity = this.modelMapper.map(sdt1, ForApprovalStaff.class);
			boolean flag = staffApprovalService.saveForApproval(convertedentity);
			if (flag) {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEKEY1, RESPONSEVALUE1);
				logger.info("Returning positive response to front end");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			} else {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEKEY1, RESPONSEVALUE2);
				logger.info("save Db faild Returning negative response to front end");
				return new ResponseEntity<>(mp, HttpStatus.OK);
			}
		} catch (Exception e) {

			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY1, RESPONSEVALUE2);
			logger.info("Exception Returning negative response to front end");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}

	}

	@GetMapping("/approvalstaffdata")
	public ResponseEntity<Map<String, String>> getApprovalStaffData() {
		logger.info("getApprovalStaffData invoked");
		ForApprovalStaff forappstaff = staffApprovalService.getApprovalChanges();
		if (forappstaff != null) {
			Optional<Staff> staff = processStaff.getStaffById(forappstaff.getStaffId());
			if (staff.isPresent()) {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEVALUE4, staff.get().getStaffId());
				mp.put("positionF", staff.get().getPosition());
				mp.put("emailF", staff.get().getEmail());
				mp.put("officePhoneNoF", staff.get().getOfficePhoneNo());
				mp.put("cellPhoneF", staff.get().getCellPhone());
				mp.put("deptAreaRegionDistrictF", staff.get().getDeptAreaRegionDistrict());
				mp.put("staffIdT", forappstaff.getStaffId());
				mp.put("positionT", forappstaff.getPosition());
				mp.put("emailT", forappstaff.getEmail());
				mp.put("officePhoneNoT", forappstaff.getOfficePhoneNo());
				mp.put("cellPhoneT", forappstaff.getCellPhone());
				mp.put("deptAreaRegionDistrictT", forappstaff.getDeptAreaRegionDistrict());
				return new ResponseEntity<>(mp, HttpStatus.OK);
			} else {
				Map<String, String> mp = new HashMap<>();
				mp.put(RESPONSEVALUE4, null);
				return new ResponseEntity<>(mp, HttpStatus.OK);
			}
		} else {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEVALUE4, null);
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}
	}

	@PutMapping("/updatestaff")
	public ResponseEntity<Map<String, String>> updateStaffApproval() {
		logger.info("updateStaffApproval invoked");
		boolean flag = staffApprovalService.updateApprovalData();
		if (flag) {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY1, RESPONSEVALUE1);
			logger.info("Returning Positive response to front end");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		} else {
			Map<String, String> mp = new HashMap<>();
			mp.put(RESPONSEKEY1, RESPONSEVALUE2);
			logger.info("Returning Negative response to front end");
			return new ResponseEntity<>(mp, HttpStatus.OK);
		}

	}

	@DeleteMapping("/deletestaff")
	public ResponseEntity<Map<String, String>> deleteStoreApproval() {
		logger.info("deleteStoreApproval Invoked");
		staffApprovalService.deleteApprovalData();
		Map<String, String> mp = new HashMap<>();
		mp.put(RESPONSEKEY1, RESPONSEVALUE1);
		logger.info("Returning Positive response to front end");
		return new ResponseEntity<>(mp, HttpStatus.OK);
	}
}
