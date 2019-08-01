package com.x14gauravg;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@Timed
public class TestController {
	
	 @Autowired
	  MeterRegistry registry;

	@GetMapping(value = "/hello")
	public ResponseEntity<String> hello() {
		
		Random rand = new Random();
		
		// counter to count different types of messages received
	    registry.counter("custom.metrics.message").increment();
	    
		return new ResponseEntity<String>("Hello World",HttpStatus.BAD_REQUEST);
		
	}
	

}
