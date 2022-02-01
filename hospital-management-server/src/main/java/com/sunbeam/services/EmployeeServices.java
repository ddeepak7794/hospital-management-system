package com.sunbeam.services;
import static com.sunbeam.dtos.EmployeeUserDataBacking.*;


import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.daos.IDoctorDao;
import com.sunbeam.daos.IEmployeeDao;
import com.sunbeam.daos.IUserDao;
import com.sunbeam.dtos.EmployeeUserDataBacking;
import com.sunbeam.entities.Employee;
import com.sunbeam.entities.User;

@Service @Transactional
public class EmployeeServices {
	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;
	@Autowired
	IDoctorDao doctorDao;
	
	
	//************fuction to add new employee
	public int addEmployee(EmployeeUserDataBacking userData) {
		
		if(userData.getRole().equalsIgnoreCase("doctor")) {
			
			userDao.insertIntoUsers(0, userData.getFirstName(), userData.getLastName(), userData.getEmail(), userData.getPassword(), userData.getCellNo(), userData.getRole(), userData.getSecurityQuestion(), userData.getSecurityAnswer());
			User user=userDao.findByEmail(userData.getEmail());//to get userId
			System.out.println("user id : "+user.getId());
			int updateCount=employeeDao.insertIntoEmployeesTable(0, user.getId(), userData.getDob(), userData.getHireDate(), userData.getSalary());
				int empId=employeeDao.getEmpIdByEmail(userData.getEmail());
				System.out.println("emp id : "+empId);
			doctorDao.insertIntoDoctorTable(0, empId, userData.getDoctorCharges());
			return updateCount;
		}
		else {
			userDao.insertIntoUsers(0, userData.getFirstName(), userData.getLastName(), userData.getEmail(), userData.getPassword(), userData.getCellNo(), userData.getRole(), userData.getSecurityQuestion(), userData.getSecurityAnswer());
			User user=userDao.findByEmail(userData.getEmail());//to get userId
			int updateCount=employeeDao.insertIntoEmployeesTable(0, user.getId(), userData.getDob(), userData.getHireDate(), userData.getSalary());
			return updateCount;
		}
		
	
	}
	
	

	
	public List<EmployeeUserDataBacking> getAllEmployees(){
		List<Employee> employees=employeeDao.findAll();
		List<EmployeeUserDataBacking> employeeDetails =createEmployee(employees);
		return employeeDetails;
	}
	
	public void updateEmployee(EmployeeUserDataBacking userData) {
		Employee employeeToUpdate =employeeDao.getById(userData.getEmpId());
		
		int updateCount=userDao.updateFirstNameLastNameDobCellNo(userData.getFirstName(), userData.getLastName(), userData.getDob(), userData.getCellNo(), employeeToUpdate.getUser().getId());
		System.out.println("updated row : "+ updateCount);
		
	}
	public int deleteUserByCellNoAndUserId(EmployeeUserDataBacking userData) {
		Employee employeeToDelete=employeeDao.getById(userData.getEmpId());
		System.out.println("\n *************************************************cell no to be deleted : "+employeeToDelete.getUser().getCellNo());
		System.out.println("\n**************************************user_id no to be deleted : "+employeeToDelete.getUser().getId());
		employeeDao.deleteById(userData.getEmpId());
		System.out.println("no of employees deleted : ");
		 return 1;
	}

}
