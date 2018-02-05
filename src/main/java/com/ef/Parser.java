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
			// --accesslog=/path/to/file --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100 
			String startDate = "2017-01-01.13:00:00";
			String duration = "daily";
			String threshold = "250";
			String accessLog = "src/main/resources/access.log";
			

			BufferedReader bufferedReader = new BufferedReader(new FileReader(accessLog));
			List<String> fileLines = new FileService().load(bufferedReader);
			
			LogService logService = new LogService();
			
			logService.insert(fileLines);
			List<Log> ipsFound = logService.findBy(startDate, duration, threshold);
			for (Log log : ipsFound) {
				String comment = "Ip "+log.getIpAddress()+" was blocked because exceeded "+ threshold+ " times "+duration;
				logService.insertIntoBlockList(log.getIpAddress(), comment);
				System.out.println(comment);
			}

		} catch (FileException e) {
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (LogException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
