package com.ef.unit.dao.enumeration;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ef.dao.enumeration.Duration;

public class DurationTest {
	
	@Test
	public void shouldRetrieveByHourlyDescription(){
		assertEquals(Duration.HOURLY, Duration.getBy("hourly"));
	}
	@Test
	public void shouldRetrieveByDailyDescription(){
		assertEquals(Duration.DAILY, Duration.getBy("daily"));
	}
	@Test
	public void shouldNotRetrieveAny(){
		assertNull(Duration.getBy("anual"));
	}
	
}
