package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
	@Autowired
    DepartmentService service;
	
	//部門列表
    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        List<Department> list = service.getAll();
 
        return new ResponseEntity<List<Department>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Integer id) 
                                                      {
        Department entity = service.getDepartmentById(id);
 
        return new ResponseEntity<Department>(entity, HttpStatus.OK);
    }
    
    //新增部門資料
    @PostMapping
    public ResponseEntity<Department> createDepartment(Department department)
                                                      {
        Department createDepartment = service.addDepartment(department);
        return new ResponseEntity<Department>(createDepartment, HttpStatus.OK);
    }
    
    //更新部門資料
    @PutMapping("/{id}")
    public ResponseEntity<Department> UpdateDepartment(Department department, @PathVariable("id") Integer id)
                                                      {
        Department updateDepartment = service.updateDepartment(department, id);
        return new ResponseEntity<Department>(updateDepartment, HttpStatus.OK);
    }
 
    //刪除部門資料
    @DeleteMapping("/{id}")
    public ResponseEntity<List<String>> deleteDepartmentById(@PathVariable("id") Integer id) {
    	String resultStr = service.deleteDepartmentById(id);
        List<String> returnStr= new ArrayList();
        returnStr.add(resultStr);
        return new ResponseEntity<List<String>>(returnStr, HttpStatus.OK);
    }
    
    @GetMapping("/find/{name}")
    public ResponseEntity<List<Department>> findByName(@PathVariable("name") String name) {
        List<Department> list = service.getDepartmentByName(name);
 
        return new ResponseEntity<List<Department>>(list, HttpStatus.OK);
    }

    
}

