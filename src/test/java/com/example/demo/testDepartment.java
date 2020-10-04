package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class testDepartment {
	  private MockMvc mockMvc;
	  
	  @InjectMocks
	  private DepartmentController DepartmentController;
	  
	  	@Autowired
		private DepartmentService departmentService;

		@Before
		public void setup() {
		    MockitoAnnotations.initMocks(this);
		    this.mockMvc = MockMvcBuilders.standaloneSetup(departmentService).build();
		}
		
		//新增部門資料單元測試
		@Test
		public void addDepartmentTest() {
		//設定測試資料
	  	Department department = new Department();
	  	String department_name ="新一部";
	  	
	  	department.setName(department_name);

	  	Department returnDepartment = departmentService.addDepartment(department);
	  	
	  	//調用測試方法
	    assertEquals( (Object) department_name, returnDepartment.getName()); 
		}

		//更新部門資料單元測試
		@Test
		public void updateDepartmentTest() {
			//設定測試資料
		  	Department department = new Department();
		  	String department_name ="新天一部";
		  	Integer department_id =1;
		  	
		  	department.setName(department_name);

		  	departmentService.updateDepartment(department,department_id);
		  	Department returnDepartment = departmentService.getDepartmentById(department_id);
		  	
		  	//調用測試方法
		    assertEquals( (Object) department_name, returnDepartment.getName()); 
		}
		 
		//刪除部門資料單元測試
		@Test
		public void delDepartmentTest() {
			//設定測試資料
		  	String returnMsg ="Delete Department:2";
		  	int delete_id =2;

		  	String returnsDepartment = departmentService.deleteDepartmentById(delete_id);
		  	
		  	//調用測試方法
		  	assertTrue(returnMsg.equals(returnsDepartment));
		}
}
