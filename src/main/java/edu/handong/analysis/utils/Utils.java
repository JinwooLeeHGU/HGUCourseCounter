package edu.handong.analysis.utils;

import java.io.File;
import java.util.ArrayList;
//import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.*;

public class Utils {
	public static ArrayList<String> getLines(String file,boolean removeHeader){
		
		ArrayList<String> infoArray = new ArrayList<String>();
		
		try {
			 //파일 객체 생성
			File filename = new File(file);
			//입력 스트림 생성
			FileReader filereader = new FileReader(filename);
			//입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";			// string line에 초기화를 해줌. 아무것도 안넣은것 "";
            while((line = bufReader.readLine()) != null){
            	infoArray.add(line);
            	//System.out.println(line); //읽힘.
            }
            if(removeHeader) {
            	infoArray.remove(0);
			}
            //.readLine()does not read \n as newline        
            bufReader.close();
            //System.out.println(1);
        }catch (FileNotFoundException e) {
            
        }catch(IOException e){
        	System.out.println("The file path does not exist. Please check your CLI argument!");
        	System.exit(0);
        }
		
		//for(String line: infoArray) {
		//	System.out.println(line);
		//}
		
		return infoArray;
	}

	/*
	 * } try { Scanner inputStream = new Scanner(new File(file)); String line =
	 * inputStream.nextLine();
	 * 
	 * //int total = 0; while (inputStream.hasNextLine()) { line =
	 * inputStream.nextLine(); infoArray.add(line); } if(removeHeader) {
	 * infoArray.remove(0); } inputStream.close(); } catch(FileNotFoundException e)
	 * { System.out.
	 * println("The file path does not exist. Please check your CLI argument!"); }
	 * return infoArray; }
	 */
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		//String fileName = targetFileName;
		//PrintWriter outputStream = null;
		
		//try {
		//	outputStream = new PrintWriter(fileName);
		//} catch(FileNotFoundException e) {
			//System.out.println("The file path does not exist. Please check your CLI argument!");
			//System.exit(0);
		//} 
		
		//File tmp = new File(targetFileName); 
		
		//if(tmp.exists()==false) {
		//	tmp.getParentFile().mkdirs();
		//}
		
		File tmp = new File(targetFileName);
			try
		    {
			  FileWriter fw = new FileWriter(tmp); // Full path can also be done
		      BufferedWriter bw = new BufferedWriter(fw);
		      
		      for(String line: lines) {
			      bw.write(line);
			      //System.out.println(lines.size());
			      //System.out.println(line);
			      
			      bw.newLine(); // newline
		      }
		       
		      bw.close();
		    }
			catch(FileNotFoundException e) {
				try{
					tmp.getParentFile().mkdirs();
					tmp.createNewFile();
				}
				catch(IOException err) {
					System.out.println(err);
					System.exit(0);
				} 
			}catch(IOException e) {
				System.out.println(e);
			}
	
			
//			File tmp = new File("C:\\Users\\user\\Hw5");   
//			tmp.createNewFile();
			
	
		
	}
}