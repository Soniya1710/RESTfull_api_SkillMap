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

import com.niit.EmployeeDaoBackend.Model.Skill;
import com.niit.EmployeeDaoBackend.Service.SkillService;

@RestController
@RequestMapping("/api/skillmap")
@CrossOrigin(origins="http://localhost:4200")
public class SkillMapController {
	@Autowired
	private SkillService skillService;
	@GetMapping
	 public List<Skill>getAll()
	 {
		 List<Skill>details=skillService.getAllSkill();
		 
		return details;
		 
	 }
	@GetMapping("/{employeeid}")
	 public ResponseEntity<Skill>getEmployees(@PathVariable("employeeid")int employeeid)
	 {
		 if(skillService.getEmployeebyId(employeeid)!=null)
		 {
			 return new ResponseEntity<Skill>(skillService.getEmployeebyId(employeeid),HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<Skill>(HttpStatus.NOT_FOUND);
			 
		 }
	 }
	 
	@PostMapping("/adding")
	public ResponseEntity<Void> addSkill(@RequestBody Skill skill) {
		
		skillService.add(skill);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@DeleteMapping("/{employeeid}")
	 public ResponseEntity <Void> deleteEmployee(@PathVariable("employeeid")int employeeid)
	 {
		 if(skillService.getEmployeebyId(employeeid)!=null)
		 {
			 
			 skillService.delete(employeeid);
			 
			 return new ResponseEntity<Void>(HttpStatus.OK);
			 
		 }
		 else
		 {
			 return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			 
		 }
	 }
	
	@PutMapping//("update/{employeeid}")
	public ResponseEntity<Void> update(@RequestBody Skill employee) {
		
		if(skillService.getEmployeebyId(employee.getEmployeeid())!=null) {
			skillService.update(employee);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}

