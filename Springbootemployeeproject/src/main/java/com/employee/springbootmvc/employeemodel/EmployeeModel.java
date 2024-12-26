package com.employee.springbootmvc.employeemodel;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {
	
	 @Id
	    @Positive(message = "*Please provide a valid positive number for ID")
	    private int id;

	    @NotBlank(message = "*Name field must not be blank")
	    private String name;

	    
	    private long phoneno;


	    @Email(message = "*Please provide a valid email address")
	    private String email;


	    private String gender;

	    @NotBlank(message = "*please enter the joining date") private String doj; // Consider using `@PastOrPresent` for dates if applicable

	    @NotBlank(message = "*Please enter the department name")
	    private String deptname;

	    @Positive(message = "*Please enter a valid department number (positive integer only)")
	    private int deptno;

	    @Positive(message = "*Please enter a valid salary")
	    private double salary;

}
