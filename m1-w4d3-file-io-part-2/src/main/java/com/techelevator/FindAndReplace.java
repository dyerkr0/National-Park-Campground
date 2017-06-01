package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FindAndReplace {

	public static void main(String[] args) {
		//Get the user to input the file to read off of
		File inputFile = getInputFileFromUser();
		Scanner input = new Scanner(System.in);
		
		//Get the search term and replacement terms from the user
		System.out.println("Please enter a search word.");
		String searchWord = input.nextLine();
		System.out.println("Please enter the word you want to use as a replacement.");
		String newWord = input.nextLine();
		
		//Get the location of the new file from the user
		File outputFile = getOutputFileFromUser();
		
		//Search through the input file, write replacement text to output file
		try(Scanner fileInput = new Scanner(inputFile)) {
			try(PrintWriter writer = new PrintWriter(outputFile)) {
				while(fileInput.hasNextLine()) {
					String originalLine = fileInput.nextLine();
					String line = originalLine;
					if(line.contains(searchWord)) {
						line = line.replace(searchWord, newWord);
					}
					writer.println(line);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static File getInputFileFromUser() {
		Scanner input = new Scanner(System.in); 
			System.out.println("Please enter the path to the file.");
			String path = input.nextLine();
			
			File file = new File(path);
			if(!file.exists()) {
				System.out.println("That file doesn't exist.");
				System.exit(1);
			} else if(!file.isFile()) {
				System.out.println(path+ " is a directory.");
				System.exit(1);
			}
			
			return file;
	}
	
	private static File getOutputFileFromUser() {
		System.out.println("Where would you like to save this file?");
		Scanner userInput = new Scanner(System.in);
		String path = userInput.nextLine();
		
		File outputFile = new File(path);
		if(outputFile.exists()) {
			System.out.println("File already exists.");
			System.exit(1);
		}
		
		return outputFile;
	}

}
