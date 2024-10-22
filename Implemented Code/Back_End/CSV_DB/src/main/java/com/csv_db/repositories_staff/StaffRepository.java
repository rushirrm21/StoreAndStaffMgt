package com.csv_db.repositories_staff;

import org.springframework.data.jpa.repository.JpaRepository;
import com.csv_db.model_staff.Staff;

public interface StaffRepository extends JpaRepository<Staff, String> {
}
