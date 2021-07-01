package org.projectx.rest.jersey.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.projectx.rest.jersey.resource.HelloResourceA;

@ApplicationPath("webapiv1")
public class ApplicationJersey extends Application {
	
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(HelloResourceA.class);
        return s;
    }
	
}
