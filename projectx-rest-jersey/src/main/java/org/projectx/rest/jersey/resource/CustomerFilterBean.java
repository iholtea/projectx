package org.projectx.rest.jersey.resource;

import javax.ws.rs.QueryParam;

public class CustomerFilterBean {
	
	private @QueryParam("pageSize") int pageSize;
	private @QueryParam("pageStart") int pageStart;
	private @QueryParam("age") int age;
	private @QueryParam("compareType") String compareType;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCompareType() {
		return compareType;
	}
	public void setCompareType(String compareType) {
		this.compareType = compareType;
	}
	
	
	
}
