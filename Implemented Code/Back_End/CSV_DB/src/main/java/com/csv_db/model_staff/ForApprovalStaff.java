package com.csv_db.model_staff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ForApprovalStaff")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForApprovalStaff {

	@Id
	@Column(name = "staff_id")
	private String staffId;

	@Column(name = "position")
	private String position;

	@Column(name = "email")
	private String email;

	@Column(name = "office_ph_no")
	private String officePhoneNo;

	@Column(name = "cell_phone")
	private String cellPhone;

	@Column(name = "dept_area_region_district")
	private String deptAreaRegionDistrict;

}
