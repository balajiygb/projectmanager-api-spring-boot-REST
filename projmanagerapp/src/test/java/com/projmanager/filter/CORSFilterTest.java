package com.projmanager.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class CORSFilterTest {

	@Test
	public void test() throws  Exception {
		System.out.println("CORS FILTER TEST");
		CORSFilter corsFilter= new CORSFilter();
		
		 MockHttpServletRequest request = new MockHttpServletRequest("OPTIONS", "/uaa/userinfo");
	        request.addHeader("Access-Control-Allow-Origin", "Origin");
	        request.addHeader("Access-Control-Allow-Credentials", "true");
	        request.addHeader("Access-Control-Request-Method", "POST, GET, OPTIONS, DELETE");
	        request.addHeader("Access-Control-Max-Age", "3600");
	        request.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
	        request.addHeader("Origin", "example.com");
	        
	       
	        MockHttpServletResponse response = new MockHttpServletResponse();
	        FilterChain filterChain = newMockFilterChain();

	        corsFilter.doFilter(request, response, filterChain);

	}
	
	  private static FilterChain newMockFilterChain() throws  Exception {
	        FilterChain filterChain = new FilterChain() {

	            @Override
	            public void doFilter(final ServletRequest request, final ServletResponse response)
	                    throws IOException,
	                    ServletException {
	                // Do nothing.
	            }
	        };
	        return filterChain;
	    }
	


}
