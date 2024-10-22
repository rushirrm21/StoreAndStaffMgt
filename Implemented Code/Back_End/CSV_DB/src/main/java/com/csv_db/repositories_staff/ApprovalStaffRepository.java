package com.csv_db.repositories_staff;

import org.springframework.data.jpa.repository.JpaRepository;
import com.csv_db.model_staff.ForApprovalStaff;

public interface ApprovalStaffRepository extends JpaRepository<ForApprovalStaff, String> {
}
