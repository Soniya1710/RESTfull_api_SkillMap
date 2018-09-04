package com.niit.RestEmployeeProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.EmployeeDaoBackend.Model.Employee;
import com.niit.EmployeeDaoBackend.Service.EmployeeService;

@RestController
@RequestMapping("/api/angular4")
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeRestController {
	@Autowired
	 private EmployeeService serveobj;
	 
	 @GetMapping
	 public List<Employee>getAll()
	 {
		 List<Employee>detail=serveobj.getAll();
		 
		return detail;
		 
	 }
	 @GetMapping("/{employeeid}")
	 public ResponseEntity<Employee>getEmployees(@PathVariable("employeeid")int employeeid)
	 {
		 if(serveobj.getEmployeebyId(employeeid)!=null)
		 {
			 return new ResponseEntity<Employee>(serveobj.getEmployeebyId(employeeid),HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
			 
		 }
	 }
	 
	 @DeleteMapping("/{employeeid}")
	 public ResponseEntity <Void> deleteEmployee(@PathVariable("employeeid")int employeeid)
	 {
		 if(serveobj.getEmployeebyId(employeeid)!=null)
		 {
			 
			 serveobj.deleteService(employeeid);
			 
			 return new ResponseEntity<Void>(HttpStatus.OK);
			 
		 }
		 else
		 {
			 return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			 
		 }
	 }
	 @PostMapping//("/adding")
		public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
			
				serveobj.addservice(employee);
				return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		
	 @PutMapping//("update/{employeeid}")
		public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
			
			if(serveobj.getEmployeebyId(employee.getEmployeeid())!=null) {
				serveobj.updateEmployee(employee);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else {
				
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		}
	 }
	 



