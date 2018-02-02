package com.ef.service;

import java.text.ParseException;
import java.util.List;

import com.ef.model.Log;
import com.ef.service.parse.FileToEntity;

public class FileService {

	private FileToEntity fileToEntity;
	
	private LogService logService;
	
	public void loadAndPersist(List<String> lines) throws ParseException {
		
		for (String line : lines) {
			Log log = fileToEntity.transform(line);
			logService.insert(log);
		}
		
	}

	
}
