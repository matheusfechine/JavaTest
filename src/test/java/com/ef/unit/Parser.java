package com.ef.unit;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.ef.model.Log;

public class Parser {

	private Log log;
	
	@Before
	public void setUp(){
		initLog();
	}
	
	private void initLog() {
		//2017-01-01 00:00:11.763|192.168.234.82|"GET / HTTP/1.1"|200|"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0"
		log = new Log();
		log.setId(1L);
		log.setStartDate(new DateTime()
				.withYear(2017)
				.withDayOfMonth(1)
				.withMonthOfYear(1)
				.withHourOfDay(0)
				.withMinuteOfHour(0)
				.withSecondOfMinute(11)
				.withMillisOfSecond(763)
				.toDate());
		log.setIpAddress("192.168.234.82");
		log.setHttpMethod("GET / HTTP/1.1");
		log.setHttpStatus(200);
		log.setDescription("swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0");
	}

	@Test
	public void shouldLoadLogFile(){
		
	}
	
}
