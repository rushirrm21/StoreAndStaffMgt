package com.csv_db.services_store;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csv_db.model_store.ForApprovalStore;
import com.csv_db.model_store.Store;
import com.csv_db.repositories_store.ApprovalRepository;
import com.csv_db.repositories_store.StoreRepository;

import java.util.List;

@Service
public class ApprovalService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private ApprovalRepository approvalRepository;

	@Autowired
	SaveInformation saveInfo;

	@Autowired
	private StoreRepository storeRepository;

	Logger logger = LoggerFactory.getLogger(ApprovalService.class);

	public boolean saveForApproval(ForApprovalStore convertedentity) {
		logger.info("saveForApproval invoked");
		try {
			approvalRepository.save(convertedentity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ForApprovalStore getApprovalChanges() {
		logger.info("getApprovalChanges invoked");
		try {
			List<ForApprovalStore> list = approvalRepository.findAll();
			return list.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteApprovalData() {
		logger.info("deleteApprovalData invoked");
		try {
			ForApprovalStore forappstore = this.getApprovalChanges();
			approvalRepository.deleteById(forappstore.getStoreNum());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateApprovalData() {
		logger.info("updateApprovalData invoked");
		try {
			ForApprovalStore forappstore = this.getApprovalChanges();
			ForApprovalStore forappstore1 = forappstore;
			forappstore.setMonHours(this.checkEmptyHrs(forappstore1.getMonHours()));
			forappstore.setTueHours(this.checkEmptyHrs(forappstore1.getTueHours()));
			forappstore.setWedHours(this.checkEmptyHrs(forappstore1.getWedHours()));
			forappstore.setThuHours(this.checkEmptyHrs(forappstore1.getThuHours()));
			forappstore.setFriHours(this.checkEmptyHrs(forappstore1.getFriHours()));
			forappstore.setSatHours(this.checkEmptyHrs(forappstore1.getSatHours()));
			forappstore.setSunHours(this.checkEmptyHrs(forappstore1.getSunHours()));

			Store convertedentity = this.modelMapper.map(forappstore, Store.class);
			storeRepository.save(convertedentity);
			approvalRepository.deleteById(forappstore1.getStoreNum());

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String checkEmptyHrs(String hrs) {
		logger.info("checkEmptyHrs invoked");
		String holidayChk = "Holiday";
		String invalidTimeChk = "Invalid Time";
		if (hrs == null || hrs.equalsIgnoreCase(holidayChk)) {
			return "Holiday";
		} else if (hrs.equalsIgnoreCase(invalidTimeChk)) {
			return "Invalid Time";
		} else {
			return saveInfo.checkHours(hrs);
		}
	}
}
