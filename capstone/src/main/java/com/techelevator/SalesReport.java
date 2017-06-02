package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class SalesReport {
	private PrintWriter writer;
	
	public SalesReport() {
		try {
			writer = new PrintWriter(new File("SalesReport.txt"));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void report(List<String> message) {
		for(String output : message)
		writer.println(output);
		writer.flush();
	}
	
	public void totalAmount(String message) {
		writer.println(" ");
		writer.println("TOTAL SALES: " + message);
		writer.println(" ");
		writer.flush();
	}

}
