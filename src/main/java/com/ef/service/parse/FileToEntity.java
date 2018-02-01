package com.ef.service.parse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ef.model.Log;

public class FileToEntity {

	public Log transform(String line) throws ParseException {

		String[] tokens = line.split("\\|+");

		Log log = new Log();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		log.setStartDate(format.parse(tokens[0]));
		log.setIpAddress(tokens[1]);
		log.setHttpMethod(tokens[2]);
		log.setHttpStatus(Integer.valueOf(tokens[3]));
		log.setDescription(tokens[4]);
		return log;
	}

}
