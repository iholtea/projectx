package projectx.dataimport;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class DateService {
	
	private Random rand = new Random();
	
	private Calendar getCalendar(int startYear, int endYear) {
		int year = startYear + rand.nextInt(endYear-startYear);
		int month = rand.nextInt(12);
		int day = 1 + rand.nextInt(28);
		return new GregorianCalendar(year, month, day);
	}
	
	public Date getDate(int startYear, int endYear) {
		Calendar cal = getCalendar(startYear, endYear);
		return cal.getTime(); 
	}

	public Date getDateTime(int startYear, int endYear) {
		Calendar cal = getCalendar(startYear, endYear);
		cal.set(Calendar.HOUR_OF_DAY, rand.nextInt(24));
		cal.set(Calendar.MINUTE, rand.nextInt(59));
		return cal.getTime();
	}

}
