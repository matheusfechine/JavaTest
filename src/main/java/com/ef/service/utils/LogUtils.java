package com.ef.service.utils;

import java.util.Date;

import org.joda.time.format.DateTimeFormat;

import com.ef.dao.enumeration.Duration;

public class LogUtils {

	public int parseToInteger(String threshold) {
		return new Integer(threshold);
	}

	public Date parseStartDateBy(String date, String duration){
		if(Duration.getBy(duration).equals(Duration.HOURLY)){
			return DateTimeFormat.forPattern("yyyy-MM-dd.HH:mm:ss").parseDateTime(date).toDate();
		}
		if(Duration.getBy(duration).equals(Duration.DAILY)){
			return DateTimeFormat.forPattern("yyyy-MM-dd.HH:mm:ss")
					.parseDateTime(date)
					.withHourOfDay(0)
					.withMinuteOfHour(0)
					.withSecondOfMinute(0)
					.withMillisOfSecond(0)
					.toDate();
		}
		return null;
	}
	
	public Date parseEndDateBy(String date, String duration){
		if(Duration.getBy(duration).equals(Duration.HOURLY)){
			return DateTimeFormat.forPattern("yyyy-MM-dd.HH:mm:ss")
					.parseDateTime(date).plusHours(1).toDate();
		}
		if(Duration.getBy(duration).equals(Duration.DAILY)){
			return DateTimeFormat.forPattern("yyyy-MM-dd.HH:mm:ss")
					.parseDateTime(date)
					.withHourOfDay(23)
					.withMinuteOfHour(59)
					.withSecondOfMinute(59)
					.withMillisOfSecond(59)
					.toDate();
		}
		return null;
	}
	
}
