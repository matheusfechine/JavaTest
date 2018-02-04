package com.ef;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.ef.model.Log;
import com.ef.service.FileService;
import com.ef.service.LogService;
import com.ef.service.exception.FileException;
import com.ef.service.exception.LogException;

public class Parser {

	public static void main(String[] args) {
		try {
			LogService logService = new LogService();

			BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/access.log"));
			List<String> fileLines = new FileService().load(bufferedReader);
			logService.insert(fileLines);
			String startDate = "2017-01-01.13:00:00";
			String duration = "hourly";
			String threshold = "250";
			List<Log> ipsFound = logService.findBy(startDate, duration, threshold);
			for (Log log : ipsFound) {
				System.out.println("Found:"+ log.getIpAddress());
				
			}
			
		} catch (FileException e) {
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
