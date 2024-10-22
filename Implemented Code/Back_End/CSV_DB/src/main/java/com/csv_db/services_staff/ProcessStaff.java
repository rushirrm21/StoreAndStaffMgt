package com.csv_db.services_staff;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csv_db.model_staff.Staff;
import com.csv_db.repositories_staff.StaffRepository;

@Service
public class ProcessStaff {

	@Autowired
	private StaffRepository staffRepository;

	public Optional<Staff> getStaffById(String staffId) {
		return staffRepository.findById(staffId);
	}
}
