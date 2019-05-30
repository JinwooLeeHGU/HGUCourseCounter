package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentId;
	private ArrayList<Course> coursesTaken =  new ArrayList<Course>(); // instantiation!!; // 학생이 들은 수업정보 라인 한줄씩 목록
	private HashMap<String,Integer> semestersByYearAndSemester = new HashMap<String, Integer>(); // 총학기 개수 
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
		 
		coursesTaken.add(newRecord);
	}
	public HashMap<String,Integer> getSemestersByYearAndSemester() {   // 총학기 개수 
		//semestersByYearAndSemester = new HashMap<String, Integer>(); // instantiation!! 
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
	public int getNumCourseInNthSemester(int semester) {   //한 학기당 수업 몇개를 들었는지 
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

	public boolean getNumCourses(String course, int j, int k) {   
		boolean totalstu = false;
		String period;
		for(Course courses : coursesTaken) {
			int yearTaken = courses.getYearTaken();
			//System.out.println(yearTaken);
			int semesterTaken = courses.getSemesterCourseTaken();
			
			String key = yearTaken + "-" + semesterTaken;
			//System.out.println(key);
			
			period = j + "-" + k;
			if(key.contentEquals(period)) {
				totalstu = true;
			}
		}
		return totalstu;
	}	
	public boolean getNumStu(String course, int j, int k) {   
		boolean totalstu = false;
		String period;
		for(Course courses : coursesTaken) {
			int yearTaken = courses.getYearTaken();
			String courseCode = courses.getCourseCode();
			
			int semesterTaken = courses.getSemesterCourseTaken();
			String key = yearTaken + "-" + semesterTaken;
			
			period = j + "-" + k;
			
			if(key.contentEquals(period)) {
				if(courseCode.equals(course)) {
					totalstu = true;
				}
			}
		}
		return totalstu;
	}
	public String getCourseName(String coursecode) {   //한 학기당 수업 몇개를 들었는지 
		String courseName = null;
		for(Course courses : coursesTaken) {	
			String courseCode = courses.getCourseCode();		
			if(courseCode.equals(coursecode)) {
				courseName = courses.getCourseName();
			}
		}
		return courseName;
	}
}
