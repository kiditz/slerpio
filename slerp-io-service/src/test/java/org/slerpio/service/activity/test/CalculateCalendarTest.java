package org.slerpio.service.activity.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class CalculateCalendarTest {
	@Test
	public void testGetDateToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);		
		Date today = calendar.getTime();
		System.err.println(today);
		calendar.add(Calendar.DATE, -1);
		Date yesterday = calendar.getTime();
		System.err.println(yesterday);
	}
}
