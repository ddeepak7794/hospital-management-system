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
import com.sunbeam.dtos.Response;
import com.sunbeam.dtos.PatientDataBacking;
import com.sunbeam.dtos.WardDataBackinBean;
import com.sunbeam.services.DoctorServices;
import com.sunbeam.services.PatientServices;
import com.sunbeam.services.WardServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ward")
public class WardController {
	@Autowired
	WardServices wardServices;

	@GetMapping("/getAllWards")
	public ResponseEntity<?> getAllWards() {
		List<WardDataBackinBean> allWards = wardServices.getAllWards();

		return Response.success(allWards);
	}

	@PostMapping("/increaseBedCount")
	public ResponseEntity<?> increaseBedCount(@RequestBody WardDataBackinBean wardData) {
String increaseBedCount = wardServices.increaseBedCount(wardData);
		System.out.println(("-------------------------request-----------increase"));
		if(increaseBedCount.equals("SUCCESS"))
		return Response.success("INCREASED");
		return Response.success("FAILED");

	}
	@PostMapping("/decreaseBedCount")
	public ResponseEntity<?> decreaseBedCount(@RequestBody WardDataBackinBean wardData) {
		System.out.println(("-------------------------request-----------decrease"));
		String increaseBedCount = wardServices.decreaseBedCount(wardData);
		
		if(increaseBedCount.equals("SUCCESS"))
		return Response.success("DECREASED");
		return Response.success("FAILED");
		
	}

	@PostMapping("/addWard")
	public ResponseEntity<?> addWard(@RequestBody WardDataBackinBean wardData) {
		int updateCount = wardServices.addWard(wardData);
		if (updateCount == 1)
			return Response.success("WARD_ADDED");
		return Response.success("FAILED");
	}

	@GetMapping("/removeWard/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable("id") int wardId) {
		wardServices.removeWard(wardId);
		return Response.success("WARD_REMOVED");
	}

}
