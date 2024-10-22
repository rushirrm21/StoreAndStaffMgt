package com.csv_db.services_staff;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csv_db.model_staff.ForApprovalStaff;
import com.csv_db.model_staff.Staff;
import com.csv_db.repositories_staff.ApprovalStaffRepository;
import com.csv_db.repositories_staff.StaffRepository;

@Service
public class StaffApprovalService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ApprovalStaffRepository approvalStaffRepository;

	@Autowired
	StaffRepository staffRepository;

	Logger logger = LoggerFactory.getLogger(StaffApprovalService.class);

	public boolean saveForApproval(ForApprovalStaff convertedentity) {
		logger.info("saveForApproval  invoked");
		try {
			approvalStaffRepository.save(convertedentity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ForApprovalStaff getApprovalChanges() {
		logger.info("getApprovalChanges  invoked");
		try {
			List<ForApprovalStaff> list = approvalStaffRepository.findAll();
			return list.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteApprovalData() {
		logger.info("deleteApprovalData  invoked");
		try {
			ForApprovalStaff forappstaff = this.getApprovalChanges();
			approvalStaffRepository.deleteById(forappstaff.getStaffId());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateApprovalData() {
		logger.info("updateApprovalData  invoked");
		try {
			ForApprovalStaff forappstaff = this.getApprovalChanges();
			ForApprovalStaff forappstaff1 = forappstaff;
			forappstaff.setStaffId((forappstaff1.getStaffId()));
			forappstaff.setPosition((forappstaff1.getPosition()));
			forappstaff.setEmail((forappstaff1.getEmail()));
			forappstaff.setOfficePhoneNo((forappstaff1.getOfficePhoneNo()));
			forappstaff.setCellPhone((forappstaff1.getCellPhone()));
			forappstaff.setDeptAreaRegionDistrict((forappstaff1.getDeptAreaRegionDistrict()));

			Staff convertedentity = this.modelMapper.map(forappstaff, Staff.class);
			staffRepository.save(convertedentity);
			approvalStaffRepository.deleteById(forappstaff1.getStaffId());

			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
