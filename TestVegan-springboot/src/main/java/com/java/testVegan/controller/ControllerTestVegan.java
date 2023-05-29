package com.java.testVegan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.testVegan.service.ServiceTestVegan;

@RequestMapping("/")
@RestController
public class ControllerTestVegan {
	
	@Autowired
	ServiceTestVegan serviceTestVegan;
	
	
	//http://localhost:8080/listIdPaccoAndMaxPeso
	
	@RequestMapping("/listIdPaccoAndMaxPeso")
	public List<String> getListPacco(){
		return serviceTestVegan.getListMockPacchiService();
	}
	
	
}
