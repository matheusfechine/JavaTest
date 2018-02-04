package com.ef.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ef.service.exception.FileException;

public class FileService {

	public List<String> load(BufferedReader bufferedReader) throws FileException {
		List<String> lines = new ArrayList<String>();
		try {
			String line = "";
			while((line = bufferedReader.readLine()) != null){
				lines.add(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			throw new FileException("Unable to read file: "+e.getMessage());
		}
		return lines;
	}

}
