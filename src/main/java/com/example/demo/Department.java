package com.example.demo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Department {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Integer id;
    private String name;
	/**
	 * @return the name
	 */
    
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Department> department;
    
    public Integer getId() {
		return id;
	}
	/**
	 * @param name the name to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
    
    
    
}
