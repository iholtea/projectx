package org.ionuth.gen.services;

import java.util.Date;

public interface DateService {
	
	Date getDate(int startYear, int endYear);
	
	Date getDateTime(int startYear, int endYear);
	
}
