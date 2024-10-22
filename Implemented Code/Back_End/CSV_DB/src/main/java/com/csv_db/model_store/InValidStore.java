package com.csv_db.model_store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InValidStore {

	private String storeNum;

	private String address;

	private String phoneNumber;

	private String areaRegionCode;

	private String monHours;

	private String tueHours;

	private String wedHours;

	private String thuHours;

	private String friHours;

	private String satHours;

	private String sunHours;
}
