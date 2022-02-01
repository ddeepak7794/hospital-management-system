package com.sunbeam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dtos.Response;
import com.sunbeam.dtos.ChargesCalculationBeanPatient;
import com.sunbeam.dtos.MedicineAssignedDataBackinBean;
import com.sunbeam.dtos.PatientDataBacking;
import com.sunbeam.services.PatientServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/patient")
public class PatientController {
	@Autowired
	PatientServices pServices;

	@PostMapping("/addPatient")
	public ResponseEntity<?> addPatient(@RequestBody PatientDataBacking patientData) {
		int updateCount = pServices.addPatient(patientData);
		if (updateCount == 1)
			return Response.success("added");
		return Response.error("adding failed");
	}

	@GetMapping("/getAllPatients")
	public ResponseEntity<?> getAllPatients() {
		List<PatientDataBacking> patients = pServices.getAllPatients();

		return Response.success(patients);
	}

	@GetMapping("/getPatient/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable("id") int patientId) {
		PatientDataBacking patient = pServices.getPatientById(patientId);
		if (patient != null)
			return Response.success(patient);
		return Response.success("failed invalid patient id");

	}

	@DeleteMapping("/removePatient/{id}")
	public ResponseEntity<?> deletePatientById(@PathVariable("id") int patientId) {
		pServices.removePatientById(patientId);
		return Response.success("success removed");

	}

	@PostMapping("/updatePatient")
	public void updatePatient(@RequestBody PatientDataBacking patientData) {
		pServices.updatePatientDetails(patientData);
	}

	@GetMapping("/getMedicines/{id}")
	public ResponseEntity<?> getMedicineByPatId(@PathVariable("id") int patientId) {
		List<MedicineAssignedDataBackinBean> medicines = pServices.getMedicineByPatId(patientId);
		if (medicines != null)
			return Response.success(medicines);
		return Response.success("failed invalid medicines id");

	}

	@GetMapping("/getCharges/{id}")
	public ResponseEntity<?> getChargesByPatId(@PathVariable("id") int patientId) {
		ChargesCalculationBeanPatient patientTotalCharges = pServices.calculateChargesByPatId(patientId);
		if (patientTotalCharges != null)
			return Response.success(patientTotalCharges);
		return Response.error("INVALID_PATIENT_ID");

	}

	@PostMapping("/updatePatientPaymentStatus")
	public void updatePatientPaymentStatus(@RequestBody PatientDataBacking patientData) {
		pServices.updatePaymentStatusByPatId(patientData);
	}

	@PostMapping("/bedExists")
	public ResponseEntity<?> checkIfBedIsFree(@RequestBody PatientDataBacking bedData) {
		Boolean bedStatus = pServices.checkIfBedAvailable(bedData);
		if (bedStatus == true)
			return Response.success("BED_NOT_AVAILABLE");
		else
			return Response.success("BED_AVAILABLE");

	}

}
