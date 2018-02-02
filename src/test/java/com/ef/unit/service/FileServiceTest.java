package com.ef.unit.service;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ef.service.FileService;
import com.ef.service.LogService;
import com.ef.service.parse.FileToEntity;

public class FileServiceTest {

	@InjectMocks
    private FileService service;
	
	@Mock
	private LogService logService;
	
	@Mock
	private FileToEntity fileToEntity;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldLoadAndPersistFile() throws Exception {
		String line1 = "2017-01-01 00:00:11.763|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"";
		String line2 = "2017-01-01 00:00:21.164|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"";
		List<String> lines = Arrays.asList(line1, line2);
		service.loadAndPersist(lines);
		verify(logService, times(1)).insert(anyList());
	}
	
}
