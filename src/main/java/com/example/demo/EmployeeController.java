package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	 
	@Autowired
    EmployeeService service;
 
	//員工列表
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) Integer page, Integer size, String sortColumn, String sortType) {
    	if(page == null) {
    		page = 0;
    	}else {
    		page -= 1;
    	}
    	
    	if(size == null) {
    		size = 10;
    	}
    	
    	if(sortColumn == null) {
    		sortColumn="";
    	}
    

		if(sortType == null) {
			sortType = "DESC";
		}
    		
    	
        List<Employee> list = service.getPagedAll(page, size, sortColumn, sortType);
 
        return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }
	
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) 
                                                      {
        Employee entity = service.getEmployeeById(id);
 
        return new ResponseEntity<Employee>(entity, HttpStatus.OK);
    }
    
    //新增員工資料
    @PostMapping
    public ResponseEntity<Employee> createEmployee(Employee employee)
                                                      {
        Employee createEmployee = service.addEmployee(employee);
        return new ResponseEntity<Employee>(createEmployee, HttpStatus.OK);
    }
    
    //更新員工資料
    @PutMapping("/{id}")
    public ResponseEntity<Employee> UpdateEmployee(Employee employee, @PathVariable("id") Integer id)
                                                      {
        Employee updateEmployee = service.updateEmployee(employee, id);
        return new ResponseEntity<Employee>(updateEmployee, HttpStatus.OK);
    }
 
    //刪除員工資料
    @DeleteMapping("/{id}")
    public ResponseEntity<List<String>> deleteEmployeeById(@PathVariable("id") Integer id) {
        String resultStr = service.deleteEmployeeById(id);
        
        List<String> returnStr= new ArrayList();
        returnStr.add(resultStr);
        return new ResponseEntity<List<String>>(returnStr, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<List<Employee>> findByName(@PathVariable("name") String name) {
        List<Employee> list = service.getEmployeeByName(name);
 
        return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }
    
    //查詢員工資料
    @PostMapping("/search")
//    public ResponseEntity<List<Employee>> findByCondition(Employee employee) {
    public ResponseEntity<Page<Employee>> findByCondition(@RequestParam(required = false) Integer page, Integer size, String sortColumn, String sortType,Employee employee) {
    	    	if(page == null) {
    	    		page = 0;
    	    	}else {
    	    		page -= 1;
    	    	}
    	    	
    	    	if(size == null) {
    	    		size = 10;
    	    	}
    	    	
    	    	if(sortColumn == null) {
    	    		sortColumn="id";
    	    	}
    	    

    			if(sortType == null) {
    				sortType = "ASC";
    			}

    			Page<Employee> list = service.findEmployeeByCondition(employee,page, size, sortColumn, sortType);

 
        return new ResponseEntity<Page<Employee>>(list, HttpStatus.OK);
    }

}

