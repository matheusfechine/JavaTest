package com.ef.service.utils;

import java.util.Date;

import org.joda.time.format.DateTimeFormat;

public class LogUtils {

	public Date parseToData(String startDate) {
		return DateTimeFormat.forPattern("yyyy-MM-dd.HH:mm:ss").parseDateTime(startDate).toDate();
	}

	public int parseToInteger(String threshold) {
		return new Integer(threshold);
	}

}
