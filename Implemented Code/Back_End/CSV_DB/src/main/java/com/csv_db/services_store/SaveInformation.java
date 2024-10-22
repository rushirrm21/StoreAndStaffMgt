package com.csv_db.services_store;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csv_db.model_store.InValidStore;
import com.csv_db.model_store.Store;
import com.csv_db.repositories_store.StoreRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Service
public class SaveInformation {

	String holidayInput = "Holiday";

	@Autowired
	ModelMapper modelMapper;

	Logger logger = LoggerFactory.getLogger(SaveInformation.class);

	@Autowired
	private StoreRepository storeRepository;

	public String saveToDB(MultipartFile file) throws IOException {
		try {
			logger.info("saveToDB method invoked");
			InputStream inputStream = file.getInputStream();
			CsvParserSettings settings = new CsvParserSettings();
			settings.setHeaderExtractionEnabled(true);
			CsvParser parser = new CsvParser(settings);
			logger.info("CSV settings and parser initalisation completed");
			List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
			InValidStore store = new InValidStore();
			logger.info("parsing records one by one");
			parseAllRecords.forEach(records -> {
				String areaRegionCode = null;
				String fullAddress = null;
				logger.info("parsing record");
				store.setStoreNum(records.getString("Store_No"));

				store.setPhoneNumber(records.getString("Phone_No"));

				fullAddress = records.getString("Address") + "," + records.getString("Street _1") + ","
						+ records.getString("Street_2") + "," + records.getString("City") + ","
						+ records.getString("District") + "," + records.getString("State");
				store.setAddress(fullAddress);

				areaRegionCode = records.getString("Area") + "," + records.getString("Region") + ","
						+ records.getString("Zip");
				store.setAreaRegionCode(areaRegionCode);

				// monday
				store.setMonHours(checkEmptyHrs((records.getString("mon_hrs"))));

				// tuesday
				store.setTueHours(checkEmptyHrs((records.getString("tue_hrs"))));

				// wednesday
				store.setWedHours(checkEmptyHrs((records.getString("wed_hrs"))));

				// thursday
				store.setThuHours(checkEmptyHrs((records.getString("thu_hrs"))));

				// friday
				store.setFriHours(checkEmptyHrs((records.getString("fri_hrs"))));

				// saturday
				store.setSatHours(checkEmptyHrs((records.getString("sat_hrs"))));

				// sunday

				store.setSunHours(checkEmptyHrs((records.getString("sun_hrs"))));

				try {
					Store convertedentity = this.modelMapper.map(store, Store.class);
					storeRepository.save(convertedentity);
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

	public String checkEmptyHrs(String hrs) {
		logger.info("checking hrs are null or actual time");
		if (hrs == null) {
			logger.info("hrs are null");
			return "Holiday";
		} else {
			logger.info("actual time");
			return checkHours(hrs);
		}
	}

	public String checkHours(String hrs) {
		int stratTime = Integer.parseInt(hrs.substring(0, 2));
		int endTime = Integer.parseInt(hrs.substring(5, 7));
		String amFormat = hrs.substring(2, 4);
		String pmFormat = hrs.substring(7, 9);
		logger.info("checking whether start time is less than end time");
		if (amFormat.equalsIgnoreCase("AM") && stratTime == 12) {
			stratTime = 0;
		} else if (amFormat.equalsIgnoreCase("PM") && stratTime != 12) {
			stratTime += 12;
		}
		if (pmFormat.equalsIgnoreCase("PM") && endTime != 12) {
			endTime += 12;
		} else if (pmFormat.equalsIgnoreCase("AM") && endTime == 12) {
			endTime = 0;
		}

		if (stratTime < endTime) {
			return hrs;
		} else {
			return "Invalid Time";
		}
	}

}
