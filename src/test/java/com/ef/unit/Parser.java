package com.ef.unit;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ef.dao.LogDao;
import com.ef.model.Log;
import com.ef.service.LogService;

public class Parser {

	private Log log;
	
	@InjectMocks
	private LogService service;
	
	@Mock
	private LogDao dao;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
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
	public void shouldListLogs(){
		when(dao.findBy(any(Date.class), anyString(), anyInt())).thenReturn(Arrays.asList(log));
		service.findBy(new DateTime()
				.withYear(2017)
				.withDayOfMonth(1)
				.withMonthOfYear(1)
				.withHourOfDay(13)
				.withMinuteOfHour(0)
				.withSecondOfMinute(0)
				.withMillisOfSecond(0)
				.toDate(), "hourly", 100);
	}
	
}
