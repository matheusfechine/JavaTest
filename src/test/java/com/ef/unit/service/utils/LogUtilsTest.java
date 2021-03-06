package com.ef.unit.service.utils;

import static org.junit.Assert.*;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.ef.service.utils.LogUtils;

public class LogUtilsTest {

	private LogUtils utils;
	
	@Before
	public void setUp(){
		utils = new LogUtils();
	}
	
	public void shouldTransformStringToInteger(){
		String intString = "200";
		assertEquals(200, utils.parseToInteger(intString));
	}
	
	@Test
	public void shouldParseStartDateByHourly(){
		String dateString = "2017-01-01.13:00:00";
		Date expectedDate = new DateTime()
				.withYear(2017)
				.withDayOfMonth(1)
				.withMonthOfYear(1)
				.withHourOfDay(13)
				.withMinuteOfHour(0)
				.withSecondOfMinute(0)
				.withMillisOfSecond(0)
				.toDate();
		assertEquals(expectedDate, utils.parseStartDateBy(dateString, "hourly"));
		
	}
	
	@Test
	public void shouldParseEndDateByHourly(){
		String dateString = "2017-01-01.13:00:00";
		Date expectedDate = new DateTime()
				.withYear(2017)
				.withDayOfMonth(1)
				.withMonthOfYear(1)
				.withHourOfDay(14)
				.withMinuteOfHour(0)
				.withSecondOfMinute(0)
				.withMillisOfSecond(0)
				.toDate();
		assertEquals(expectedDate, utils.parseEndDateBy(dateString, "hourly"));
	}
	
	@Test
	public void shouldParseStartDateByDaily(){
		String dateString = "2017-01-01.13:00:00";
		Date expectedDate = new DateTime()
				.withYear(2017)
				.withDayOfMonth(1)
				.withMonthOfYear(1)
				.withHourOfDay(0)
				.withMinuteOfHour(0)
				.withSecondOfMinute(0)
				.withMillisOfSecond(0)
				.toDate();
		assertEquals(expectedDate, utils.parseStartDateBy(dateString, "daily"));
	}
	
	@Test
	public void shouldParseEndDateByDaily(){
		String dateString = "2017-01-01.13:00:00";
		Date expectedDate = new DateTime()
				.withYear(2017)
				.withDayOfMonth(1)
				.withMonthOfYear(1)
				.withHourOfDay(23)
				.withMinuteOfHour(59)
				.withSecondOfMinute(59)
				.withMillisOfSecond(59)
				.toDate();
		assertEquals(expectedDate, utils.parseEndDateBy(dateString, "daily"));
	}
	
}
