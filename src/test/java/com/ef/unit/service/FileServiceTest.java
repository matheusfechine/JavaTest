package com.ef.unit.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ef.service.FileService;
import com.ef.service.exception.FileException;

public class FileServiceTest {

	@InjectMocks
	private FileService service;

	@Mock
	private BufferedReader bufferedReader;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldLoadAndPersistFile() throws Exception {
		String line1 = "2017-01-01 00:00:11.763|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"";
		String line2 = "2017-01-01 00:00:21.164|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"";
		List<String> lines = Arrays.asList(line1, line2);
		BufferedReader bufferedReader = mock(BufferedReader.class);
		when(bufferedReader.readLine()).thenReturn(line1, line2, null);
		assertEquals(lines, service.load(bufferedReader));
	}

	@Test(expected = FileException.class)
	public void shouldThrowFileException() throws IOException, FileException {
		BufferedReader bufferedReader = mock(BufferedReader.class);
		doThrow(IOException.class).when(bufferedReader).readLine();
		service.load(bufferedReader);

	}

}
