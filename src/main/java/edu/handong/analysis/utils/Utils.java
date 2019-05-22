package edu.handong.analysis.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Utils {
	public static ArrayList<String> getLines(String file,boolean removeHeader){
		
		ArrayList<String> infoArray = new ArrayList<String>();
		try {
			Scanner inputStream = new Scanner(new File(file)); 
			String line = inputStream.nextLine();

			//int total = 0;
			while (inputStream.hasNextLine())
			{	
				line = inputStream.nextLine();
				infoArray.add(line);
			}
			if(removeHeader) {
				infoArray.remove(0);
			}
				inputStream.close();
			}
			catch(FileNotFoundException e) {
				System.out.println("Cannot find file " + file);
			}
			return infoArray;
	}
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		String fileName = targetFileName;
		PrintWriter outputStream = null;
		
		try {
			outputStream = new PrintWriter(fileName);
		} catch(FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}
		
		outputStream.close();
	}
}