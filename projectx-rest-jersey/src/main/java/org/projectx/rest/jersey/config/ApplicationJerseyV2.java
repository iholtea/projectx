package org.projectx.rest.jersey.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.projectx.rest.jersey.resource.HelloResourceB;

@ApplicationPath("webapiv2")
public class ApplicationJerseyV2 extends Application {
	
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(HelloResourceB.class);
        return s;
    }
	
}
