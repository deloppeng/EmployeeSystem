package com.example.demo;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
class Sb1ApplicationTests {
    private MockMvc mockMvc;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }


    private EmployeeController employeeController;
	@SuppressWarnings("deprecation")
	@Test
	void contextLoads() {
		
//		List<Employee> testList = employeeController.getAllemployee();
//
//		int aaa =0;
//		Employee result = testList.get(0);  
//
//	        //验证方法调用(是否调用了get(0))  
//	        verify(testList).get(0);  
        //junit测试  
//        Assert.assertEquals("一", result.getName());  
        
		/*
		mockMvc.perform(get("/springmvc/api/getUser/{id}", userId))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("id").value(userId))
        .andExpect(jsonPath("name").value(userName))
        .andExpect(jsonPath("password").value(userPassword));
*/
		
	}

}
