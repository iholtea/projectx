package org.projectx.rest.jersey.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.projectx.rest.jersey.config.exception.DataNotFoundExceptionMapper;
import org.projectx.rest.jersey.resource.CustomerAddressResource;
import org.projectx.rest.jersey.resource.CustomerResource;
import org.projectx.rest.jersey.resource.other.DemoResource;

@ApplicationPath("webapi")
public class ApplicationJersey extends Application {
	
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(CustomerResource.class);
        s.add(CustomerAddressResource.class);
        s.add(DemoResource.class);
        return s;
    }
	
	@Override
	public Set<Object> getSingletons() {
	    Set<Object> set = new HashSet<>();
	    set.add(new DataNotFoundExceptionMapper());
	    return set;
	}
	
	
}
