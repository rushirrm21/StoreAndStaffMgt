package com.csv_db.model_store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ForApprovalStore")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForApprovalStore {

	@Id
	@Column(name = "store_num")
	private String storeNum;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "area_region_code")
	private String areaRegionCode;

	@Column(name = "mon_hours")
	private String monHours;

	@Column(name = "tue_hours")
	private String tueHours;

	@Column(name = "wed_hours")
	private String wedHours;

	@Column(name = "thu_hours")
	private String thuHours;

	@Column(name = "fri_hours")
	@NotBlank
	private String friHours;

	@Column(name = "sat_hours")
	private String satHours;

	@Column(name = "sun_hours")
	private String sunHours;
}
