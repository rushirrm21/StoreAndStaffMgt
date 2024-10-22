package com.csv_db.service_test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.csv_db.model_store.ForApprovalStore;
import com.csv_db.repositories_store.ApprovalRepository;
import com.csv_db.repositories_store.StoreRepository;
import com.csv_db.services_store.ApprovalService;
import com.csv_db.services_store.SaveInformation;

@ExtendWith(MockitoExtension.class)
class ApprovalServiceTest {

	@InjectMocks
	ApprovalService approvalService;

	@Mock
	ApprovalRepository approvalRepository;

	@Mock
	StoreRepository storeRepository;

	@Spy
	SaveInformation saveInfo;

	@Spy
	ModelMapper modelMapper;

	@Test
	void saveForApprovalTest1() throws IOException {
		ForApprovalStore store = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		when(approvalRepository.save(store)).thenReturn(store);
		assertEquals(true, approvalService.saveForApproval(store));
	}

	@Test
	void saveForApprovalTest2() throws IOException {
		ForApprovalStore store = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		when(approvalRepository.save(store)).thenThrow();
		assertEquals(false, approvalService.saveForApproval(store));
	}

	@Test
	void getApprovalChangesTest1() throws IOException {
		ForApprovalStore store1 = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		List<ForApprovalStore> store = new ArrayList<>();
		store.add(store1);
		when(approvalRepository.findAll()).thenReturn(store);
		assertEquals(store.get(0), approvalService.getApprovalChanges());
	}

	@Test
	void getApprovalChangesTest2() throws IOException {
		ForApprovalStore store1 = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		List<ForApprovalStore> store = new ArrayList<>();
		store.add(store1);
		when(approvalRepository.findAll()).thenReturn(null);
		assertEquals(null, approvalService.getApprovalChanges());
	}

	@Test
	void deleteApprovalDataTest1() throws IOException {
		ForApprovalStore store1 = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		List<ForApprovalStore> store = new ArrayList<>();
		store.add(store1);
		when(approvalRepository.findAll()).thenReturn(store);
		assertTrue(approvalService.deleteApprovalData());
	}

	@Test
	void deleteApprovalDataTest2() throws IOException {
		ForApprovalStore store1 = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		List<ForApprovalStore> store = new ArrayList<>();
		store.add(store1);
		when(approvalRepository.findAll()).thenThrow();
		assertFalse(approvalService.deleteApprovalData());
	}

	@Test
	void checkEmptyHrsTest1() throws IOException {
		assertEquals("09am-07pm", approvalService.checkEmptyHrs("09am-07pm"));
	}

	@Test
	void checkEmptyHrsTest2() {
		assertEquals("Holiday", approvalService.checkEmptyHrs(null));
	}

	@Test
	void checkEmptyHrsTest3() {
		assertEquals("Holiday", approvalService.checkEmptyHrs("Holiday"));
	}

	@Test
	void checkEmptyHrsTest4() {
		assertEquals("Invalid Time", approvalService.checkEmptyHrs("Invalid Time"));
	}

	@Test
	void updateApprovalDataTest1() throws IOException {
		ForApprovalStore store1 = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		List<ForApprovalStore> store = new ArrayList<>();
		store.add(store1);
		when(approvalRepository.findAll()).thenThrow();
		assertEquals(false, approvalService.updateApprovalData());
	}

	@Test
	void updateApprovalDataTest2() throws IOException {
		ForApprovalStore store1 = new ForApprovalStore("adb4cf4WQA", "Pune", "7350095218", "Keshavnagar", "09am-07pm",
				"09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm", "09am-07pm");
		List<ForApprovalStore> store = new ArrayList<>();
		store.add(store1);
		when(approvalRepository.findAll()).thenReturn(store);
		assertEquals(true, approvalService.updateApprovalData());
	}
}
