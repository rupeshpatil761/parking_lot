package com.parkinglot;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import com.parkinglot.exception.InvalidFileException;

public class AppTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}

	@Test(expected = InvalidFileException.class)
	public void testInvalidFile() throws IOException {
		App.main(new String[] {});
	}

	@Test
	public void testFileModeWithInvalidFile() throws IOException {
		final String expectedOutput = "Invalid file given.";
		App.main(new String[] { "abc.txt" });
		assertEquals(expectedOutput, outContent.toString());
	}
}
