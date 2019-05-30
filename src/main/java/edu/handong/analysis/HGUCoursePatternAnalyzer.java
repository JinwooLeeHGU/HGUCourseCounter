package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.*;
import java.nio.file.Files;
import java.lang.String;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.nio.file.Paths;
//import java.util.Set;
//import java.util.Iterator;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer{

	private HashMap<String,Student> students;
	
	String inPath;
	String outPath;
	boolean analysis;
	boolean analysis1;
	boolean analysis2;
	String coursecode;
	String startYear;
	String endYear;
	boolean help;
		
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) throws IOException {
		ArrayList<CSVRecord> csvRecords = new ArrayList<CSVRecord>() ;

		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			// TODO show the number of files in the path
			if(analysis1) {
				try {
			            Reader reader = Files.newBufferedReader(Paths.get(inPath));
			            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);					
			        {
					for (CSVRecord csvRecord : csvParser) {
						csvRecords.add(csvRecord);		           
					}
					csvRecords.remove(0);
					
					students = loadStudentCourseRecords(csvRecords);
							                     		       
					}
				}catch(IOException e) {
					System.out.println("The file path does not exist. Please check your CLI argument!");
					System.exit(0);
				}
				
				// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
				Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
				
				// Generate result lines to be saved.
				ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
				
				// Write a file (named like the value of resultPath) with linesTobeSaved.
				Utils.writeAFile(linesToBeSaved, outPath);
			}
			if(analysis2) {
				try {
			            Reader reader = Files.newBufferedReader(Paths.get(inPath));
			            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);					
			        {
					for (CSVRecord csvRecord : csvParser) {
						csvRecords.add(csvRecord);		           
					}
					csvRecords.remove(0);
					
					students = loadStudentCourseRecords(csvRecords);
							                     		       
					}	
				}
		        catch(IOException e) {
					System.out.println("The file path does not exist. Please check your CLI argument!");
					System.exit(0);
				}
				
				// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
				Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
				
				// Generate result lines to be saved.
				ArrayList<String> linesToBeSaved = countNumberOfStudentsOfCoursesTaken(sortedStudents);
				
				// Write a file (named like the value of resultPath) with linesTobeSaved.
				Utils.writeAFile(linesToBeSaved, outPath);
			}
		}
			
	}
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			inPath = cmd.getOptionValue("i");
			outPath = cmd.getOptionValue("o");
			//if(cmd.hasOption("a")) {
			//System.out.println(cmd.getOptionValue("a"));
				if(cmd.getOptionValue("a").equals("1")) {
					analysis1 = true;
					//System.out.println("1");
				}
				else {
					analysis2 = true; 
					if(!cmd.hasOption("c")) printHelp(options);
					else coursecode = cmd.getOptionValue("c");
					//System.out.println("2");
				}
			
			startYear = cmd.getOptionValue("s");
			endYear = cmd.getOptionValue("e");
			help = cmd.hasOption("h");
			

		} catch (Exception e) {
			printHelp(options);
			
			return false;
		}

		return true;
	}
	
	// Definition Stage
	private Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, 2: Count per course name and year")
				.hasArg()
				.argName("Analysis option")
				.required()
				.build());
		
		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2' option")
				.hasArg()
				.argName("course code")
				.required(analysis2)
				.build());
				
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg()
				.argName("Start year for analysis")
				.required()
				.build());
		
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005")
				.hasArg()
				.argName("End year for analysis")
				.required()
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show a Help page")
				//.hasArg()
				.argName("Help")
				//.required()
				.build());
		
		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer ="";
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}

	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<CSVRecord> lines) {
		
		// TODO: Implement this method
		students = new HashMap<String, Student>(); // HashMap for students is declared above. 
		Student studentInstance= null; 
		
		String studentNo; 
		
		for (CSVRecord csvRecord : lines) {
			
			studentNo = csvRecord.get(0).trim();
			//System.out.println(studentNo);
			if(!students.containsKey(studentNo)){
				studentInstance= new Student(studentNo);
				Course course = new Course(csvRecord);   
				studentInstance.addCourse(course);
				
				students.put(studentNo, studentInstance);				
			}
			else {
				Course course = new Course(csvRecord);   
				studentInstance.addCourse(course);
			}
		}
	
			return students;
	}

	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		
		// TODO: Implement this method		
		String StudentID;
		int TotalNumberOfSemestersRegistered;
		int Semester;
		int NumCoursesTakenInTheSemester;
		int i=1;
		Student studentInfo;
		String linesToBeSaved;
		
		ArrayList<String> result = new ArrayList<String>();
		//Set<Map.Entry<String, Student>> entries = sortedStudents.entrySet();
		
		String Title = "StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester";  // add one line title 
		result.add(Title);
		
		
				for(String eachStudent : sortedStudents.keySet() ){
					StudentID= eachStudent;
					
					//System.out.println(StudentID);
				
					studentInfo= sortedStudents.get(StudentID);
					TotalNumberOfSemestersRegistered= studentInfo.getSemestersByYearAndSemester().size();  // sizeof Hashmap 
					for(int j= Integer.parseInt(startYear); j<=Integer.parseInt(endYear); j++) {
						for(int k= 1; k<=4; k++) {
							i=1;
							while(TotalNumberOfSemestersRegistered>=i) {
								Semester=i;
								if(studentInfo.getNumCourses(coursecode, j, k) == true) {
									NumCoursesTakenInTheSemester= studentInfo.getNumCourseInNthSemester(Semester);
									linesToBeSaved = StudentID + ","+ TotalNumberOfSemestersRegistered + "," + Semester + "," + NumCoursesTakenInTheSemester;
									result.add(linesToBeSaved);																								
								}
								i++;
							} 
						}
					}
				}
		
		return result; // do not forget to return a proper variable.
	}
	private ArrayList<String> countNumberOfStudentsOfCoursesTaken(Map<String, Student> sortedStudents) {
		
		// TODO: Implement this method
		
		String StudentID;
		int TotalNumberOfStudentsRegistered;
		int CourseStudent=0;
		//int Semester=1;
		Student studentInfo;
		String linesToBeSaved = null;
		String courseName = null;
		double rate = 0;
		
		ArrayList<String> result = new ArrayList<String>();
		//Set<Map.Entry<String, Student>> entries = sortedStudents.entrySet();
		
		String Title = "Year,Semester,CouseCode, CourseName,TotalStudents,StudentsTaken,Rate";  // add one line title 
		result.add(Title);		

			for(int j= Integer.parseInt(startYear); j<=Integer.parseInt(endYear); j++) {
				for(int k= 1; k<=4; k++) {
					TotalNumberOfStudentsRegistered= 0;
					CourseStudent = 0; 
					for(String eachStudent : sortedStudents.keySet() ){
						StudentID= eachStudent;
						studentInfo= sortedStudents.get(StudentID);
						
						if(studentInfo.getNumCourses(coursecode, j, k) == true) {
							courseName = studentInfo.getCourseName(coursecode);
							TotalNumberOfStudentsRegistered++;						
						}
						if(studentInfo.getNumStu(coursecode, j, k) == true) {
							CourseStudent++;
							
						}						
					}	
					if(CourseStudent!=0 && TotalNumberOfStudentsRegistered!=0) {
						rate = (CourseStudent/(double)TotalNumberOfStudentsRegistered)*100;
						//System.out.println(rate); 
						linesToBeSaved = j + ","+ k +  ","+ coursecode +  ","+ courseName +  ","+TotalNumberOfStudentsRegistered + "," + CourseStudent + "," + String.format("%.1f", rate)+"%";
						result.add(linesToBeSaved);
					}				
				}					
			}
	
			//System.out.println(linesToBeSaved); 
			
			return result; // do not forget to return a proper variable.
	  	}
		
}
