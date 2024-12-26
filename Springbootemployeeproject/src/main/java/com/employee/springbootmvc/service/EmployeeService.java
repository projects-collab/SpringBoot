package com.employee.springbootmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.springbootmvc.employeemodel.EmployeeModel;
import com.employee.springbootmvc.entity.EmployeeEntity;
import com.employee.springbootmvc.repository.EmployeeRepository;

@Service
public class EmployeeService {
        @Autowired
        EmployeeRepository employeeRepository;

		public void saveEmployeeData(EmployeeModel employeeModel) {
			
			double da;
			da = 0.2 * employeeModel.getSalary();
			
			double hra;
			hra = 0.3 * employeeModel.getSalary();
			
			double totalsalary;
			totalsalary = da + hra + employeeModel.getSalary();
			
			// create the object for employee entity class and save that class in database
			EmployeeEntity employeeEntity = new EmployeeEntity();
			
			employeeEntity.setId(employeeModel.getId());
			employeeEntity.setName(employeeModel.getName());
			employeeEntity.setPhoneno(employeeModel.getPhoneno());
			employeeEntity.setEmail(employeeModel.getEmail());
			employeeEntity.setGender(employeeModel.getGender());
			employeeEntity.setDoj(employeeModel.getDoj());
			employeeEntity.setDeptname(employeeModel.getDeptname());
			employeeEntity.setDeptno(employeeModel.getDeptno());
			employeeEntity.setSalary(employeeModel.getSalary());
			employeeEntity.setDa(da);
			employeeEntity.setHra(hra);
			employeeEntity.setTotalsalary(totalsalary);
			
			employeeRepository.save(employeeEntity);
		}
		
		
		
		// to get employee details from the database

		public List<EmployeeEntity> getEmployeeDetails() 
		{
			List<EmployeeEntity>  employee  = employeeRepository.findAll();
			return employee;
		}


		
        // search employee based on id
		public EmployeeEntity searchEmployeeById(int id)
		{
			Optional<EmployeeEntity> optionaldata = employeeRepository.findById(id);
			
			if(optionaldata.isPresent())
			{
				  EmployeeEntity employee = optionaldata.get();
				  return employee;
			}
			else
			{
				return null;
			}
		}


        
		// delete employee details 
		public void deleteEmployeeById(int id) 
		{
			employeeRepository.deleteById(id);
		}


        
		// edit employee details
		public EmployeeModel editById(int id)
		{
			Optional <EmployeeEntity> optional = employeeRepository.findById(id);
			
			if(optional.isPresent())
			{
				EmployeeEntity employeeEntity = optional.get();
				
				EmployeeModel employeeModel = new EmployeeModel();
				employeeModel.setId(employeeEntity.getId());
				employeeModel.setName(employeeEntity.getName());
				employeeModel.setPhoneno(employeeEntity.getPhoneno());
				employeeModel.setEmail(employeeEntity.getEmail());
				employeeModel.setGender(employeeEntity.getGender());
				employeeModel.setDoj(employeeEntity.getDoj());
				employeeModel.setDeptname(employeeEntity.getDeptname());
				employeeModel.setDeptno(employeeEntity.getDeptno());
				
				return employeeModel;
			}
			else
			{
				return null;
			}
		}



		public void updateEmployeeById(int id, EmployeeModel employeeModel)
		{
			Optional <EmployeeEntity> optional = employeeRepository.findById(id);	
			
			if(optional.isPresent())
			{
			    EmployeeEntity employeeEntity = optional.get();
			    
			    employeeEntity.setName(employeeModel.getName());
			    employeeEntity.setPhoneno(employeeModel.getPhoneno());
			    employeeEntity.setEmail(employeeModel.getEmail());
			    employeeEntity.setGender(employeeModel.getGender());
			    employeeEntity.setDoj(employeeModel.getDoj());
			    employeeEntity.setDeptname(employeeModel.getDeptname());
			    employeeEntity.setDeptno(employeeModel.getDeptno());
			    employeeEntity.setSalary(employeeModel.getSalary());
			    
			    double da;
				da = 0.2 * employeeModel.getSalary();
				
				employeeEntity.setDa(da);
				
				double hra;
				hra = 0.3 * employeeModel.getSalary();
				
				employeeEntity.setHra(hra);
				
				double totalsalary;
				totalsalary = da + hra + employeeModel.getSalary();
				
				employeeEntity.setTotalsalary(totalsalary);
				
				employeeRepository.save(employeeEntity);
			}
		}
		
}
