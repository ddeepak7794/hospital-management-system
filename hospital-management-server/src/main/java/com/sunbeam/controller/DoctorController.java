package com.sunbeam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dtos.DoctorDataBackinBean;
import com.sunbeam.dtos.DoctorVisitsDataBackinBean;
import com.sunbeam.dtos.Response;
import com.sunbeam.dtos.PatientDataBacking;
import com.sunbeam.services.DoctorServices;
import com.sunbeam.services.DoctorVisitsServices;
import com.sunbeam.services.PatientServices;
@CrossOrigin("*")
@RestController @RequestMapping("/api/doctor")
public class DoctorController {
	@Autowired
	DoctorServices  doctorServices;
	@Autowired
	DoctorVisitsServices visitService;
	@Autowired
	PatientServices patientService;
	
	
	@GetMapping("/getAllDoctors")
	public ResponseEntity<?> getAllPatients(){
		List<DoctorDataBackinBean> doctors=doctorServices.getAllDoctors();
		
		return Response.success(doctors);
	}
	@PostMapping("/addPrescription")
	public void updatePatient(@RequestBody PatientDataBacking patientData) {
		doctorServices.updatePatientDetails(patientData);
	}
	
	@PostMapping("/increaseVisitCount")
	public ResponseEntity<?> increaseVisitCount(@RequestBody DoctorVisitsDataBackinBean visitData) {
		visitService.increaseVisitCount(visitData);
		return Response.success("increamented");
	}
	
	@GetMapping("/getPatients/{id}")
	public ResponseEntity<?> getPatientsOfDoctor(@PathVariable int id){
		List<PatientDataBacking> patientList=patientService.getPatientsOfDocter(id);
		return Response.success(patientList);
	}
	
	
	
}
