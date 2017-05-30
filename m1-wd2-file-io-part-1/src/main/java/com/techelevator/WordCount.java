package com.techelevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) {
		File file = getFileFromUser();
		final String DELIMITER_WORD = " ";
		//final String DELIMITER_SENTENCE = ".!?";
		int wordCount = 0;
		int sentenceCount = 0;
		try(Scanner fileInput = new Scanner(file)) {
			while(fileInput.hasNextLine()) {
				String line = fileInput.nextLine();
				String [] wordArray = line.split(DELIMITER_WORD);
				for(int i = 0; i < wordArray.length; i++) {
					if(!wordArray[i].isEmpty()) {
						wordCount++;
						if(wordArray[i].contains(".") || wordArray[i].contains("?") || wordArray[i].contains("!")) {
							sentenceCount++;
						}
					}
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Word count: " +wordCount);
		System.out.println("Sentence count: " +sentenceCount);

	}
	
	private static File getFileFromUser() {
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

}
