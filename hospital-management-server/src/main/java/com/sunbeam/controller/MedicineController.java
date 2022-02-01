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
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dtos.MedicineAssignedDataBackinBean;
import com.sunbeam.dtos.Response;
import com.sunbeam.dtos.WardDataBackinBean;
import com.sunbeam.services.MedicineServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
	@Autowired
	MedicineServices medicineServices;

	@GetMapping("getAllMedicines")
	public ResponseEntity<?> getAllMedicine() {
		List<MedicineAssignedDataBackinBean> allMedicines = medicineServices.getAllMedicines();
		if (allMedicines != null)
			return Response.success(allMedicines);
		return Response.error("NO_LIST_FOUND");
	}

	@PostMapping("/addMedicine")
	public ResponseEntity<?> addWard(@RequestBody MedicineAssignedDataBackinBean medicineData) {
		int updateCount = medicineServices.addMedicine(medicineData);
		if (updateCount == 1)
			return Response.success("MEDICINE_ADDED");
		return Response.success("FAILED");
	}

	@GetMapping("/removeMedicine/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable("id") int medicineId) {
		medicineServices.removeMedicine(medicineId);
		return Response.success("MEDICINE_REMOVED");
	}

}
