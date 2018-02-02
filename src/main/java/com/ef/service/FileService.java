package com.ef.service;

import java.util.List;

public class FileService {

	private LogService logService;

	public void loadAndPersist(List<String> lines) throws Exception {
		initService();
		logService.insert(lines);
	}

	private void initService() {
		if (logService == null) {
			logService = new LogService();
		}
	}

}
