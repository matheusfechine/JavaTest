package com.ef.service.parse;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ef.model.Log;
import com.ef.service.parse.exception.FileToEntityException;

public class FileToEntity {

	public Log transform(String line) throws FileToEntityException {
		try {
			String[] tokens = line.split("\\|+");

			Log log = new Log();
			log.setStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tokens[0]));
			log.setIpAddress(tokens[1]);
			log.setHttpMethod(tokens[2]);
			log.setHttpStatus(Integer.valueOf(tokens[3]));
			log.setDescription(tokens[4]);
			return log;

		}catch(ParseException e) {
			throw new FileToEntityException("Parse Exception: "+e.getMessage());
		}
	}

}
