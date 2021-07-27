package org.projectx.rest.jersey.config.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * 
 * Fix the CORS error
 * Access to fetch at 'http://localhost:8080/projectx-rest-jersey/webapi/customers' 
 * from origin 'http://127.0.0.1:5500' has been blocked by CORS policy: 
 * No 'Access-Control-Allow-Origin' header is present on the requested resource
 * 
 * It seems the domain + port are representing an unique origin
 * so we need to add the port also in the allow origin header valuee 
 */
@Provider
public class CorsResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		
		responseContext.getHeaders().add(
				"Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		
		responseContext.getHeaders().add(
				"Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		
		responseContext.getHeaders().add(
				"Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
		/*
		responseContext.getHeaders().add(
				"Access-Control-Allow-Credentials", "true");
		*/
		
	}
	
}
