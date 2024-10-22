package com.csv_db.services_staff;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.csv_db.model_staff.InvalidStaff;
import com.csv_db.model_staff.Staff;
import com.csv_db.repositories_staff.StaffRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Service
public class SaveStaffInformation {

	@Autowired
	ModelMapper modelMapper;

	Logger logger = LoggerFactory.getLogger(SaveStaffInformation.class);

	@Autowired
	private StaffRepository staffRepository;

	public String saveToDB(MultipartFile file) throws IOException {
		try {
			logger.info("saveToDB method invoked");
			InputStream inputStream = file.getInputStream();
			CsvParserSettings settings = new CsvParserSettings();
			settings.setHeaderExtractionEnabled(true);
			CsvParser parser = new CsvParser(settings);
			logger.info("CSV settings and parser initalisation completed");
			List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
			InvalidStaff staff = new InvalidStaff();
			logger.info("parsing records one by one");
			parseAllRecords.forEach(records -> {
				String deptAreaRegionDistrict = null;
				String officePhone = null;
				logger.info("parsing record");

				staff.setStaffId(records.getString("Staff_Id"));

				staff.setPosition(records.getString("Position"));

				staff.setEmail(records.getString("Email"));

				officePhone = "+1" + records.getString("Office_ph_no");
				staff.setOfficePhoneNo(officePhone);

				staff.setCellPhone(records.getString("Cell_phone"));

				deptAreaRegionDistrict = records.getString("Dept") + "," + records.getString("Area") + ","
						+ records.getString("Region") + "," + records.getString("District");
				staff.setDeptAreaRegionDistrict(deptAreaRegionDistrict);

				try {
					Staff convertedentity = this.modelMapper.map(staff, Staff.class);
					staffRepository.save(convertedentity);
					logger.info("saved to DB one record");
				} catch (Exception exception) {
					// do nothing don't save to DB
					logger.info("Invalid records modelmapper failed to map");
				}

			});
			logger.info("return success to controller");
			return "Upload Successfull";
		} catch (Exception e) {
			return "Upload Unsuccessfull";
		}
	}
}