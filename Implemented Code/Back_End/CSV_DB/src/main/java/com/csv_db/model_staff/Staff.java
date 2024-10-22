package com.csv_db.model_staff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MasterStaff")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

	@Id
	@Column(name = "staff_id")
	@Pattern(regexp = "\\d{5,10}$")
	private String staffId;

	@NotBlank
	@Column(name = "position")
	private String position;

	@Email
	@NotBlank
	@Column(name = "email")
	private String email;

	@Pattern(regexp = "\\+[1-1]\\d{10}")
	@Column(name = "office_ph_no")
	private String officePhoneNo;

	@Pattern(regexp = "\\d{10}$")
	@Column(name = "cell_phone")
	private String cellPhone;

	@NotBlank
	@Column(name = "dept_area_region_district")
	private String deptAreaRegionDistrict;

}
