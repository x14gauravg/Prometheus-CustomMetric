package com.x14gauravg;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;

@Component
@Order(1)
public class MetricFilter implements Filter {
	
	@Autowired
	MeterRegistry registry;


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		chain.doFilter(request, response);

		Random rand = new Random();
		if(rand.nextInt()%10==5 || rand.nextInt()%10 == 7) {
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    registry.counter("custom.metrics.400").increment();
		    System.out.println("Bad request");
			
		}else {
			httpResponse.setStatus(HttpServletResponse.SC_OK);
		    registry.counter("custom.metrics.200").increment();
		}		
		
	}

}
