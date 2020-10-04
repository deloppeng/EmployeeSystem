package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class testEmployee {
  private MockMvc mockMvc;
  
  @InjectMocks
  private EmployeeController employeeController;
  
  	@Autowired
	private EmployeeService employeeService;

	@Before
	public void setup() {
	    MockitoAnnotations.initMocks(this);
	    this.mockMvc = MockMvcBuilders.standaloneSetup(employeeService).build();
	}
	

	//新增員工資料單元測試
	@Test
	public void addEmployeeTest() {
	//設定測試資料
  	Employee employee = new Employee();
  	String Address ="新北市";
  	int age =30;
  	int department_id =1;
  	String eid="R-test-1";
  	String gender="F";
  	String name="文一一";
  	String phone="0912345678";
  	
  	employee.setAddress(Address);
  	employee.setAge(age);
  	employee.setDepartment_id(department_id);
  	employee.setEid(eid);
  	employee.setGender(gender);
  	employee.setName(name);
  	employee.setPhone(phone);

  	Employee returnsEmployee = employeeService.addEmployee(employee);
  	
  	//調用測試方法
    assertEquals( (Object) Address, returnsEmployee.getAddress()); 
    assertEquals( (Object) age, returnsEmployee.getAge()); 
    assertEquals( (Object) department_id, returnsEmployee.getDepartment_id()); 
    assertEquals( (Object) eid, returnsEmployee.getEid()); 
    assertEquals( (Object) gender, returnsEmployee.getGender()); 
    assertEquals( (Object) name, returnsEmployee.getName()); 
    assertEquals( (Object) phone, returnsEmployee.getPhone());
	}

	//更新員工資料單元測試
	@Test
	public void updateEmployeeTest() {
  	Employee employee = new Employee();
  	String Address ="基隆市";
  	int age =25;
  	int department_id =3;
  	String eid="R-testUp-01";
  	String gender="M";
  	String name="文一二";
  	String phone="021234567";
  	int employee_id =26;
  	employee.setAddress(Address);
  	employee.setAge(age);
  	employee.setDepartment_id(department_id);
  	employee.setEid(eid);
  	employee.setGender(gender);
  	employee.setName(name);
  	employee.setPhone(phone);

  	employeeService.updateEmployee(employee,employee_id);

  	Employee returnsEmployee = employeeService.getEmployeeById(employee_id);
  	
  	//調用測試方法
    assertEquals( (Object) Address, returnsEmployee.getAddress()); 
    assertEquals( (Object) age, returnsEmployee.getAge()); 
    assertEquals( (Object) department_id, returnsEmployee.getDepartment_id()); 
    assertEquals( (Object) eid, returnsEmployee.getEid()); 
    assertEquals( (Object) gender, returnsEmployee.getGender()); 
    assertEquals( (Object) name, returnsEmployee.getName()); 
    assertEquals( (Object) phone, returnsEmployee.getPhone()); 
	}
	
	//搜尋員工資料單元測試
	 @Test
	public void searchEmployeeTest() throws ParseException {
  	Employee employee = new Employee();
  	int age =28;
  	int department_id =2;
  	String name="王大一";
  	String eid="R-3";
      
  	employee.setName(name);
	int page = 0;
	int size = 10;
	String sortColumn="id";
    String  sortType = "ASC";
	    
  	Page<Employee> returnsEmployee = employeeService.findEmployeeByCondition( employee, page,  size,  sortColumn,  sortType);
  	
  	//調用測試方法
    assertEquals( (Object) age, returnsEmployee.getContent().get(0).getAge()); 
    assertEquals( (Object) department_id, returnsEmployee.getContent().get(0).getDepartment_id());
    assertEquals( (Object) name, returnsEmployee.getContent().get(0).getName()); 
	}
	 
	//刪除員工資料單元測試
	@Test
	public void delEmployeeTest() {
		//設定測試資料
	  	String returnMsg ="Deleted 15";
	  	int delete_id =15;

	  	String returnsEmployee = employeeService.deleteEmployeeById(delete_id);
	  	
	  	//調用測試方法
	  	assertTrue(returnMsg.equals(returnsEmployee));
	}
	
}







