package com.csv_db.model_store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "MasterStore")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {

	@Id
	@Column(name = "store_num")
	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]+$")
	private String storeNum;

	@Column(name = "address")
	@NotBlank
	private String address;

	@Column(name = "phone_number")
	@NotBlank
	private String phoneNumber;

	@Column(name = "area_region_code")
	@NotBlank
	private String areaRegionCode;

	@Column(name = "mon_hours")
	@NotBlank
	private String monHours;

	@Column(name = "tue_hours")
	@NotBlank
	private String tueHours;

	@Column(name = "wed_hours")
	@NotBlank
	private String wedHours;

	@Column(name = "thu_hours")
	@NotBlank
	private String thuHours;

	@Column(name = "fri_hours")
	@NotBlank
	private String friHours;

	@Column(name = "sat_hours")
	@NotBlank
	private String satHours;

	@Column(name = "sun_hours")
	@NotBlank
	private String sunHours;
}
