package com.csv_db.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {

	private String staffId;

	private String position;

	private String email;

	private String officePhoneNo;

	private String cellPhone;

	private String deptAreaRegionDistrict;

}
