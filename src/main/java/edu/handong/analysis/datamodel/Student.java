package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentId;
	private ArrayList<Course> coursesTaken; // 학생이 들은 수업 목록
	private HashMap<String,Integer> semestersByYearAndSemester; 
	                                                         // key: Yearprivate Semester
	                                                         // e.g., 2003private 1, 
	public Student(String studentId) {
		this.studentId = studentId; 	// constructor
		// coursesTaken = new ArrayList<Course>();
		// semestersByYearAndSemester = new HashMap<String, Integer>();
	}
	public String getStudentId(){
		return studentId;
	}
	public void addCourse(Course newRecord) {
		coursesTaken = new ArrayList<Course>(); // instantiation!! 
		coursesTaken.add(newRecord);
	}
	public HashMap<String,Integer> getSemestersByYearAndSemester() {
		semestersByYearAndSemester = new HashMap<String, Integer>(); // instantiation!! 
		int semester = 1;
		for(Course course : coursesTaken) {
			int yearTaken = course.getYearTaken();
			int semesterTaken = course.getSemesterCourseTaken();
			String key = yearTaken + "-" + semesterTaken;
			
			if(!semestersByYearAndSemester.containsKey(key)) {
				semestersByYearAndSemester.put(key, semester);
				semester++;
			}
		}
		return semestersByYearAndSemester;
	}
	public int getNumCourseInNthSemester(int semester) {
		int count = 0;
		for(Course course : coursesTaken) {
			int yearTaken = course.getYearTaken();
			int semesterTaken = course.getSemesterCourseTaken();
			String key = yearTaken + "-" + semesterTaken;
			
			if(semester == semestersByYearAndSemester.get(key)) {
				count++;
			}	
		}	
		return count;
	}
	/* field에 대한 getter setter 필요에 따라 추가 */

}
