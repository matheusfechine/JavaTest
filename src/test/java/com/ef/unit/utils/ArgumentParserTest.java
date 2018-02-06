package com.ef.unit.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ef.utils.ArgumentParser;

public class ArgumentParserTest {

	private ArgumentParser parser;

	private String[] args = {"--accesslog=/path/to/file", "--startDate=2017-01-01.13:00:00", "--duration=hourly","--threshold=100"};
	
	@Before
	public void setUp(){
		parser = new ArgumentParser();
	}
	
	@Test
	public void shouldParseAnArgumentToStartDate(){
		String startDate = parser.parse("startDate", args);
		assertEquals("2017-01-01.13:00:00", startDate);
	}
	
	@Test
	public void shouldParseAnArgumentToAccessLog(){
		String accessLog = parser.parse("accesslog", args);
		assertEquals("/path/to/file", accessLog);
	}
	
	@Test
	public void shouldParseAnArgumentToDuration(){
		String duration = parser.parse("duration", args);
		assertEquals("hourly", duration);
	}

	@Test
	public void shouldParseAnArgumentToThreshold(){
		String threshold = parser.parse("threshold", args);
		assertEquals("100", threshold);
	}
	
	
	
}
