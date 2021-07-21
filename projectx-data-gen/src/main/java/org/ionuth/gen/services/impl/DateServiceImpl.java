package org.ionuth.gen.services.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.ionuth.gen.services.DateService;

public class DateServiceImpl implements DateService {
	
	Random rand = new Random();
	
	private Calendar getCalendar(int startYear, int endYear) {
		int year = startYear + rand.nextInt(endYear-startYear);
		int month = rand.nextInt(12);
		int day = 1 + rand.nextInt(28);
		return new GregorianCalendar(year, month, day);
	}
	
	@Override
	public Date getDate(int startYear, int endYear) {
		Calendar cal = getCalendar(startYear, endYear);
		return cal.getTime(); 
	}

	@Override
	public Date getDateTime(int startYear, int endYear) {
		Calendar cal = getCalendar(startYear, endYear);
		cal.set(Calendar.HOUR_OF_DAY, rand.nextInt(24));
		cal.set(Calendar.MINUTE, rand.nextInt(59));
		return cal.getTime();
	}
	
	public static void main(String[] args) {
		
		Date randDate;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat tf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		DateServiceImpl ds = new DateServiceImpl();
		
		randDate = ds.getDate(1950, 2000);
		System.out.println( df.format(randDate) );
		randDate = ds.getDate(1950, 2000);
		System.out.println( df.format(randDate) );
		
		randDate = ds.getDateTime(2015, 2021);
		System.out.println( tf.format(randDate) );
		
		randDate = ds.getDateTime(2015, 2021);
		System.out.println( tf.format(randDate) );
	}
	
}
