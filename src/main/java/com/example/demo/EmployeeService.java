package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
     
    @Autowired
    EmployeeRepository repository;

	@PersistenceContext
	EntityManager em;
	
    public List<Employee> getAllEmployees()
    {
        List<Employee> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<Employee>();
        }
    }
     
    public Employee getEmployeeById(Integer id)  
    {
        Optional<Employee> employee = repository.findById(id);
         
        return employee.get();
    }
     
    public List<Employee> getEmployeeByName(String name)  
    {
    	return repository.findByName(name);
    }
    
    public Employee addEmployee(Employee entity)  
    {	
    	entity.setCreated_at(new Date());
    	entity.setUpdated_at(new Date());
    	entity = repository.save(entity);
        
        return entity;
    } 
    
    public Employee updateEmployee(Employee entity, Integer id)  
    {
        Optional<Employee> employee = repository.findById(id);
        try
        {
            Employee newEntity = employee.get();

            if(entity.getName()!=null) {
            	newEntity.setName(entity.getName());
            }
            
            if(entity.getEid()!=null) {
            	newEntity.setEid(entity.getEid());
            }
            
            if(entity.getDepartment_id()!=null) {
            	newEntity.setDepartment_id(entity.getDepartment_id());
            }
            
            if(entity.getGender()!=null) {
            	newEntity.setGender(entity.getGender());
            }
            
            if(entity.getPhone()!=null) {
            	newEntity.setPhone(entity.getPhone());
            }
            
            if(entity.getAddress()!=null) {
            	newEntity.setAddress(entity.getAddress());
            }
            
            if(entity.getAge()!=null) {
            	newEntity.setAge(entity.getAge());
            }
            
            Date current = new Date();
            newEntity.setUpdated_at(current);
            

            newEntity = repository.save(newEntity);
             
            return newEntity;
        }
        catch(Exception e)
        {
        	return null;
        }
    } 
     
    public String deleteEmployeeById(Integer id)  
    {
        Optional<Employee> employee = repository.findById(id);
         
        repository.deleteById(id);
        
        return "Deleted " + id;
    } 
    
    public Page<Employee> findEmployeeByCondition(Employee employeeEntity, int page, int size, String sortColumn, String sortType) {
    	Page<Employee> pageResult = setPage( page,  size,  sortColumn,  sortType);
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
     
        Root<Employee> employee = cq.from(Employee.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (employeeEntity.getName() != null) {
            predicates.add(cb.like(employee.get("name"), "%" + employeeEntity.getName() + "%"));
            
        }
        
        if (employeeEntity.getEid() != null) {
            predicates.add(cb.equal(employee.get("eid"), employeeEntity.getEid()));
        }
        
        if (employeeEntity.getAge() != null) {
            predicates.add(cb.equal(employee.get("age"), employeeEntity.getAge()));
        }
        
        if (employeeEntity.getDepartment() != null && employeeEntity.getDepartment().getName() != null) {
        	Join<Employee, Department> join = employee.join("department");
            predicates.add(cb.equal(join.get("name"), employeeEntity.getDepartment().getName()));
        }

    	if(sortColumn=="") {
    		//預設用ＩＤ升序
            cq.orderBy(cb.asc(employee.get("id"))); 
    	}else {
    		if(sortColumn.equals("department.name")) {
    			
    			switch(sortType){
                case "DESC" :
                	//降序
                	cq.orderBy(cb.desc(employee.join("department").get("name"))); 
                	
        			break; 
                case "ASC" :
        			//升序
                	cq.orderBy(cb.asc(employee.join("department").get("name"))); 
        			break; 
        		}
    		}else {
    			switch(sortType){
                case "DESC" :
                	//降序
                	cq.orderBy(cb.desc(employee.get(sortColumn))); 
                	
        			break; 
                case "ASC" :
        			//升序
                	cq.orderBy(cb.asc(employee.get(sortColumn))); 
        			break; 
        		}
    		}
    			
    		
    	}
    	
        cq.where(predicates.toArray(new Predicate[0]));
        
        List<Employee> result = em.createQuery(cq).getResultList();

        //計數查詢結果條數 
        TypedQuery<Employee> createCountQuery = em.createQuery(cq);

        //SQL查詢物件 
        TypedQuery<Employee> createQuery = em.createQuery(cq); 
        Pageable pageable = (Pageable) PageRequest.of(page, size,Sort.by("id").descending());
        
        //分頁引數 
        Integer pageSize = pageable.getPageSize(); 
        Integer pageNo = pageable.getPageNumber(); 
        
        // 實際查詢返回分頁物件 
        int startIndex = pageSize * pageNo; 
        createQuery.setFirstResult(startIndex); 
        createQuery.setMaxResults(pageable.getPageSize()); 

        Page<Employee> pageRst = 
        		new PageImpl<Employee>(createQuery.getResultList(),  pageable, createCountQuery.getResultList().size()); 
        
        return pageRst;
    }
    
    public Page<Employee>  setPage(int page, int size, String sortColumn, String sortType)
    {
    	Page<Employee> pageResult = null;
    	switch(sortColumn) {
		case "created_at" :
			sortColumn= "createdAt";
			break; 
		case "updated_at" :
			sortColumn= "updatedAt";
			break; 
		case "department_id" :
			sortColumn= "departmentId";
			break; 
        default : 
        	break;
		}
    	
    	if(sortColumn=="") {
    		//無排序
        	pageResult = repository.findAll(
                    PageRequest.of(page,  // 查詢的頁數，從0起算
                                    size // 查詢的每頁筆數 
                                    )); 
    	}else {
    		switch(sortType){
            case "DESC" :
            	//降序
            	pageResult = repository.findAll(
                        PageRequest.of(page,  // 查詢的頁數，從0起算
                                        size // 查詢的每頁筆數 
                                        ,Sort.by(sortColumn).descending()
                                        )); // 依CREATE_AT欄位降冪排序
    			break; 
            case "ASC" :
    			//升序
            	
            	pageResult = repository.findAll(
                        PageRequest.of(page,  // 查詢的頁數，從0起算
                                        size // 查詢的每頁筆數 
                                        ,Sort.by(sortColumn).ascending()
                                        )); // 依CREATE_AT欄位降冪排序
    			break; 
    		}
    	}
        pageResult.getNumberOfElements(); // 本頁筆數
        pageResult.getSize();             // 每頁筆數 
        pageResult.getTotalElements();    // 全部筆數
        pageResult.getTotalPages();       // 全部頁數
        return pageResult;
    }
    
    public List<Employee> getPagedAll(int page, int size, String sortColumn, String sortType) {
    	Page<Employee> pageResult = setPage( page,  size,  sortColumn,  sortType);
        List<Employee> employeeList =  pageResult.getContent();
    
        return employeeList;
    
    }
    
    
}