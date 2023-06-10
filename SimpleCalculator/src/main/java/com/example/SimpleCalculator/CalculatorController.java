package com.example.SimpleCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("http://localhost:4200")
@RestController

public class CalculatorController {
	@Autowired
	CalculatorServices service;
	@PostMapping
	public String getData(@RequestBody String expression) {
		String result=service.calculate(expression);
		if(result.equals("Infinity")|| result.equals("-Infinity")) {
			return "E";
		}
		return result;
		
	}
	

}
