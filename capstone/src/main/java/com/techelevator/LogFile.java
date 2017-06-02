package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogFile {
	private PrintWriter writer;
	private LocalDateTime now = LocalDateTime.now();
	
	public LogFile() {
		try {
			writer = new PrintWriter(new File("Log.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void log(String message) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a");
		writer.println(now.format(format) + message);
		writer.flush();
	}
}
