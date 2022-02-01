package com.sunbeam.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.sunbeam.dtos.DoctorDataBackinBean.*;

import com.sunbeam.daos.IDoctorDao;
import com.sunbeam.daos.IEmployeeDao;
import com.sunbeam.daos.IPatientDao;
import com.sunbeam.daos.IUserDao;
import com.sunbeam.dtos.DoctorDataBackinBean;
import com.sunbeam.dtos.PatientDataBacking;
import com.sunbeam.entities.Doctor;
import com.sunbeam.entities.User;

@Service @Transactional
public class DoctorServices {
	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;
	@Autowired
	IDoctorDao doctorDao;
	@Autowired
	IPatientDao patientDao;
	
	public List<DoctorDataBackinBean> getAllDoctors() {
		List<Doctor> doctors=doctorDao.findAll();
		List<DoctorDataBackinBean> doctorDetail=createDoctorsList(doctors);
		
		return doctorDetail;
		
	}

	public void updatePatientDetails(PatientDataBacking patientData) {
		int updateCount=patientDao.updatePatientPrescription(patientData.getPrescription(),patientData.getPatId());
		
	}
	
	

}
