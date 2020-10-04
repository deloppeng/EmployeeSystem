package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class DepartmentService {
     
    @Autowired
    DepartmentRepository repository;
     
    public List<Department> getAll()
    {
        List<Department> departmentList = repository.findAll();
         
        if(departmentList.size() > 0) {
            return departmentList;
        } else {
            return new ArrayList<Department>();
        }
    }
     
    public Department getDepartmentById(Integer id)  
    {
        Optional<Department> DepartmentObj = repository.findById(id);
         
        return DepartmentObj.get();
    }

    public List<Department> getDepartmentByName(String name)  
    {
    	return repository.findByName(name);
    }
    
    public Department addDepartment(Department entity)  
    {	
    	entity = repository.save(entity);
        
        return entity;
    } 
    
    public Department updateDepartment(Department entity, Integer id)  
    {
        Optional<Department> DepartmentObj = repository.findById(id);
         
        Department newEntity = DepartmentObj.get();
        newEntity.setName(entity.getName());

        newEntity = repository.save(newEntity);
         
        return newEntity;
    } 
     
    public String deleteDepartmentById(Integer id)  
    {
        Optional<Department> DepartmentObj = repository.findById(id);
         
        repository.deleteById(id);
        
        return "Delete Department:"+id;
    } 
}