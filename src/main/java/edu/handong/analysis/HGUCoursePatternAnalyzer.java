package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.lang.String;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		ArrayList<String> lines = Utils.getLines(dataPath, true);
		
		students = loadStudentCourseRecords(lines);
		
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		
		// TODO: Implement this method
		// ArrayList<Map<String, Student>> list; 
		
		// System.out.println(lines.get(1));
		
		//HashMap<String, Student> myStudents = new HashMap<String, Student>();
		
		students = new HashMap<String, Student>(); // HashMap for students is declared above. 
		
		String[] studentNumber = new String[(lines.size())];
		String studentNo; 
		// for(int i=0;i<lines.size();i++) { 				// USE ENHANCED FOR LOOP TO CONVERT ARRAYLIST TO STRING!!!! 안그려면 너무 복잡해
		for(String line: lines) {
			// studentNo = line.get(i).trim().split(",")[0];
			// studentNumber[i] = lines.get(i).trim().split(",")[0];
			
			studentNo = line.trim().split(",")[0];
			//studentNumber[i] = lines.trim().split(",")[0];
			
			if(students.containsKey(studentNo)) {
				Course course = new Course(line);   // ArrayList를 String으로 Convert하지 말고 처음부터 enhanced for에서 String으로 변환!
				students.put(studentNo, course);
				
			}
			mArrayList.put(studentNumber[i], new Student(studentNumber[i]));     // student Id as key; student instance as value
			Course newRecord = new Course(lines.get(i));
			
			mArrayList.get(studentNumber[i]).addCourse(newRecord);
		}
		return mArrayList; // do not forget to return a proper variable.
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
		
		return null; // do not forget to return a proper variable.
	}
}
