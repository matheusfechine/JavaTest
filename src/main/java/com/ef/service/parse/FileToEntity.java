package com.ef.service.parse;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ef.model.Log;

public class FileToEntity {

	public Log transform(String line) throws ParseException {

		String[] tokens = line.split("\\|+");

		Log log = new Log();
		log.setStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tokens[0]));
		log.setIpAddress(tokens[1]);
		log.setHttpMethod(tokens[2]);
		log.setHttpStatus(Integer.valueOf(tokens[3]));
		log.setDescription(tokens[4]);
		return log;
	}

}
