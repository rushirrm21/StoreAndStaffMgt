package com.csv_db.model_staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidStaff {

	private String staffId;

	private String position;

	private String email;

	private String officePhoneNo;

	private String cellPhone;

	private String deptAreaRegionDistrict;

}
