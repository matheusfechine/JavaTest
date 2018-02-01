package com.ef.unit.service.parse;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.ef.model.Log;
import com.ef.service.parse.FileToEntity;

public class FileToEntityTest {

	private Log log;
	
	private FileToEntity fileToEntity;
	
	@Before
	public void setUp() {
		fileToEntity = new FileToEntity();
		createExpectedLog();
	}

	private void createExpectedLog() {
		log = new Log();
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
		log.setHttpMethod("\"GET / HTTP/1.1\"");
		log.setHttpStatus(200);
		log.setDescription("\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"");;
	}
	
	@Test
	public void shouldTransformLineToLog() throws ParseException {
		String line = "2017-01-01 00:00:11.763|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"";
		Log lineTransformed = fileToEntity.transform(line);
		assertEquals(lineTransformed.toString(), log.toString());
		
	}
	
}
